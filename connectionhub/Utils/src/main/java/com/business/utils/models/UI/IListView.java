package com.business.utils.models.UI;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class IListView extends IView {
    IView control;
    String type;
    @JacksonXmlProperty(isAttribute = true)
    String entityField;

    public IListView() {
    }

    public IView getControl() {
        return control;
    }

    public void setControl(IView control) {
        this.control = control;
    }

    public String getType() {
        return type;
    }

    @JsonIgnore()
    public void setType(String type) {
        this.type = type;
    }

    public String getEntityField() {
        return entityField;
    }

    public void setEntityField(String entityField) {
        this.entityField = entityField;
    }
}
