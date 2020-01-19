package com.business.utils.models.UI;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(name = "Caption", value = ICaption.class),
        @JsonSubTypes.Type(name = "Checkbox", value = ICheckBox.class),
        @JsonSubTypes.Type(name = "Input", value = IInput.class),
        @JsonSubTypes.Type(name = "Label", value = ILabel.class),
        @JsonSubTypes.Type(name = "Button", value = IButton.class),
        @JsonSubTypes.Type(name = "StackLayout", value = IStackLayout.class),
        @JsonSubTypes.Type(name = "GridLayout", value = IGridLayout.class),
        @JsonSubTypes.Type(name = "ScrollView", value = IScrollView.class),
})
public class IView {
    @JacksonXmlProperty(isAttribute = true)
    String width;
    @JacksonXmlProperty(isAttribute = true)
    int minWidth;
    @JacksonXmlProperty(isAttribute = true)
    int minHeight;
    @JacksonXmlProperty(isAttribute = true)
    String visibility;
    @JacksonXmlProperty(isAttribute = true)
    int marginTop;
    @JacksonXmlProperty(isAttribute = true)
    int marginRight;
    @JacksonXmlProperty(isAttribute = true)
    int marginBottom;
    @JacksonXmlProperty(isAttribute = true)
    int marginLeft;
    @JacksonXmlProperty(isAttribute = true)
    int paddingTop;
    @JacksonXmlProperty(isAttribute = true)
    int paddingRight;
    @JacksonXmlProperty(isAttribute = true)
    int paddingBottom;
    @JacksonXmlProperty(isAttribute = true)
    int paddingLeft;
    @JacksonXmlProperty(isAttribute = true)
    String height;
    @JacksonXmlProperty(isAttribute = true)
    String backgroundColor;
    @JacksonXmlProperty(isAttribute = true)
    String horizontalAlignment;
    @JacksonXmlProperty(isAttribute = true)
    String verticalAlignment;
    @JacksonXmlProperty(isAttribute = true)
    String row;
    @JacksonXmlProperty(isAttribute = true)
    String col;
    @JacksonXmlProperty(isAttribute = true)
    int rowSpan;
    @JacksonXmlProperty(isAttribute = true)
    int colSpan;

    public IView() {
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getHorizontalAlignment() {
        return horizontalAlignment;
    }

    public void setHorizontalAlignment(String horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }

    public String getVerticalAlignment() {
        return verticalAlignment;
    }

    public void setVerticalAlignment(String verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public int getRowSpan() {
        return rowSpan;
    }

    public void setRowSpan(int rowSpan) {
        this.rowSpan = rowSpan;
    }

    public int getColSpan() {
        return colSpan;
    }

    public void setColSpan(int colSpan) {
        this.colSpan = colSpan;
    }

    public int getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(int minWidth) {
        this.minWidth = minWidth;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(int minHeight) {
        this.minHeight = minHeight;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(int marginTop) {
        this.marginTop = marginTop;
    }

    public int getMarginRight() {
        return marginRight;
    }

    public void setMarginRight(int marginRight) {
        this.marginRight = marginRight;
    }

    public int getMarginBottom() {
        return marginBottom;
    }

    public void setMarginBottom(int marginBottom) {
        this.marginBottom = marginBottom;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
    }

    public int getPaddingTop() {
        return paddingTop;
    }

    public void setPaddingTop(int paddingTop) {
        this.paddingTop = paddingTop;
    }

    public int getPaddingRight() {
        return paddingRight;
    }

    public void setPaddingRight(int paddingRight) {
        this.paddingRight = paddingRight;
    }

    public int getPaddingBottom() {
        return paddingBottom;
    }

    public void setPaddingBottom(int paddingBottom) {
        this.paddingBottom = paddingBottom;
    }

    public int getPaddingLeft() {
        return paddingLeft;
    }

    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
    }
}
