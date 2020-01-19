package com.business.utils.models.UI;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ILabel extends IView {
    @JsonProperty("ID")
    @JacksonXmlProperty(localName = "ID", isAttribute = true)
    String id;
    String type;
    @JacksonXmlProperty(isAttribute = true)
    int letterSpacing;
    @JacksonXmlProperty(isAttribute = true)
    int lineHeight;
    @JacksonXmlProperty(isAttribute = true)
    String text;
    @JacksonXmlProperty(isAttribute = true)
    String textAlignment;
    @JacksonXmlProperty(isAttribute = true)
    String textDecoration;
    @JacksonXmlProperty(isAttribute = true)
    String textTransform;
    @JacksonXmlProperty(isAttribute = true)
    boolean textWrap;
    @JacksonXmlProperty(isAttribute = true)
    String whiteSpace;
    @JacksonXmlProperty(isAttribute = true)
    String entityField;

    public ILabel() {
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


    public int getLetterSpacing() {
        return letterSpacing;
    }


    public void setLetterSpacing(int letterSpacing) {
        this.letterSpacing = letterSpacing;
    }


    public int getLineHeight() {
        return lineHeight;
    }


    public void setLineHeight(int lineHeight) {
        this.lineHeight = lineHeight;
    }


    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }


    public String getTextAlignment() {
        return textAlignment;
    }


    public void setTextAlignment(String textAlignment) {
        this.textAlignment = textAlignment;
    }


    public String getTextDecoration() {
        return textDecoration;
    }


    public void setTextDecoration(String textDecoration) {
        this.textDecoration = textDecoration;
    }


    public String getTextTransform() {
        return textTransform;
    }


    public void setTextTransform(String textTransform) {
        this.textTransform = textTransform;
    }


    public boolean isTextWrap() {
        return textWrap;
    }


    public void setTextWrap(boolean textWrap) {
        this.textWrap = textWrap;
    }


    public String getWhiteSpace() {
        return whiteSpace;
    }


    public void setWhiteSpace(String whiteSpace) {
        this.whiteSpace = whiteSpace;
    }


    public String getEntityField() {
        return entityField;
    }


    public void setEntityField(String entityField) {
        this.entityField = entityField;
    }
}
