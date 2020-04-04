package com.business.utils.models.Entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class IQuery {
    @JacksonXmlProperty(isAttribute = true)
    String sql;
    @JacksonXmlProperty(isAttribute = true)
    String name;
    @JacksonXmlProperty(isAttribute = true)
    String queryType;
    @JacksonXmlElementWrapper(localName = "parameters")
    @JacksonXmlProperty(localName = "parameter")
    IParameter[] parameters;
    @JacksonXmlElementWrapper(localName = "customMappings")
    @JacksonXmlProperty(localName = "customMapping")
    ICustomMap[] customMaps;

    public IQuery() {
    }

    public IQuery(String sql, String name, IParameter[] parameters, ICustomMap[] customMaps, String queryType) {
        this.sql = sql;
        this.name = name;
        this.parameters = parameters;
        this.customMaps = customMaps;
        this.queryType = queryType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getName() {
        return name;
    }

    public void setName(String label) {
        this.name = label;
    }

    public IParameter[] getParameters() {
        return parameters;
    }

    public void setParameters(IParameter[] parameters) {
        this.parameters = parameters;
    }

    public ICustomMap[] getCustomMaps() {
        return customMaps;
    }

    public void setCustomMaps(ICustomMap[] customMaps) {
        this.customMaps = customMaps;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }
}
