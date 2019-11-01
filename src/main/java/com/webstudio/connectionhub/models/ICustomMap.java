package com.webstudio.connectionhub.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ICustomMap {
    @JacksonXmlProperty(isAttribute = true)
    String column;
    @JacksonXmlProperty(isAttribute = true)
    String objectField;

    public ICustomMap() {
    }

    public ICustomMap(String column, String objectField) {
        this.column = column;
        this.objectField = objectField;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getObjectField() {
        return objectField;
    }

    public void setObjectField(String objectField) {
        this.objectField = objectField;
    }
}
