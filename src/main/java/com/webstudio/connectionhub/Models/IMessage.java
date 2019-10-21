package com.webstudio.connectionhub.Models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class IMessage {
    @JacksonXmlProperty(isAttribute = true)
    String message;
    @JacksonXmlProperty(isAttribute = true)
    int messageId;
    @JacksonXmlElementWrapper(localName = "parameters")
    @JacksonXmlProperty(localName = "parameter")
    IMessageParameter[] parameters;

    public IMessage(String message, int messageId, IMessageParameter[] parameters) {
        this.message = message;
        this.messageId = messageId;
        this.parameters = parameters;
    }

    public IMessage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public IMessageParameter[] getParameters() {
        return parameters;
    }

    public void setParameters(IMessageParameter[] parameters) {
        this.parameters = parameters;
    }
}
