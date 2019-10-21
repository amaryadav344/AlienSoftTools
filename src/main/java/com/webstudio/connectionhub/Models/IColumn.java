package com.webstudio.connectionhub.Models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class IColumn {
    @JacksonXmlProperty(isAttribute = true)
    String name;
    @JacksonXmlProperty(isAttribute = true)
    String objectField;
    @JacksonXmlProperty(isAttribute = true)
    String dataType;
    @JacksonXmlProperty(isAttribute = true)
    String maxLength;
    @JacksonXmlProperty(isAttribute = true)
    String canBeNull;


    public IColumn(String name, String dataType, String objectField) {
        this.name = name;
        this.dataType = dataType;
        this.objectField = objectField;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(String maxLength) {
        this.maxLength = maxLength;
    }

    public String getCanBeNull() {
        return canBeNull;
    }

    public void setCanBeNull(String canBeNull) {
        this.canBeNull = canBeNull;
    }

    public IColumn() {

    }

    public IColumn(String name, String objectField, String dataType, String maxLength, String canBeNull) {
        this.name = name;
        this.objectField = objectField;
        this.dataType = dataType;
        this.maxLength = maxLength;
        this.canBeNull = canBeNull;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getObjectField() {
        return objectField;
    }

    public void setObjectField(String objectField) {
        this.objectField = objectField;
    }
}
