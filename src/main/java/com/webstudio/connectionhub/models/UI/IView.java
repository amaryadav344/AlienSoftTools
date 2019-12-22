package com.webstudio.connectionhub.models.UI;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(name = "Caption", value = ICaption.class),
        @JsonSubTypes.Type(name = "Checkbox", value = ICheckBox.class),
        @JsonSubTypes.Type(name = "Input", value = IInput.class),
        @JsonSubTypes.Type(name = "Label", value = ILabel.class),
        @JsonSubTypes.Type(name = "Grid", value = IGrid.class),
        @JsonSubTypes.Type(name = "Row", value = IGRow.class),
        @JsonSubTypes.Type(name = "Column", value = IGColumn.class),
        @JsonSubTypes.Type(name = "Section", value = ISection.class),
        @JsonSubTypes.Type(name = "Button", value = IButton.class)
})
public class IView {

    public IView() {
    }
}
