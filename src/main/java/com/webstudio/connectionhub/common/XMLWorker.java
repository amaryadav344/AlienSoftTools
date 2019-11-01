package com.webstudio.connectionhub.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.webstudio.connectionhub.models.IXMLBase;

import java.io.IOException;

public class XMLWorker {
    private static XMLWorker mXmlWorker;
    private XmlMapper xmlMapper;

    private XMLWorker() {
        xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
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
