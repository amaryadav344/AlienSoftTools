package com.webstudio.connectionhub.models.UI;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.webstudio.connectionhub.models.IXMLBase;


@JacksonXmlRootElement(localName = "form")
public class IForm extends IXMLBase {
    @JsonProperty("ID")
    @JacksonXmlProperty(localName = "ID", isAttribute = true)
    String id;
    @JacksonXmlProperty(isAttribute = true)
    String entity;
    String type;
    IView control;

    public IForm() {
    }

    @JsonIgnore()
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public IView getControl() {
        return control;
    }

    public void setControl(IView control) {
        this.control = control;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }
}
