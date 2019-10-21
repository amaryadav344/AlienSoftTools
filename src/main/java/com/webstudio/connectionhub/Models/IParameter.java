package com.webstudio.connectionhub.Models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class IParameter {
    @JacksonXmlProperty(isAttribute = true)
    String name;
    @JacksonXmlProperty(isAttribute = true)
    String dataType;

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

    public IParameter() {
    }
}
