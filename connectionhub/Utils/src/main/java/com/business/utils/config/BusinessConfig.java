package com.business.utils.config;

import com.business.utils.models.common.Config;
import com.business.utils.models.common.Database;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@JacksonXmlRootElement(localName = "BusinessConfig")
public class BusinessConfig {
    private Database Database;
    private List<Config> Configs;
    private HashMap<String, String> ConfigMap;

    @JacksonXmlElementWrapper(localName = "Database")
    public Database getDatabaseConnection() {
        return Database;
    }

    public void setDatabaseConnection(Database databaseConnection) {
        Database = databaseConnection;
    }

    @JacksonXmlElementWrapper(localName = "Configs")
    @JacksonXmlProperty(localName = "Config")
    private List<Config> getConfigs() {
        return Configs;
    }

    public void setConfigs(List<Config> branches) {
        this.Configs = branches;
        this.ConfigMap = new HashMap<>();
        this.ConfigMap = (HashMap<String, String>) Configs.stream().collect(Collectors.toMap(Config::getKey, Config::getValue));

    }

    public String getConfigValue(String Key) {
        return this.ConfigMap.get(Key);
    }


}
