package com.business.utils.models.Entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ILoadParameter {
    @JacksonXmlProperty(isAttribute = true)
    String name;
    @JacksonXmlProperty(isAttribute = true)
    String entityField;

    public ILoadParameter() {
    }

    public ILoadParameter(String name, String entityField) {
        this.name = name;
        this.entityField = entityField;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntityField() {
        return entityField;
    }

    public void setEntityField(String entityField) {
        this.entityField = entityField;
    }
}
