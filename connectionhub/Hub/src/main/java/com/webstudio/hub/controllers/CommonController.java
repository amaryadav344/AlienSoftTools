package com.webstudio.hub.controllers;

import com.business.utils.FileHelper;
import com.business.utils.models.Entity.IAttribute;
import com.business.utils.models.Entity.IDBConnectionInfo;
import com.webstudio.hub.common.Constants;
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
@RequestMapping(Constants.CommonRequestMapping.COMMON)
public class CommonController {
    private AppConfig appConfig;
    private Branch CurrentBranch;
    private DBMetaDataRepository DBMetaDataRepository;


    @RequestMapping(value = Constants.CommonRequestMapping.GET_MATCHING_TABLE_NAMES, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String[]> getMatchingTableNames(@RequestBody(required = false) String query) {
        query = query == null ? Constants.Common.EMPTY_STRING : query;
        List<String> tables = DBMetaDataRepository.getAllTables(query);
        return new ResponseEntity<>(tables.toArray(new String[0]), HttpStatus.OK);
    }

    @RequestMapping(value = Constants.CommonRequestMapping.IS_TABLE_PRESENT, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> isTablePresent(@RequestBody String tableName) {
        return new ResponseEntity<>(DBMetaDataRepository.getIsTablePresent(tableName), HttpStatus.OK);
    }

    @RequestMapping(value = Constants.CommonRequestMapping.GET_COLUMNS, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IAttribute[]> GetColumns(@RequestBody String tableName) {
        List<IAttribute> columns = DBMetaDataRepository.getColumns(tableName);
        return new ResponseEntity<>(columns.toArray(new IAttribute[0]), HttpStatus.OK);
    }

    @RequestMapping(value = Constants.CommonRequestMapping.GET_DB_CONNECTION_INFO, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IDBConnectionInfo> GetDBConnection() {
        IDBConnectionInfo idbConnectionInfo = new IDBConnectionInfo();
        idbConnectionInfo.setDBUrl(appConfig.getBusinessConfig().getDatabaseConnection().getDatabaseUrl());
        idbConnectionInfo.setDBUserName(appConfig.getBusinessConfig().getDatabaseConnection().getDatabaseUsername());
        return new ResponseEntity<>(idbConnectionInfo, HttpStatus.OK);
    }

    @RequestMapping(value = Constants.CommonRequestMapping.GET_MATCHING_FOLDERS, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String[]> getMatchingFolders(@RequestBody(required = false) String query) {
        query = query == null ? Constants.Common.EMPTY_STRING : query;
        List<String> folders = FileHelper.ListAllFolders(CurrentBranch.getXMLPath(), Constants.Common.EMPTY_STRING, query);
        return new ResponseEntity<>(folders.toArray(new String[0]), HttpStatus.OK);
    }

    @Autowired
    public void setDBMetaDataRepository(com.webstudio.hub.repositories.DBMetaDataRepository DBMetaDataRepository) {
        this.DBMetaDataRepository = DBMetaDataRepository;
    }

    @Autowired
    public void setAppConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Autowired
    @Qualifier(Constants.ApplicationBeans.DEFAULT_BRANCH)
    public void setCurrentBranch(Branch currentBranch) {
        CurrentBranch = currentBranch;
    }
}
