package com.business.utils.models.common;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Database {
    @JacksonXmlProperty(localName = "DatabaseUrl")
    private String DatabaseUrl;
    @JacksonXmlProperty(localName = "DatabaseUsername")
    private String DatabaseUsername;
    @JacksonXmlProperty(localName = "DatabasePassword")
    private String DatabasePassword;
    @JacksonXmlProperty(localName = "DatabaseDriver")
    private String DatabaseDriver;


    public String getDatabaseUrl() {
        return DatabaseUrl;
    }

    public void setDatabaseUrl(String databaseUrl) {
        DatabaseUrl = databaseUrl;
    }

    public String getDatabaseUsername() {
        return DatabaseUsername;
    }

    public void setDatabaseUsername(String databaseUsername) {
        DatabaseUsername = databaseUsername;
    }

    public String getDatabasePassword() {
        return DatabasePassword;
    }

    public void setDatabasePassword(String databasePassword) {
        DatabasePassword = databasePassword;
    }

    public String getDatabaseDriver() {
        return DatabaseDriver;
    }

    public void setDatabaseDriver(String databaseDriver) {
        DatabaseDriver = databaseDriver;
    }
}
