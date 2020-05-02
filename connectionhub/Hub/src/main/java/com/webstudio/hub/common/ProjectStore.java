package com.webstudio.hub.common;

import com.business.utils.FileHelper;
import com.business.utils.models.Entity.IEntity;
import com.business.utils.models.Entity.IFile;
import com.business.utils.models.Entity.IObjectMethod;
import com.business.utils.models.Entity.ISymbol;
import com.business.utils.models.IXMLBase;
import com.business.utils.models.UI.IForm;
import com.business.utils.models.UI.NavigationParameter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.webstudio.hub.models.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@Component
public class ProjectStore {
    @Autowired
    private XMLStore xmlStore;
    @Autowired
    FileStore fileStore;
    @Autowired
    private SymbolProvider symbolProvider;
    @Autowired
    @Qualifier("DefaultBranch")
    Branch DefaultBranch;
    private Logger logger = Logger.getLogger(getClass().getName());

    public void LoadProject(Branch branch) throws IOException {
        symbolProvider.LoadJar(branch.getBusinessObject().get(0));
        fileStore.LoadFilesAndFolders(branch.getXMLPath());
        xmlStore.LoadXML(fileStore.getFiles());
    }

    @PostConstruct
    public void LoadDefaultProject() throws IOException {
        logger.info("Loading Default Branch " + DefaultBranch.getName());
        LoadProject(DefaultBranch);
    }




    public String CreateEntity(IXMLBase ixmlBase, String path, boolean createModel) throws IOException {
        String Path = "";
        if (ixmlBase instanceof IEntity) {
            IEntity entity = (IEntity) ixmlBase;
            String xml = xmlStore.getXMLString(entity);
            Path = DefaultBranch.getXMLPath() + "\\" + path + "\\" + entity.getName() + ".ent.xml";
            FileHelper.CreateAndWriteFile(Path, xml);
            if (createModel) {
                ModelHelper.createModel(entity, DefaultBranch.getPackageName(),
                        DefaultBranch.getSourcePath(), path);
            }
            xmlStore.SaveXml(ixmlBase, entity.getName());
        } else if (ixmlBase instanceof IForm) {
            IForm form = (IForm) ixmlBase;
            String xml = xmlStore.getXMLString(form);
            Path = DefaultBranch.getXMLPath() + "\\" + path + "\\" + form.getName() + ".form.xml";
            FileHelper.CreateAndWriteFile(Path, xml);
            xmlStore.SaveXml(ixmlBase, form.getName());
        }
        return Path;
    }

    /*public List<String> GetSymbols(IFile file, String query) throws IOException {
        String xmlString = FileHelper.ReadCompleteFile(file.getPath());
        IEntity value = (IEntity) xmlStore.getXMLObjectFromString(xmlString);
        String ClassPath = GetFullyQualifiedModelName(file, value.getModelName());
        List<String> symbols = symbolProvider.getMatchingSymbols(ClassPath, query);
        return symbols;
    }*/



    public List<String> getForms(String query) {
        return xmlStore.getFormsByQuery(query);
    }


    public String getXMLString(IXMLBase ixmlBase) throws JsonProcessingException {
        return xmlStore.getXMLString(ixmlBase);
    }

    public IXMLBase getXMLObjectFromString(String xml) throws IOException {
        return xmlStore.getXMLObjectFromString(xml);
    }




    public List<NavigationParameter> GetNavigationParameterByForm(String form) {
        return xmlStore.GetNavigationParameterByForm(form);
    }

}
