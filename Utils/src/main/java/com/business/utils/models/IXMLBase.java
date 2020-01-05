package com.business.utils.models;

import com.business.utils.models.Entity.IEntity;
import com.business.utils.models.UI.IForm;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(name = "entity", value = IEntity.class),
        @JsonSubTypes.Type(name = "form", value = IForm.class)})
public class IXMLBase {
}
