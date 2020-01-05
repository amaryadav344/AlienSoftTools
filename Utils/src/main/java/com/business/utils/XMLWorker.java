package com.business.utils;

import com.business.utils.models.IXMLBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;

public class XMLWorker {
    private static XMLWorker mXmlWorker;
    private XmlMapper xmlMapper;

    private XMLWorker() {
        xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        xmlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    }

    public static XMLWorker getInstance() {
        if (mXmlWorker == null)
            mXmlWorker = new XMLWorker();
        return mXmlWorker;
    }

    public String getXMLString(IXMLBase ixmlBase) throws JsonProcessingException {
        return xmlMapper.writeValueAsString(ixmlBase);
    }

    public IXMLBase getXMLObjectFromString(String xml) throws IOException {
        return xmlMapper.readValue(xml, IXMLBase.class);
    }


}
