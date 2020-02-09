package com.business.utils.models.UI;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ICheckBox extends IView {
    @JsonProperty("ID")
    @JacksonXmlProperty(localName = "ID", isAttribute = true)
    String id;
    String type;

    ICheckBox(String ID) {
        this.id = ID;
    }

    public ICheckBox() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonIgnore()
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
