package com.webstudio.connectionhub.controllers;


import com.business.utils.AppConfigRepository;
import com.business.utils.FileHelper;
import com.business.utils.models.Entity.*;
import com.business.utils.models.IXMLBase;
import com.business.utils.models.UI.IForm;
import com.business.utils.models.UI.NavigationParameter;
import com.webstudio.connectionhub.common.Constants;
import com.webstudio.connectionhub.common.ProjectStore;
import com.webstudio.connectionhub.repositories.TableRepository;
import com.webstudio.connectionhub.services.SystemSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class XMLController {
    @Autowired
    SystemSettingsService systemSettingsService;
    @Autowired
    TableRepository tableRepository;
    ProjectStore projectStore = ProjectStore.getInstance();

    @RequestMapping(value = "/xml/jstoxml", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getXmlFromObject(@RequestBody IXMLBase entity) throws IOException {
        String xml = projectStore.getXMLString(entity);
        return new ResponseEntity<>(xml, HttpStatus.OK);
    }

    @RequestMapping(value = "/xml/xmltojs", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IXMLBase> getObjectFromXMl(@RequestBody String xml) throws IOException {
        IXMLBase value = projectStore.getXMLObjectFromString(xml);
        return new ResponseEntity<>(value, HttpStatus.OK);
    }

    @RequestMapping(value = "/xml/save", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> saveXml(@RequestBody IXMLBase entity, @RequestParam("path") String path) throws IOException {
        String xml = projectStore.SaveXml(entity, path);
        return new ResponseEntity<>(xml, HttpStatus.OK);
    }

    @RequestMapping(value = "/xml/getxml", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IXMLBase> getXml(@RequestBody IFile file) {
        IXMLBase value = projectStore.GetXml(file);
        return new ResponseEntity<>(value, HttpStatus.OK);
    }

    @RequestMapping(value = "/xml/getMatchingFolders", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String[]> getMatchingFolders(@RequestBody(required = false) String query) {
        query = query == null ? "" : query;
        List<String> folders = FileHelper.ListAllFolders(systemSettingsService.getXmlBasePath(), "", query);
        return new ResponseEntity<>(folders.toArray(new String[folders.size()]), HttpStatus.OK);
    }

    @RequestMapping(value = "/xml/getMatchingTableNames", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String[]> getMatchingTableNames(@RequestBody(required = false) String query) {
        query = query == null ? "" : query;
        List<String> tables = tableRepository.getAllTables(query);
        return new ResponseEntity<>(tables.toArray(new String[tables.size()]), HttpStatus.OK);
    }

    @RequestMapping(value = "/xml/isTablePresent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> isTablePresent(@RequestBody String tableName) {
        return new ResponseEntity<>(tableRepository.getIsTablePresent(tableName), HttpStatus.OK);
    }

    @RequestMapping(value = "/xml/getColumns", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IColumn[]> GetColumns(@RequestBody String tableName) {
        List<IColumn> columns = tableRepository.getColumns(tableName);
        return new ResponseEntity<>(columns.toArray(new IColumn[columns.size()]), HttpStatus.OK);
    }

    @RequestMapping(value = "/xml/CreateNewXml/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity CreateNewXml(@RequestBody IXMLBase ixmlBase, @RequestParam("path") String path, @RequestParam("createModel") boolean createModel) throws IOException {
        projectStore.CreateEntity(ixmlBase, path, createModel);
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = "/xml/getSymbols", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ISymbol[]> getSymbols(@RequestBody IFile file, @RequestParam(name = "query", required = false) String query, @RequestParam(name = "type", required = false) int SymbolType) throws IOException {
        List<ISymbol> symbols = new ArrayList<>();
        if (file.getName().endsWith(".form.xml")) {
            ResponseEntity<IXMLBase> form = getXml(file);
            IForm iForm = (IForm) form.getBody();
            String entity = iForm.getEntity() + ".ent.xml";
            file = projectStore.getFiles().stream().filter(iFile -> iFile.getName().equals(entity)).findFirst().get();
        }
        if (SymbolType == Constants.SymbolTypes.TYPE_OBJECT) {
            symbols.addAll(projectStore.GetObjectSymbols(file, query));
        } else if (SymbolType == Constants.SymbolTypes.TYPE_COLLECTION) {
            symbols.addAll(projectStore.GetObjectSymbols(file, query));
            symbols.addAll(projectStore.GetCollectionSymbols(file, query));
        } else if (SymbolType == Constants.SymbolTypes.TYPE_VARIBLE) {
            symbols.addAll(projectStore.GetObjectSymbols(file, query));
            symbols.addAll(projectStore.GetVariableSymbols(file, query));

        }
        return new ResponseEntity<>(symbols.toArray(new ISymbol[symbols.size()]), HttpStatus.OK);
    }

    @RequestMapping(value = "/xml/getEntityFields", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ISymbol[]> getEntityFields(@RequestBody String entity, @RequestParam(name = "query", required = false) String query) throws IOException {
        if (query == null) {
            query = "";
        }
        List<ISymbol> Rfields = new ArrayList<>();
        String[] fields = query.split("\\.");
        IEntity Last = (IEntity) getXml(new IFile("", 0, entity)).getBody();
        for (String field : fields) {
            if (Last.getObjects() != null) {
                if (Arrays.stream(Last.getObjects()).anyMatch(object -> object.getName().equals(field))) {
                    IObject Iobject = Arrays.stream(Last.getObjects()).filter(object -> object.getName().equals(field)).findFirst().get();
                    Last = (IEntity) getXml(new IFile("", 0, Iobject.getEntity())).getBody();
                }
            }
        }
        if (Last.getColumns() != null)
            Rfields.addAll(Arrays.stream(Last.getColumns()).map(iColumn -> new ISymbol(iColumn.getName(), 0, "", "")).collect(Collectors.toList()));
        if (Last.getObjects() != null)
            Rfields.addAll(Arrays.stream(Last.getObjects()).map(iColumn -> new ISymbol(iColumn.getName(), 0, "", "")).collect(Collectors.toList()));
        if (Last.getProperties() != null)
            Rfields.addAll(Arrays.stream(Last.getProperties()).map(iColumn -> new ISymbol(iColumn.getName(), 0, "", "")).collect(Collectors.toList()));
        if (Last.getCollections() != null)
            Rfields.addAll(Arrays.stream(Last.getCollections()).map(iColumn -> new ISymbol(iColumn.getName(), 0, "", "")).collect(Collectors.toList()));
        return new ResponseEntity<>(Rfields.toArray(new ISymbol[Rfields.size()]), HttpStatus.OK);

    }

    @RequestMapping(value = "/xml/GetObjectMethods", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IObjectMethod[]> getSymbols(@RequestBody IFile file, @RequestParam(name = "query", required = false) String query) throws IOException {
        List<IObjectMethod> symbols = new ArrayList<>();
        symbols = projectStore.ListObjectMethods(file, query);
        return new ResponseEntity<>(symbols.toArray(new IObjectMethod[symbols.size()]), HttpStatus.OK);
    }

    @RequestMapping(value = "/xml/GetFiles", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IFile[]> GetFiles() {
        List<IFile> files = projectStore.getFiles();
        return new ResponseEntity<>(files.toArray(new IFile[files.size()]), HttpStatus.OK);
    }

    @RequestMapping(value = "/xml/GetForms", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String[]> GetForms(@RequestBody(required = false) String query) {
        if (query == null) {
            query = "";
        }
        List<String> files = projectStore.getForms(query);
        return new ResponseEntity<>(files.toArray(new String[files.size()]), HttpStatus.OK);
    }

    @RequestMapping(value = "/xml/GetNavigationParameterByForm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NavigationParameter[]> GetNavigationParameterByForm(@RequestBody(required = false) String form) {
        List<NavigationParameter> navigationParameters = projectStore.GetNavigationParameterByForm(form);
        return new ResponseEntity<>(navigationParameters.toArray(new NavigationParameter[navigationParameters.size()]), HttpStatus.OK);
    }


    @RequestMapping(value = "/xml/ListEntities", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String[]> ListEntities(@RequestBody(required = false) String query) {
        if (query == null) {
            query = "";
        }
        List<String> entities = projectStore.getEntitiesByQuery(query);
        return new ResponseEntity<>(entities.toArray(new String[entities.size()]), HttpStatus.OK);
    }

    @RequestMapping(value = "/xml/GetDBConnectionInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IDBConnectionInfo> GetDBConnection() throws IOException {
        AppConfigRepository appConfigRepository = AppConfigRepository.getInstance();
        IDBConnectionInfo idbConnectionInfo = new IDBConnectionInfo();
        idbConnectionInfo.setDBUrl(appConfigRepository.getAppConfig(AppConfigRepository.DATABASE_URL));
        idbConnectionInfo.setDBUserName(appConfigRepository.getAppConfig(AppConfigRepository.DATABASE_USERNAME));
        return new ResponseEntity<>(idbConnectionInfo, HttpStatus.OK);
    }


    @RequestMapping(value = "/xml/LoadProject", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity LoadProject() throws IOException {
        AppConfigRepository appConfigRepository = AppConfigRepository.getInstance();
        IProject iProject = new IProject();
        iProject.setBinPath(appConfigRepository.getAppConfig(AppConfigRepository.BIN_RELATIVE_PATH));
        iProject.setXMLPath(appConfigRepository.getAppConfig(AppConfigRepository.XML_RELATIVE_PATH));
        iProject.setPackageName(appConfigRepository.getAppConfig(AppConfigRepository.PACKAGE_NAME));
        iProject.setBusinessModelPath(appConfigRepository.getAppConfig(AppConfigRepository.BUSINESS_MODELS_RELATIVE_PATH));
        iProject.setBasePath(appConfigRepository.getAppConfig(AppConfigRepository.BASE_DIRECTORY));
        projectStore.LoadProject(iProject);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/xml/RefreshMetaData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity RefreshMetaData() throws IOException {
        AppConfigRepository appConfigRepository = AppConfigRepository.getInstance();
        IProject iProject = new IProject();
        iProject.setBinPath(appConfigRepository.getAppConfig(AppConfigRepository.BIN_RELATIVE_PATH));
        iProject.setXMLPath(appConfigRepository.getAppConfig(AppConfigRepository.XML_RELATIVE_PATH));
        iProject.setPackageName(appConfigRepository.getAppConfig(AppConfigRepository.PACKAGE_NAME));
        iProject.setBusinessModelPath(appConfigRepository.getAppConfig(AppConfigRepository.BUSINESS_MODELS_RELATIVE_PATH));
        iProject.setBasePath(appConfigRepository.getAppConfig(AppConfigRepository.BASE_DIRECTORY));
        projectStore.LoadProject(iProject);
        return new ResponseEntity(HttpStatus.OK);
    }
}
