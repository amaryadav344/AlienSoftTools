package com.business.utils.models.UI;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class IStackLayout extends IView {
    @JsonProperty("ID")
    @JacksonXmlProperty(localName = "ID", isAttribute = true)
    String id;
    String type;
    @JacksonXmlProperty(isAttribute = true)
    String orientation;
    IView[] controls;

    public IStackLayout() {
    }

    public IView[] getControls() {
        return controls;
    }

    public void setControls(IView[] controls) {
        this.controls = controls;
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

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }
}
