package com.webstudio.connectionhub.Models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class IRule {
    @JacksonXmlProperty(isAttribute = true)
    String name;

    public IRule() {
    }

    public IRule(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
