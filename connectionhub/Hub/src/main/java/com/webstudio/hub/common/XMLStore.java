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
import com.webstudio.hub.models.Branch;
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

    public IXMLBase GetXml(IFile file) {
        String name = "";
        if (file.getType() == 0) {
            name = file.getName().replace(".ent.xml", "");
        } else if (file.getType() == 1) {
            name = file.getName().replace(".form.xml", "");
        }
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

    public void LoadXML(List<IFile> files) throws IOException {
        stringIXMLBaseHashMap = new HashMap<>();
        EntityModelMappings = new HashMap<>();
        for (IFile file : files) {
            String xmlString = FileHelper.ReadCompleteFile(file.getPath());
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

    public String CreateEntity(IXMLBase ixmlBase, String path, boolean createModel, Branch DefaultBranch) throws IOException {
        String Path = "";
        if (ixmlBase instanceof IEntity) {
            IEntity entity = (IEntity) ixmlBase;
            String xml = getXMLString(entity);
            Path = DefaultBranch.getXMLPath() + "\\" + path + "\\" + entity.getName() + ".ent.xml";
            FileHelper.CreateAndWriteFile(Path, xml);
            if (createModel) {
                ModelHelper.createModel(entity, DefaultBranch.getPackageName(),
                        DefaultBranch.getSourcePath(), path);
            }
            SaveXml(ixmlBase, entity.getName());
        } else if (ixmlBase instanceof IForm) {
            IForm form = (IForm) ixmlBase;
            String xml = getXMLString(form);
            Path = DefaultBranch.getXMLPath() + "\\" + path + "\\" + form.getName() + ".form.xml";
            FileHelper.CreateAndWriteFile(Path, xml);
            SaveXml(ixmlBase, form.getName());
        }
        return Path;
    }

    public String Save(IXMLBase ixmlBase, String Path) throws IOException {
        String xml = "";
        if (ixmlBase instanceof IEntity) {
            IEntity entity = (IEntity) ixmlBase;
            SaveXml(ixmlBase, entity.getName());
            xml = getXMLString(entity);
            FileHelper.WriteFile(Path, xml);
        } else if (ixmlBase instanceof IForm) {
            IForm form = (IForm) ixmlBase;
            SaveXml(ixmlBase, form.getName());
            xml = getXMLString(form);
            FileHelper.WriteFile(Path, xml);
        }
        return xml;
    }

}
