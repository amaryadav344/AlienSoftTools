package com.webstudio.hub.common;

import com.business.utils.FileHelper;
import com.business.utils.XMLWorker;
import com.business.utils.models.Entity.IEntity;
import com.business.utils.models.Entity.IFile;
import com.business.utils.models.Entity.ILoadMapping;
import com.business.utils.models.Entity.ILoadParameter;
import com.business.utils.models.IXMLBase;
import com.business.utils.models.UI.IForm;
import com.business.utils.models.UI.NavigationParameter;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class XMLStore {
    private HashMap<String, IXMLBase> stringIXMLBaseHashMap;
    private XMLWorker xmlWorker = XMLWorker.getInstance();
    private HashMap<String, String> EntityModelMappings;


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

    public List<String> getFormsByQuery(String query) {
        return stringIXMLBaseHashMap.values().stream()
                .filter(ixmlBase -> ixmlBase instanceof IForm && (query.isEmpty() || ((IForm) ixmlBase)
                        .getName()
                        .toLowerCase()
                        .contains(query.toLowerCase())))
                .map(ixmlBase -> ((IForm) ixmlBase).getName())
                .collect(Collectors.toList());
    }

    public List<NavigationParameter> GetNavigationParameterByForm(String form) {
        List<NavigationParameter> navigationParameters = new ArrayList<>();
        IForm form1 = (IForm) stringIXMLBaseHashMap.values().stream()
                .filter(ixmlBase -> ixmlBase instanceof IForm && (((IForm) ixmlBase)
                        .getName()
                        .toLowerCase()
                        .equals(form.toLowerCase()))).findFirst().get();
        for (ILoadMapping loadMapping : form1.getLoadMethod().getLoadMapping()) {
            for (ILoadParameter loadParameter : loadMapping.getLoadParameters()) {
                NavigationParameter navigationParameter = new NavigationParameter();
                navigationParameter.setName(loadParameter.getName());
                navigationParameter.setValue(loadParameter.getEntityField());
                navigationParameters.add(navigationParameter);
            }
        }
        return navigationParameters;
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
                stringIXMLBaseHashMap.put(form.getName(), value);
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
