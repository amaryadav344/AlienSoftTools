package com.business.utils;

import com.business.utils.models.Entity.IFile;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.File;
import java.nio.file.Paths;

public class HelperFunctions {
    @JsonIgnore
    public static String getExecutableHomePath(Class c, String fileName) {
        String dirtyPath = c.getResource("\\").toString();
        String jarPath = dirtyPath.replaceAll("^.*file:/", ""); //removes file:/ and everything before it
        jarPath = jarPath.replaceAll("jar!.*", "jar"); //removes everything after .jar, if .jar exists in dirtyPath
        jarPath = jarPath.replaceAll("%20", " "); //necessary if path has spaces within
        if (!jarPath.endsWith(".jar")) { // this is needed if you plan to run the app using Spring Tools Suit play button.
            jarPath = jarPath.replaceAll("/classes/.*", "/classes/");
        }
        String directoryPath = Paths.get(jarPath).getParent().toString(); //Paths - from java 8
        return directoryPath + "\\" + fileName;
    }

    public static String SnakeCaseToCamelCase(String input) {
        StringBuilder sb = new StringBuilder();
        for (String s : input.split("_")) {
            sb.append(Character.toUpperCase(s.charAt(0)));
            if (s.length() > 1) {
                sb.append(s.substring(1, s.length()).toLowerCase());
            }
        }
        return sb.toString();
    }

    public static String GetFullyQualifiedModelName(IFile file, String modelName, String XmlPath, String PackageName) {
        String RelativeFilePath = file.getPath().replace(XmlPath, "");
        String ModelRelativePath = RelativeFilePath.replace(file.getName(), "DTO." + modelName);
        return PackageName + ModelRelativePath.replace(File.separator, ".");

    }

}
