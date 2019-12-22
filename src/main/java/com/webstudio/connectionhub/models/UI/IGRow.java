package com.webstudio.connectionhub.models.UI;


public class IGRow {
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
