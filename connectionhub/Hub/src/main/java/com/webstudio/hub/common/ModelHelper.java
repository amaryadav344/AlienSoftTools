package com.webstudio.hub.common;

import com.business.utils.models.Entity.IAttribute;
import com.business.utils.models.Entity.IEntity;
import com.squareup.javapoet.*;
import com.webstudio.hub.models.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ModelHelper {
    public static void createModel(IEntity entity, Branch DefaultBranch, String destinationPath) throws IOException {

        String BasePackageName = DefaultBranch.getPackageName() + "." + destinationPath.replace(Constants.Common.FOLDER_SEPARATOR, ".");
        // DTO
        JavaFile javaEntityFile = CreateDTO(BasePackageName, DefaultBranch, entity);

        // Service
        JavaFile javaServiceFile = CreateService(BasePackageName, DefaultBranch, entity);

        // Repository
        JavaFile javaRepositoryFile = CreateRepository(BasePackageName, DefaultBranch, entity);

        // Validator
        JavaFile javaValidatorFile = CreateValidator(BasePackageName, DefaultBranch, entity);

        // Constants
        JavaFile javaConstantFile = CreateConstants(BasePackageName, DefaultBranch, entity);

        File file = new File(DefaultBranch.getSourcePath());
        javaEntityFile.writeTo(file);
        javaServiceFile.writeTo(file);
        javaRepositoryFile.writeTo(file);
        javaValidatorFile.writeTo(file);
        javaConstantFile.writeTo(file);
    }

    // Create Constants
    private static JavaFile CreateConstants(String BasePackageName, Branch DefaultBranch, IEntity entity) {
        ModelBuilder ConstantModelBuilder = new ModelBuilder(entity.getName() + "Constants")
                .setPackageName(GetConstantsPackageName(BasePackageName))
                .setModifier(Modifier.PUBLIC);

        FieldSpec.Builder builder = FieldSpec.builder(String.class,
                entity.getName().toUpperCase() + "_SERVICE",
                Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL);
        builder.initializer("$S", entity.getServiceName());

        FieldSpec.Builder builderR = FieldSpec.builder(String.class,
                entity.getName().toUpperCase() + "_REPOSITORY",
                Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL);
        builderR.initializer("$S", entity.getName() + "Repository");

        FieldSpec.Builder builderV = FieldSpec.builder(String.class,
                entity.getName().toUpperCase() + "_VALIDATOR",
                Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL);
        builderV.initializer("$S", entity.getName() + "Validator");

        TypeSpec typeSpec = TypeSpec.classBuilder("BeanName")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addField(builder.build())
                .addField(builderR.build())
                .addField(builderV.build())
                .build();
        ConstantModelBuilder.addType(typeSpec);
        return ConstantModelBuilder.build();
    }

    //Create Validator
    private static JavaFile CreateValidator(String BasePackageName, Branch DefaultBranch, IEntity entity) {
        ModelBuilder ServiceModelBuilder = new ModelBuilder(entity.getName() + "Validator")
                .setPackageName(GetValidatorPackageName(BasePackageName))
                .addAnnotation(Component.class, "value", "$T.$L.$L",
                        ClassName.get(GetConstantsPackageName(BasePackageName),
                                entity.getName() + "Constants"),
                        "BeanName", entity.getName().toUpperCase() + "_VALIDATOR")
                .setSuperClass(ParameterizedTypeName.get(ClassName.bestGuess(DefaultBranch.getValidationBase()),
                        ClassName.get(GetDTOPackageName(BasePackageName),
                                entity.getName())))
                .setModifier(Modifier.PUBLIC);
        return ServiceModelBuilder.build();
    }

    //  Create Repository
    private static JavaFile CreateRepository(String BasePackageName, Branch DefaultBranch, IEntity entity) {
        ModelBuilder RepositoryModelBuilder = new ModelBuilder(entity.getName() + "Repository", true)
                .setPackageName(GetRepositoryPackageName(BasePackageName))
                .setSuperInterface(ParameterizedTypeName.get(ClassName.bestGuess(DefaultBranch.getRepositoryBase()),
                        ClassName.get(GetDTOPackageName(BasePackageName), entity.getName()), ClassName.get(Integer.class)))
                .addAnnotation(Repository.class, "value", "$T.$L.$L",
                        ClassName.get(GetConstantsPackageName(BasePackageName),
                                entity.getName() + "Constants"),
                        "BeanName", entity.getName().toUpperCase() + "_REPOSITORY")
                .setModifier(Modifier.PUBLIC);
        return RepositoryModelBuilder.build();
    }

    // Create Service
    private static JavaFile CreateService(String BasePackageName, Branch DefaultBranch, IEntity entity) {
        ModelBuilder ServiceModelBuilder = new ModelBuilder(entity.getServiceName())
                .setPackageName(GetServicePackageName(BasePackageName))
                .addAnnotation(Service.class, "value", "$T.$L.$L",
                        ClassName.get(GetConstantsPackageName(BasePackageName),
                                entity.getName() + "Constants"),
                        "BeanName", entity.getName().toUpperCase() + "_SERVICE")
                .setSuperClass(ParameterizedTypeName.get(ClassName.bestGuess(DefaultBranch.getServiceBase()),
                        ClassName.get(GetDTOPackageName(BasePackageName),
                                entity.getName()), ClassName.get(Integer.class)))
                .setModifier(Modifier.PUBLIC);
        MethodSpec methodSpec = MethodSpec.constructorBuilder()
                .addAnnotation(Autowired.class)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(ParameterSpec.builder(ClassName.get(GetRepositoryPackageName(BasePackageName), entity.getName() + "Repository"), entity.getName() + "Repository").build())
                .addParameter(ParameterSpec.builder(ClassName.get(GetValidatorPackageName(BasePackageName), entity.getName() + "Validator"), entity.getName() + "Validator").build())
                .addStatement("super($L, $L)", entity.getName() + "Repository", entity.getName() + "Validator")
                .build();
        ServiceModelBuilder.addMethod(methodSpec);
        return ServiceModelBuilder.build();
    }

    // Create DTO
    private static JavaFile CreateDTO(String BasePackageName, Branch defaultBranch, IEntity entity) {
        ClassName busBase = ClassName.bestGuess(defaultBranch.getBusinessObjectBase());
        ModelBuilder EntityModelBuilder =
                new ModelBuilder(entity.getName())
                        .setPackageName(GetDTOPackageName(BasePackageName))
                        .setSuperClass(busBase)
                        .addAnnotation(ClassName.get("javax.persistence", "Entity"))
                        .addAnnotation(ClassName.get("javax.persistence", "Table"),
                                "name", entity.getTableName())
                        .setModifier(Modifier.PUBLIC);
        for (IAttribute attribute : entity.getAttributes()) {
            if (attribute.getDataType().toLowerCase().equals("string")) {
                EntityModelBuilder.addField(ClassName.get(String.class), attribute.getName(), Modifier.PUBLIC, true, attribute.getIsPrimaryKey());
            }
            if (attribute.getDataType().toLowerCase().equals("integer")) {
                EntityModelBuilder.addField(ClassName.get(Integer.class), attribute.getName(), Modifier.PUBLIC, true, attribute.getIsPrimaryKey());
            }
            if (attribute.getDataType().toLowerCase().equals("datetime")) {
                EntityModelBuilder.addField(ClassName.get(Date.class), attribute.getName(), Modifier.PUBLIC, true, attribute.getIsPrimaryKey());
            }
        }
        return EntityModelBuilder.build();
    }


    private static String GetDTOPackageName(String BasePackageName) {
        return BasePackageName + Constants.Common.FULL_STOP + "DTO";
    }

    private static String GetServicePackageName(String BasePackageName) {
        return BasePackageName + Constants.Common.FULL_STOP + "Service";
    }

    private static String GetRepositoryPackageName(String BasePackageName) {
        return BasePackageName + Constants.Common.FULL_STOP + "Repository";
    }

    private static String GetConstantsPackageName(String BasePackageName) {
        return BasePackageName + Constants.Common.FULL_STOP + "Constant";
    }

    private static String GetValidatorPackageName(String BasePackageName) {
        return BasePackageName + Constants.Common.FULL_STOP + "Validator";
    }


/*    ModelBuilder ValidatorBuilder = new ModelBuilder(entity.getName() + "Validator")
            .setPackageName(ValidationPackageName)
            .setModifier(Modifier.PUBLIC)
            .setSuperInterface(ParameterizedTypeName.get(ClassName.get("javax.validation", "ConstraintValidator"),
                    ClassName.get(ValidationPackageName, "Valid" + entity.getName()), ClassName.get(GetDTOPackageName(BasePackageName), entity.getName())));

    MethodSpec methodSpecValid = MethodSpec.methodBuilder("isValid")
            .addAnnotation(Override.class)
            .addModifiers(Modifier.PUBLIC)
            .addParameter(ClassName.get(GetDTOPackageName(BasePackageName), entity.getName()), entity.getName())
            .addParameter(ClassName.get("javax.validation", "ConstraintValidatorContext"), "constraintValidatorContext")
            .returns(boolean.class)
            .addStatement("return false")
            .build();

        ValidatorBuilder.addMethod(methodSpecValid);
    JavaFile ValidatorFile = ValidatorBuilder.build();

    // Valid
    TypeSpec.Builder ValidationBuilder = TypeSpec.annotationBuilder(ClassName.get(ValidationPackageName, "Valid" + entity.getName()));
    AnnotationSpec annotationSpec = AnnotationSpec.builder(Target.class)
            .addMember("value", "$T.FIELD", ElementType.class)
            .build();
        ValidationBuilder.addAnnotation(annotationSpec);
    AnnotationSpec annotationSpecR = AnnotationSpec.builder(Retention.class)
            .addMember("value", "$T.RUNTIME", RetentionPolicy.class)
            .build();
        ValidationBuilder.addAnnotation(annotationSpecR);

    AnnotationSpec annotationSpecV =
            AnnotationSpec.builder(ClassName.get("javax.validation", "Constraint"))
                    .addMember("validatedBy", "$L", entity.getName() + "Validator" + ".class")
                    .build();
        ValidationBuilder.addAnnotation(annotationSpecV);
        ValidationBuilder.addModifiers(Modifier.PUBLIC);
    MethodSpec messageMethod = MethodSpec.methodBuilder("message")
            .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
            .returns(String.class)
            .defaultValue("$S", entity.getName() + " is not allowed")
            .build();
    TypeName wildcard = WildcardTypeName.subtypeOf(Object.class);
    TypeName classOfAny = ParameterizedTypeName.get(
            ClassName.get(Class.class), wildcard);
    MethodSpec groupsMethod = MethodSpec.methodBuilder("groups")
            .returns(ArrayTypeName.of(classOfAny))
            .defaultValue("{}")
            .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
            .build();

    TypeName wildcardPayload = WildcardTypeName.subtypeOf(Payload.class);
    MethodSpec payloadMethod = MethodSpec.methodBuilder("payload")
            .returns(ArrayTypeName.of(ParameterizedTypeName.get(ClassName.get(Class.class), wildcardPayload)))
            .defaultValue("{}")
            .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
            .build();
        ValidationBuilder.addMethod(messageMethod);
        ValidationBuilder.addMethod(groupsMethod);
        ValidationBuilder.addMethod(payloadMethod);
    JavaFile ValidationFile = JavaFile.builder(ValidationPackageName, ValidationBuilder.build()).build();*/
}
