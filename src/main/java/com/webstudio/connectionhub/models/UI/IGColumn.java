package com.webstudio.connectionhub.models.UI;

public class IGColumn {
    int span;

    IView[] controls;


    public IGColumn() {
    }


    public int getSpan() {
        return span;
    }

    public void setSpan(int span) {
        this.span = span;
    }

    public IView[] getControls() {
        return controls;
    }

    public void setControls(IView[] controls) {
        this.controls = controls;
    }
}
