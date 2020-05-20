package com.webstudio.hub.common;

import com.business.utils.FileHelper;
import com.business.utils.XMLWorker;
import com.business.utils.models.Entity.IEntity;
import com.business.utils.models.Entity.IFile;
import com.business.utils.models.IXMLBase;
import com.business.utils.models.UI.IForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.webstudio.hub.models.Branch;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class XMLStore {
    private HashMap<String, IXMLBase> stringIXMLBaseHashMap;
    private XMLWorker xmlWorker = XMLWorker.getInstance();
    private HashMap<String, String> EntityModelMappings;


    public String SaveXml(IXMLBase ixmlBase, String Path) throws IOException {
        String xml = Constants.Common.EMPTY_STRING;
        if (ixmlBase instanceof IEntity) {
            IEntity entity = (IEntity) ixmlBase;
            xml = getXMLString(entity);
            FileHelper.WriteFile(Path, xml);
            stringIXMLBaseHashMap.put(entity.getName(), ixmlBase);
            EntityModelMappings.put(entity.getBusinessObject(), entity.getName());
        } else if (ixmlBase instanceof IForm) {
            IForm form = (IForm) ixmlBase;
            xml = getXMLString(form);
            FileHelper.WriteFile(Path, xml);
            stringIXMLBaseHashMap.put(form.getName(), ixmlBase);
        }
        return xml;
    }

    public IXMLBase GetXml(IFile file) {
        String name = Constants.Common.EMPTY_STRING;
        if (file.getType() == 0) {
            name = file.getName().replace(".ent.xml", Constants.Common.EMPTY_STRING);
        } else if (file.getType() == 1) {
            name = file.getName().replace(".form.xml", Constants.Common.EMPTY_STRING);
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

    public void LoadXML(List<IFile> files) throws IOException {
        stringIXMLBaseHashMap = new HashMap<>();
        EntityModelMappings = new HashMap<>();
        for (IFile file : files) {
            String xmlString = FileHelper.ReadCompleteFile(file.getPath());
            IXMLBase value = xmlWorker.getXMLObjectFromString(xmlString);
            if (value instanceof IEntity) {
                IEntity entity = (IEntity) value;
                stringIXMLBaseHashMap.put(entity.getName(), value);
                EntityModelMappings.put(entity.getBusinessObject(), entity.getName());
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
        String EntityName = Constants.Common.EMPTY_STRING;
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
        String TargetPath = Constants.Common.EMPTY_STRING;
        if (ixmlBase instanceof IEntity) {
            IEntity entity = (IEntity) ixmlBase;
            String xml = getXMLString(entity);
            File file = new File(DefaultBranch.getXMLPath() + Constants.Common.FOLDER_SEPARATOR + path);
            if (!file.exists()) {
                file.mkdir();
            }
            TargetPath = DefaultBranch.getXMLPath() + Constants.Common.FOLDER_SEPARATOR + path + Constants.Common.FOLDER_SEPARATOR + entity.getName() + ".ent.xml";
            FileHelper.CreateAndWriteFile(TargetPath, xml);
            if (createModel) {
                ModelHelper.createModel(entity, DefaultBranch, path);
            }
            SaveXml(ixmlBase, entity.getName());
        } else if (ixmlBase instanceof IForm) {
            IForm form = (IForm) ixmlBase;
            String xml = getXMLString(form);
            TargetPath = DefaultBranch.getXMLPath() + Constants.Common.FOLDER_SEPARATOR + path + Constants.Common.FOLDER_SEPARATOR + form.getName() + ".form.xml";
            FileHelper.CreateAndWriteFile(TargetPath, xml);
            SaveXml(ixmlBase, form.getName());
        }
        return TargetPath;
    }

}
