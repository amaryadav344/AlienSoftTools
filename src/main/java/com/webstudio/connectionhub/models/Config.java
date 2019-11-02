package com.webstudio.connectionhub.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Config {
    @JacksonXmlProperty(localName = "name")
    String name;
    @JacksonXmlProperty(localName = "value")
    String value;

    public Config() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
