package com.business.utils.models.UI;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class IGridLayout extends IView {
    @JsonProperty("ID")
    @JacksonXmlProperty(localName = "ID", isAttribute = true)
    String id;
    @JacksonXmlProperty(isAttribute = true)
    String columns;
    @JacksonXmlProperty(isAttribute = true)
    String rows;
    String type;
    IView[] controls;

    public IGridLayout() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    @JsonIgnore()
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public IView[] getControls() {
        return controls;
    }

    public void setControls(IView[] controls) {
        this.controls = controls;
    }
}
