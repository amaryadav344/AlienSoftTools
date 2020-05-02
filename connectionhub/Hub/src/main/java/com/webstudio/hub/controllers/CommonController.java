package com.webstudio.hub.controllers;

import com.business.utils.FileHelper;
import com.business.utils.models.Entity.IColumn;
import com.business.utils.models.Entity.IDBConnectionInfo;
import com.webstudio.hub.config.AppConfig;
import com.webstudio.hub.models.Branch;
import com.webstudio.hub.repositories.DBMetaDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("Common")
public class CommonController {
    @Autowired
    AppConfig appConfig;
    @Autowired
    @Qualifier("DefaultBranch")
    Branch CurrentBranch;
    private DBMetaDataRepository DBMetaDataRepository;


    @RequestMapping(value = "/getMatchingTableNames", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String[]> getMatchingTableNames(@RequestBody(required = false) String query) {
        query = query == null ? "" : query;
        List<String> tables = DBMetaDataRepository.getAllTables(query);
        return new ResponseEntity<>(tables.toArray(new String[0]), HttpStatus.OK);
    }

    @RequestMapping(value = "/isTablePresent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> isTablePresent(@RequestBody String tableName) {
        return new ResponseEntity<>(DBMetaDataRepository.getIsTablePresent(tableName), HttpStatus.OK);
    }

    @RequestMapping(value = "/getColumns", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IColumn[]> GetColumns(@RequestBody String tableName) {
        List<IColumn> columns = DBMetaDataRepository.getColumns(tableName);
        return new ResponseEntity<>(columns.toArray(new IColumn[0]), HttpStatus.OK);
    }

    @RequestMapping(value = "/GetDBConnectionInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IDBConnectionInfo> GetDBConnection() {
        IDBConnectionInfo idbConnectionInfo = new IDBConnectionInfo();
        idbConnectionInfo.setDBUrl(appConfig.getBusinessConfig().getDatabaseConnection().getDatabaseUrl());
        idbConnectionInfo.setDBUserName(appConfig.getBusinessConfig().getDatabaseConnection().getDatabaseUsername());
        return new ResponseEntity<>(idbConnectionInfo, HttpStatus.OK);
    }

    @RequestMapping(value = "/getMatchingFolders", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String[]> getMatchingFolders(@RequestBody(required = false) String query) {
        query = query == null ? "" : query;
        List<String> folders = FileHelper.ListAllFolders(CurrentBranch.getXMLPath(), "", query);
        return new ResponseEntity<>(folders.toArray(new String[folders.size()]), HttpStatus.OK);
    }

    @Autowired
    public void setDBMetaDataRepository(com.webstudio.hub.repositories.DBMetaDataRepository DBMetaDataRepository) {
        this.DBMetaDataRepository = DBMetaDataRepository;
    }
}
