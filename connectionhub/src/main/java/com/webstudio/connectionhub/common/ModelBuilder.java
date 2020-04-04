package com.webstudio.connectionhub.common;

import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;

public class ModelBuilder {
    private TypeSpec.Builder builder;
    private String PackageName;

    public ModelBuilder(String ClassName) {
        this.builder = TypeSpec.classBuilder(ClassName);
    }

    public ModelBuilder setPackageName(String packageName) {
        PackageName = packageName;
        return this;
    }

    public ModelBuilder setModifier(Modifier modifier) {
        builder.addModifiers(modifier);
        return this;
    }

    public ModelBuilder setSuperClass(ClassName SuperClass) {
        this.builder.superclass(SuperClass);
        return this;
    }

    public ModelBuilder addField(TypeName type, String name, Modifier modifier, boolean creatGetterSetters) {
        FieldSpec fieldSpec = FieldSpec.builder(type, name, modifier).build();
        builder.addField(fieldSpec);
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

    public JavaFile build() {
        return JavaFile.builder(PackageName, builder.build()).build();
    }

}
