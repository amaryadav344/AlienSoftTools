package com.webstudio.connectionhub.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.webstudio.connectionhub.models.IEntity;
import com.webstudio.connectionhub.models.IFile;
import com.webstudio.connectionhub.models.IProject;
import com.webstudio.connectionhub.models.IXMLBase;

import java.io.IOException;
import java.util.List;

public class ProjectStore {
    private static ProjectStore mProjectStore;
    private XMLStore xmlStore = XMLStore.getInstance();
    private FileStore fileStore = FileStore.getInstance();
    private SymbolProvider symbolProvider = SymbolProvider.getInstance();
    private IProject iProject;

    public static ProjectStore getInstance() {
        if (mProjectStore == null)
            mProjectStore = new ProjectStore();
        return mProjectStore;
    }

    public void LoadProject(IProject iProject) throws IOException {
        this.iProject = iProject;
        symbolProvider.LoadJar(iProject.getBinPath() + "/BusinessObjects.jar");
        fileStore.LoadFilesAndFolders(iProject.getXMLPath());
        xmlStore.LoadXML(fileStore.getFiles(), iProject.getXMLPath());
    }


    public String SaveXml(IXMLBase ixmlBase, String Path) throws IOException {
        String xml = "";
        if (ixmlBase instanceof IEntity) {
            IEntity entity = (IEntity) ixmlBase;
            xmlStore.SaveXml(ixmlBase, entity.getName());
            xml = xmlStore.getXMLString(entity);
            FileHelper.WriteFile(iProject.getXMLPath() + Path, xml);
        }
        return xml;
    }

    public void CreateEntity(IXMLBase ixmlBase, String path, boolean createModel) throws IOException {
        if (ixmlBase instanceof IEntity) {
            IEntity entity = (IEntity) ixmlBase;
            String xml = xmlStore.getXMLString(entity);
            FileHelper.CreateAndWriteFile(iProject.getXMLPath() + path + "/" + entity.getName() + ".ent.xml", xml);
            if (createModel) {
                ModelHelper.createModel(path, entity,
                        iProject.getPackageName() + "." + path.replace("/", "."),
                        iProject.getBusinessModelPath());
            }
            xmlStore.SaveXml(ixmlBase, entity.getName());
        }
    }

    public List<String> GetSymbols(IFile file, String query) throws IOException {
        String xmlString = FileHelper.ReadCompleteFile(iProject.getXMLPath() + file.getPath());
        IEntity value = (IEntity) xmlStore.getXMLObjectFromString(xmlString);
        String ClassPath = iProject.getPackageName() + "." + file.getPath().replace("\\" + file.getName(), "") + "." + value.getModelName();
        List<String> symbols = symbolProvider.getMatchingSymbols(ClassPath, query);
        return symbols;
    }

    public List<String> GetObjectSymbols(IFile file, String query) throws IOException {
        String xmlString = FileHelper.ReadCompleteFile(iProject.getXMLPath() + file.getPath());
        IEntity value = (IEntity) xmlStore.getXMLObjectFromString(xmlString);
        String ClassPath = iProject.getPackageName() + "." + file.getPath().replace("\\" + file.getName(), "") + "." + value.getModelName();
        List<String> symbols = symbolProvider.getObjectSymbols(ClassPath, query);
        for (String symbol : symbols) {
            String ModelName = xmlStore.getEntityNameByModelName(symbol);
        }
        return symbols;
    }

    public IXMLBase GetXml(IFile file) {
        return xmlStore.GetXml(file.getName());
    }

    public List<IFile> getFiles() {
        return fileStore.getFiles();
    }


    public String getXMLString(IXMLBase ixmlBase) throws JsonProcessingException {
        return xmlStore.getXMLString(ixmlBase);
    }

    public IXMLBase getXMLObjectFromString(String xml) throws IOException {
        return xmlStore.getXMLObjectFromString(xml);
    }
}
