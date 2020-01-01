package com.webstudio.connectionhub.models.UI;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.webstudio.connectionhub.models.Entity.ICustomMethod;
import com.webstudio.connectionhub.models.IXMLBase;


@JacksonXmlRootElement(localName = "form")
public class IForm extends IXMLBase {
    @JacksonXmlProperty(isAttribute = true)
    String name;
    @JacksonXmlProperty(isAttribute = true)
    String entity;
    String type;
    ICustomMethod loadMethod;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public ICustomMethod getLoadMethod() {
        return loadMethod;
    }

    public void setLoadMethod(ICustomMethod loadMethod) {
        this.loadMethod = loadMethod;
    }
}
