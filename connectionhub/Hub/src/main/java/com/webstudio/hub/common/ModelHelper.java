package com.webstudio.hub.common;

import com.business.utils.models.Entity.IAttribute;
import com.business.utils.models.Entity.IEntity;
import com.squareup.javapoet.*;
import com.webstudio.hub.models.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.lang.model.element.Modifier;
import javax.validation.Payload;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Date;

public class ModelHelper {
    public static void createModel(IEntity entity, Branch DefaultBranch, String destinationPath) throws IOException {

        // DTO
        String BasePackageName = DefaultBranch.getPackageName() + "." + destinationPath.replace(Constants.Common.FOLDER_SEPARATOR, ".");
        String DTOPackageName = BasePackageName + Constants.Common.FULL_STOP + "DTO";
        ClassName busBase = ClassName.bestGuess(DefaultBranch.getBusinessObjectBase());
        ModelBuilder EntityModelBuilder =
                new ModelBuilder(entity.getName())
                        .setPackageName(DTOPackageName)
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
        JavaFile javaEntityFile = EntityModelBuilder.build();

        // Service
        String ServicePackageName = BasePackageName + Constants.Common.FULL_STOP + "Service";
        String RepositoryPackageName = BasePackageName + Constants.Common.FULL_STOP + "Repository";

        ModelBuilder ServiceModelBuilder = new ModelBuilder(entity.getServiceName())
                .setPackageName(ServicePackageName)
                .addAnnotation(Service.class)
                .addAnnotation(Scope.class, "value", WebApplicationContext.SCOPE_SESSION)
                .setSuperClass(ClassName.bestGuess(DefaultBranch.getServiceBase()))
                .setModifier(Modifier.PUBLIC)
                .addField(ClassName.get(RepositoryPackageName, entity.getName() + "Repository")
                        , entity.getName() + "Repository", Modifier.PRIVATE, false, false);
        MethodSpec methodSpec = MethodSpec.methodBuilder("set" + entity.getName() + "Repository")
                .addAnnotation(Autowired.class)
                .addParameter(ClassName.get(RepositoryPackageName, entity.getName() + "Repository"),
                        entity.getName() + "Repository")
                .addStatement("this.$L=$L", entity.getName() + "Repository", entity.getName() + "Repository")
                .build();
        ServiceModelBuilder.addMethod(methodSpec);
        JavaFile javaServiceFile = ServiceModelBuilder.build();

        // Repository
        ModelBuilder RepositoryModelBuilder = new ModelBuilder(entity.getName() + "Repository", true)
                .setPackageName(RepositoryPackageName)
                .setSuperInterface(ParameterizedTypeName.get(ClassName.bestGuess(DefaultBranch.getRepositoryBase()),
                        ClassName.get(DTOPackageName, entity.getName()), ClassName.get(Integer.class)))
                .addAnnotation(Repository.class)
                .addAnnotation(Scope.class, "value", WebApplicationContext.SCOPE_SESSION)
                .setModifier(Modifier.PUBLIC);
        JavaFile javaRepositoryFile = RepositoryModelBuilder.build();

        String ValidationPackageName = BasePackageName + Constants.Common.FULL_STOP + "Validation";
        // Validator
        ModelBuilder ValidatorBuilder = new ModelBuilder(entity.getName() + "Validator")
                .setPackageName(ValidationPackageName)
                .setModifier(Modifier.PUBLIC)
                .setSuperInterface(ParameterizedTypeName.get(ClassName.get("javax.validation", "ConstraintValidator"),
                        ClassName.get(ValidationPackageName, "Valid" + entity.getName()), ClassName.get(DTOPackageName, entity.getName())));

        MethodSpec methodSpecValid = MethodSpec.methodBuilder("isValid")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(ClassName.get(DTOPackageName, entity.getName()), entity.getName())
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
        JavaFile ValidationFile = JavaFile.builder(ValidationPackageName, ValidationBuilder.build()).build();


        File file = new File(DefaultBranch.getSourcePath());
        javaEntityFile.writeTo(file);
        javaServiceFile.writeTo(file);
        javaRepositoryFile.writeTo(file);
        ValidatorFile.writeTo(file);
        ValidationFile.writeTo(file);
    }
}
