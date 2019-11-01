package com.webstudio.connectionhub.common;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.webstudio.connectionhub.models.IColumn;
import com.webstudio.connectionhub.models.IEntity;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ModelHelper {
    public static void createModel(String path, IEntity entity, String PackageName, String BSModelPath) throws IOException {
        String cdoClassName = "cdo" + entity.getName();
        String doClassName = "do" + entity.getName();
        String modelClassName = entity.getName();
        ModelBuilder modelBuilder = new ModelBuilder(modelClassName)
                .setPackageName(PackageName)
                .setModifier(Modifier.PUBLIC);
        ModelBuilder cdoModelBuilder = new ModelBuilder(cdoClassName).setPackageName(PackageName)
                .setModifier(Modifier.PUBLIC);
        ModelBuilder doModelBuilder = new ModelBuilder(doClassName).setPackageName(PackageName)
                .setModifier(Modifier.PUBLIC);
        for (IColumn column : entity.getColumns()) {
            if (column.getDataType().toLowerCase().equals("string")) {
                doModelBuilder.addField(ClassName.get(String.class), column.getName(), Modifier.PUBLIC, true);
            }
            if (column.getDataType().toLowerCase().equals("integer")) {
                doModelBuilder.addField(ClassName.get(Integer.class), column.getName(), Modifier.PUBLIC, true);
            }
            if (column.getDataType().toLowerCase().equals("datetime")) {
                doModelBuilder.addField(ClassName.get(Date.class), column.getName(), Modifier.PUBLIC, true);
            }
        }
        cdoModelBuilder.setSuperClass(doClassName);
        modelBuilder.addField(ClassName.get(PackageName, cdoClassName), "i" + cdoClassName, Modifier.PUBLIC, true);
        JavaFile javaFiledo = doModelBuilder.build();
        JavaFile javfilecdo = cdoModelBuilder.build();
        JavaFile javfilemodel = modelBuilder.build();
        File file = new File(BSModelPath);
        javaFiledo.writeTo(file);
        javfilecdo.writeTo(file);
        javfilemodel.writeTo(file);

    }
}