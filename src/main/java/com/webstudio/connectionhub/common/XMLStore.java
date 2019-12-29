package com.webstudio.connectionhub.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.webstudio.connectionhub.models.Entity.IEntity;
import com.webstudio.connectionhub.models.Entity.IFile;
import com.webstudio.connectionhub.models.IXMLBase;
import com.webstudio.connectionhub.models.UI.IForm;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class XMLStore {
    private static XMLStore xmlStore;
    private HashMap<String, IXMLBase> stringIXMLBaseHashMap;
    private XMLWorker xmlWorker = XMLWorker.getInstance();
    private HashMap<String, String> EntityModelMappings;

    public static XMLStore getInstance() {
        if (xmlStore == null)
            xmlStore = new XMLStore();
        return xmlStore;
    }

    public void SaveXml(IXMLBase ixmlBase, String name) {
        stringIXMLBaseHashMap.put(name, ixmlBase);
        if (ixmlBase instanceof IEntity) {
            IEntity entity = (IEntity) ixmlBase;
            EntityModelMappings.put(entity.getModelName(), entity.getName());
        }

    }

    public IXMLBase GetXml(String name) {
        return stringIXMLBaseHashMap.get(name);
    }

    public void LoadXML(List<IFile> files, String XMLPath) throws IOException {
        stringIXMLBaseHashMap = new HashMap<>();
        EntityModelMappings = new HashMap<>();
        for (IFile file : files) {
            String xmlString = FileHelper.ReadCompleteFile(XMLPath + file.getPath());
            IXMLBase value = xmlWorker.getXMLObjectFromString(xmlString);
            if (value instanceof IEntity) {
                IEntity entity = (IEntity) value;
                stringIXMLBaseHashMap.put(entity.getName(), value);
                EntityModelMappings.put(entity.getModelName(), entity.getName());
            } else if (value instanceof IForm) {
                IForm form = (IForm) value;
                stringIXMLBaseHashMap.put(form.getId(), value);
            }
        }
    }


    public String getXMLString(IXMLBase ixmlBase) throws JsonProcessingException {
        return xmlWorker.getXMLString(ixmlBase);
    }

    public IXMLBase getXMLObjectFromString(String xml) throws IOException {
        return xmlWorker.getXMLObjectFromString(xml);
    }

    public String getEntityNameByModelName(String modelName) {
        String EntityName = "";
        if (EntityModelMappings.containsKey(modelName)) {
            return EntityModelMappings.get(modelName);
        }
        return EntityName;
    }

    public List<String> getEntitiesByQuery(String query) {
        return stringIXMLBaseHashMap.values().stream()
                .filter(ixmlBase -> ixmlBase instanceof IEntity && (query.isEmpty() || ((IEntity) ixmlBase).getName().toLowerCase().contains(query.toLowerCase()))).map(ixmlBase -> ((IEntity) ixmlBase).getName())
                .collect(Collectors.toList());

    }
}
