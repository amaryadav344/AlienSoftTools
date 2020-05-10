package com.business.utils.models.Entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class IAttribute {
    @JacksonXmlProperty(isAttribute = true)
    String name;
    @JacksonXmlProperty(isAttribute = true)
    String objectField;
    @JacksonXmlProperty(isAttribute = true)
    String dataType;
    @JacksonXmlProperty(isAttribute = true)
    String entity;
    @JacksonXmlProperty(isAttribute = true)
    boolean isPrimaryKey;
    @JacksonXmlProperty(isAttribute = true)
    String type;

    public String getName() {
        return name;
    }

    public IAttribute() {
    }

    public IAttribute(String name, String objectField, String dataType, String entity, boolean isPrimaryKey, String type) {
        this.name = name;
        this.objectField = objectField;
        this.dataType = dataType;
        this.entity = entity;
        this.isPrimaryKey = isPrimaryKey;
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjectField() {
        return objectField;
    }

    public void setObjectField(String objectField) {
        this.objectField = objectField;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getIsPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }
}

