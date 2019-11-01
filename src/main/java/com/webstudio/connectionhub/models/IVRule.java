package com.webstudio.connectionhub.models;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class IVRule {
    @JacksonXmlProperty(isAttribute = true)
    String name;
    @JacksonXmlProperty(isAttribute = true)
    String expression;
    @JacksonXmlProperty(isAttribute = true)
    boolean isObjectRule;
    IMessage message;

    public IVRule(String name, String expression, boolean isObjectRule, IMessage message) {
        this.name = name;
        this.expression = expression;
        this.isObjectRule = isObjectRule;
        this.message = message;
    }

    public IVRule() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public boolean getIsObjectRule() {
        return isObjectRule;
    }

    public void setObjectRule(boolean objectRule) {
        isObjectRule = objectRule;
    }

    public IMessage getMessage() {
        return message;
    }

    public void setMessage(IMessage message) {
        this.message = message;
    }
}
