package com.webstudio.hub.common;

import com.webstudio.hub.models.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.logging.Logger;

@Component
public class ProjectStore {
    private XMLStore xmlStore;
    private FileStore fileStore;
    private SymbolProvider symbolProvider;
    private Branch DefaultBranch;
    private Logger logger = Logger.getLogger(getClass().getName());

    public void LoadProject(Branch branch) throws IOException {
        symbolProvider.LoadJar(branch.getSourcePath());
        fileStore.LoadFilesAndFolders(branch.getXMLPath());
        xmlStore.LoadXML(fileStore.getFiles());
    }

    @PostConstruct
    public void LoadDefaultProject() throws IOException {
        logger.info("Loading Default Branch " + DefaultBranch.getName());
        LoadProject(DefaultBranch);
    }


    /*public List<String> GetSymbols(IFile file, String query) throws IOException {
        String xmlString = FileHelper.ReadCompleteFile(file.getPath());
        IEntity value = (IEntity) xmlStore.getXMLObjectFromString(xmlString);
        String ClassPath = GetFullyQualifiedModelName(file, value.getModelName());
        List<String> symbols = symbolProvider.getMatchingSymbols(ClassPath, query);
        return symbols;
    }*/


    @Autowired
    public void setXmlStore(XMLStore xmlStore) {
        this.xmlStore = xmlStore;
    }

    @Autowired
    public void setFileStore(FileStore fileStore) {
        this.fileStore = fileStore;
    }

    @Autowired
    public void setSymbolProvider(SymbolProvider symbolProvider) {
        this.symbolProvider = symbolProvider;
    }

    @Autowired
    @Qualifier(Constants.ApplicationBeans.DEFAULT_BRANCH)
    public void setDefaultBranch(Branch defaultBranch) {
        DefaultBranch = defaultBranch;
    }

}
