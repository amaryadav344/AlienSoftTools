package com.webstudio.hub.common;

import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;

class ModelBuilder {
    private TypeSpec.Builder builder;
    private String PackageName;

    ModelBuilder(String ClassName) {
        this.builder = TypeSpec.classBuilder(ClassName);
    }

    ModelBuilder(String ClassName, boolean IsInterface) {
        if (IsInterface) {
            this.builder = TypeSpec.interfaceBuilder(ClassName);
        }
    }

    ModelBuilder setPackageName(String packageName) {
        PackageName = packageName;
        return this;
    }

    ModelBuilder addType(TypeSpec typeSpec) {
        builder.addType(typeSpec);
        return this;
    }

    ModelBuilder setModifier(Modifier modifier) {
        builder.addModifiers(modifier);
        return this;
    }

    ModelBuilder setSuperClass(ClassName SuperClass) {
        this.builder.superclass(SuperClass);
        return this;
    }

    ModelBuilder setSuperClass(ParameterizedTypeName SuperClass) {
        this.builder.superclass(SuperClass);
        return this;
    }

    ModelBuilder setSuperInterface(ParameterizedTypeName SuperClass) {
        this.builder.addSuperinterface(SuperClass);
        return this;
    }

    ModelBuilder addAnnotation(ClassName className, String MemberName, String value) {
        AnnotationSpec annotationSpec = AnnotationSpec.builder(className)
                .addMember(MemberName, "$S", value)
                .build();
        this.builder.addAnnotation(annotationSpec);
        return this;
    }

    ModelBuilder addAnnotation(Class<?> className, String MemberName, String value, Object... args) {
        AnnotationSpec annotationSpec = AnnotationSpec.builder(className)
                .addMember(MemberName, value, args)
                .build();
        this.builder.addAnnotation(annotationSpec);
        return this;
    }

    ModelBuilder addAnnotation(ClassName className) {
        AnnotationSpec annotationSpec = AnnotationSpec.builder(className)
                .build();
        this.builder.addAnnotation(annotationSpec);
        return this;
    }

    ModelBuilder addAnnotation(Class type, String MemberName, String value) {
        AnnotationSpec annotationSpec = AnnotationSpec.builder(type)
                .addMember(MemberName, "$S", value)
                .build();
        this.builder.addAnnotation(annotationSpec);
        return this;
    }

    ModelBuilder addAnnotation(Class type) {
        AnnotationSpec annotationSpec = AnnotationSpec.builder(type)
                .build();
        this.builder.addAnnotation(annotationSpec);
        return this;
    }


    ModelBuilder addField(TypeName type, String name, Modifier modifier, boolean creatGetterSetters, boolean PrimaryKey) {
        FieldSpec.Builder fieldSpecBuilder = FieldSpec.builder(type, name, Modifier.PRIVATE);
        if (PrimaryKey) {
            fieldSpecBuilder.addAnnotation(ClassName.get("javax.persistence", "Id"));
        }
        builder.addField(fieldSpecBuilder.build());
        if (creatGetterSetters) {
            MethodSpec methodSpecGetter = MethodSpec.methodBuilder("get" + name)
                    .addModifiers(Modifier.PUBLIC)
                    .returns(type)
                    .addStatement("return $L", name)
                    .build();
            builder.addMethod(methodSpecGetter);
            MethodSpec methodSpecSetter = MethodSpec.methodBuilder("set" + name)
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(ParameterSpec.builder(type, name).build())
                    .addStatement("this.$L=$L", name, name)
                    .build();
            builder.addMethod(methodSpecSetter);
        }
        return this;
    }

    ModelBuilder addMethod(MethodSpec methodSpec) {
        builder.addMethod(methodSpec);
        return this;
    }

    JavaFile build() {
        return JavaFile.builder(PackageName, builder.build()).build();
    }

}
