package com.webstudio.connectionhub.models;

public class DatabaseConnection {
    String DatabaseUrl;
    String DatabaseUsername;
    String DatabasePassword;
    String DatabaseDriver;

    public DatabaseConnection() {
    }

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
