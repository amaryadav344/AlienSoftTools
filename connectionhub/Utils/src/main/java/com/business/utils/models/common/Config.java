package com.business.utils.models.common;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Config {
    private String Key;
    private String Value;

    @JacksonXmlProperty(isAttribute = true,localName = "key")
    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }
    @JacksonXmlProperty(isAttribute = true,localName = "value")
    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}
