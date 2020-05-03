package com.webstudio.hub.common;

import com.business.utils.models.Entity.IColumn;
import com.business.utils.models.Entity.IEntity;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ModelHelper {
    public static void createModel(IEntity entity, String PackageName, String Path, String destinationPath) throws IOException {

        ClassName busBase = ClassName.get(PackageName, "BusBase");
        ClassName doBase = ClassName.get(PackageName, "DoBase");
        PackageName = PackageName + "." + destinationPath.replace(Constants.Common.FOLDER_SEPARATOR, ".");
        String cdoClassName = "cdo" + entity.getModelName().substring(3);
        String doClassName = "do" + entity.getModelName().substring(3);
        String modelClassName = entity.getModelName();
        File file = new File(Path);
        ModelBuilder modelBuilder = new ModelBuilder(modelClassName)
                .setPackageName(PackageName)
                .setSuperClass(busBase)
                .setModifier(Modifier.PUBLIC);
        if (!entity.getIsWrapper()) {
            ModelBuilder cdoModelBuilder = new ModelBuilder(cdoClassName).setPackageName(PackageName)
                    .setModifier(Modifier.PUBLIC);
            ModelBuilder doModelBuilder = new ModelBuilder(doClassName).setPackageName(PackageName)
                    .setSuperClass(doBase)
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
            cdoModelBuilder.setSuperClass(ClassName.get(PackageName, doClassName));
            JavaFile javaFiledo = doModelBuilder.build();
            JavaFile javfilecdo = cdoModelBuilder.build();
            javaFiledo.writeTo(file);
            javfilecdo.writeTo(file);
            modelBuilder.addField(ClassName.get(PackageName, cdoClassName), "i" + cdoClassName, Modifier.PUBLIC, true);
        }
        JavaFile javfilemodel = modelBuilder.build();
        javfilemodel.writeTo(file);

    }
}
