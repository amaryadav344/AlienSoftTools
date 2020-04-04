package com.business.utils.models.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IDBConnectionInfo {
    @JsonProperty("DBUrl")
    String DBUrl;
    @JsonProperty("DBUserName")
    String DBUserName;

    public IDBConnectionInfo() {
    }

    @JsonIgnore
    public String getDBUrl() {
        return DBUrl;
    }

    public void setDBUrl(String DBUrl) {
        this.DBUrl = DBUrl;
    }

    @JsonIgnore
    public String getDBUserName() {
        return DBUserName;
    }

    public void setDBUserName(String DBUserName) {
        this.DBUserName = DBUserName;
    }
}
