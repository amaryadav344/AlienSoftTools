package com.webstudio.hub.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webstudio.hub.config.HubConfig;

import java.nio.file.Paths;

public class HelperFunctions {
    @JsonIgnore
    public static String getHubConfigFilePath() {
        String dirtyPath = HubConfig.class.getResource("").toString();
        String jarPath = dirtyPath.replaceAll("^.*file:/", ""); //removes file:/ and everything before it
        jarPath = jarPath.replaceAll("jar!.*", "jar"); //removes everything after .jar, if .jar exists in dirtyPath
        jarPath = jarPath.replaceAll("%20", " "); //necessary if path has spaces within
        if (!jarPath.endsWith(".jar")) { // this is needed if you plan to run the app using Spring Tools Suit play button.
            jarPath = jarPath.replaceAll("/classes/.*", "/classes/");
        }
        String directoryPath = Paths.get(jarPath).getParent().toString(); //Paths - from java 8
        return directoryPath + "\\HubConfig.xml";
    }
}
