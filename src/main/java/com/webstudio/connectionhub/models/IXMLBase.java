package com.webstudio.connectionhub.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.webstudio.connectionhub.models.Entity.IEntity;
import com.webstudio.connectionhub.models.UI.IForm;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(name = "entity", value = IEntity.class),
        @JsonSubTypes.Type(name = "form", value = IForm.class)})
public class IXMLBase {
}
