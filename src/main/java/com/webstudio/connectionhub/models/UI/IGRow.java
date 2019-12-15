package com.webstudio.connectionhub.models.UI;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class IGRow {
    @JacksonXmlProperty(localName = "Column")
    @JacksonXmlElementWrapper(useWrapping = false)
    IGColumn[] columns;


    IGRow(IGColumn[] columns) {
        this.columns = columns;
    }

    public IGRow() {
    }

    public IGColumn[] getColumns() {
        return columns;
    }

    public void setColumns(IGColumn[] columns) {
        this.columns = columns;
    }
}
