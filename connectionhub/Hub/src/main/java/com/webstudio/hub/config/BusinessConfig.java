package com.webstudio.hub.config;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.webstudio.hub.models.Config;
import com.webstudio.hub.models.Database;

import java.util.List;
import java.util.logging.Logger;

@JacksonXmlRootElement(localName = "BusinessConfig")
public class BusinessConfig {
    private static Logger logger = Logger.getLogger(HubConfig.class.getName());
    private com.webstudio.hub.models.Database Database;
    List<Config> Configs;

    @JacksonXmlElementWrapper(localName = "Database")
    public Database getDatabaseConnection() {
        return Database;
    }

    public void setDatabaseConnection(Database databaseConnection) {
        Database = databaseConnection;
    }

    @JacksonXmlElementWrapper(localName = "Configs")
    @JacksonXmlProperty(localName = "Config")
    public List<Config> getConfigs() {
        return Configs;
    }

    public void setConfigs(List<Config> branches) {
        this.Configs = branches;
    }


}
