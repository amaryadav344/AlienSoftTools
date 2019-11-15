package com.webstudio.connectionhub.controllers;


import com.webstudio.connectionhub.common.FileHelper;
import com.webstudio.connectionhub.common.ProjectStore;
import com.webstudio.connectionhub.models.*;
import com.webstudio.connectionhub.repositories.AppConfigRepository;
import com.webstudio.connectionhub.repositories.TableRepository;
import com.webstudio.connectionhub.services.SystemSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


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
    public ResponseEntity<IXMLBase> getXml(@RequestBody IFile file) throws IOException {
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
    public ResponseEntity<String[]> getSymbols(@RequestBody IFile file, @RequestParam(name = "query", required = false) String query) throws IOException {
        List<String> symbols = projectStore.GetObjectSymbols(file, query);
        return new ResponseEntity<>(symbols.toArray(new String[symbols.size()]), HttpStatus.OK);
    }

    @RequestMapping(value = "/xml/GetFiles", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IFile[]> GetFiles() {
        List<IFile> files = projectStore.getFiles();
        return new ResponseEntity<>(files.toArray(new IFile[files.size()]), HttpStatus.OK);
    }

    @RequestMapping(value = "/xml/GetDBConnectionInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IDBConnectionInfo> GetDBConnection() throws IOException {
        AppConfigRepository appConfigRepository = AppConfigRepository.getInstance();
        IDBConnectionInfo idbConnectionInfo = new IDBConnectionInfo();
        idbConnectionInfo.setDBUrl(appConfigRepository.getAppConfig("databaseurl"));
        idbConnectionInfo.setDBUserName(appConfigRepository.getAppConfig("databaseusername"));
        return new ResponseEntity<>(idbConnectionInfo, HttpStatus.OK);
    }


    @RequestMapping(value = "/xml/LoadProject", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity LoadProject() throws IOException {
        IProject iProject = new IProject();
        iProject.setBinPath(systemSettingsService.getBinPath());
        iProject.setXMLPath(systemSettingsService.getXmlBasePath());
        iProject.setPackageName(systemSettingsService.getPackageName());
        iProject.setBusinessModelPath(systemSettingsService.getBusinessModelPath());
        projectStore.LoadProject(iProject);
        return new ResponseEntity(HttpStatus.OK);
    }


}
