package com.webstudio.hub.models;

import com.business.utils.FileHelper;
import com.business.utils.XMLWorker;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

@Component(value = "HubConfig")
@JacksonXmlRootElement(localName = "HubConfig")
public class HubConfig implements DisposableBean {
    public static Logger logger = Logger.getLogger(HubConfig.class.getName());
    Database Database;
    List<Branch> branches;
    @JsonIgnore
    private static HubConfig mHubConfig;


    @JsonIgnore
    public static HubConfig getInstance() {
        return mHubConfig;
    }

    private HubConfig() {

    }

    @PostConstruct
    public static void InitializeeConfig() throws IOException {
        if (mHubConfig == null) {
            String content = FileHelper.ReadCompleteFile(getHubConfigFilePath());
            mHubConfig = XMLWorker.getInstance().readCustomType(content, HubConfig.class);
        }
    }

    @JacksonXmlElementWrapper(localName = "Database")
    public Database getDatabaseConnection() {
        return Database;
    }

    public void setDatabaseConnection(Database databaseConnection) {
        Database = databaseConnection;
    }

    @JacksonXmlElementWrapper(localName = "Branches")
    @JacksonXmlProperty(localName = "Branch")
    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

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

    @Override
    public void destroy() {
        logger.info("stopping the aplication please note it down");
    }
}
