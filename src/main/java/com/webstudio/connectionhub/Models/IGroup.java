package com.webstudio.connectionhub.Models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class IGroup {
    @JacksonXmlProperty(isAttribute = true)
    String name;
    @JacksonXmlElementWrapper(localName = "rules")
    @JacksonXmlProperty(localName = "rule")
    IRule rules[];

    public IGroup() {
    }

    public IGroup(String name, IRule[] rules) {
        this.name = name;
        this.rules = rules;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IRule[] getRules() {
        return rules;
    }

    public void setRules(IRule[] rules) {
        this.rules = rules;
    }
}
