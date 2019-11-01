package com.webstudio.connectionhub.controllers;


import com.webstudio.connectionhub.common.FileHelper;
import com.webstudio.connectionhub.common.ModelHelper;
import com.webstudio.connectionhub.common.SymbolProvider;
import com.webstudio.connectionhub.common.XMLWorker;
import com.webstudio.connectionhub.models.IColumn;
import com.webstudio.connectionhub.models.IEntity;
import com.webstudio.connectionhub.models.IFile;
import com.webstudio.connectionhub.models.IXMLBase;
import com.webstudio.connectionhub.repositories.TableRepository;
import com.webstudio.connectionhub.services.SystemSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;


@RestController
public class XMLController {
    @Autowired
    SystemSettingsService systemSettingsService;
    @Autowired
    TableRepository tableRepository;
    private XMLWorker xmlWorker = XMLWorker.getInstance();
    private SymbolProvider symbolProvider = SymbolProvider.getInstance();

    @RequestMapping(value = "/xml/jstoxml", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getXmlFromObject(@RequestBody IXMLBase entity) throws IOException {
        String xml = xmlWorker.getXMLString(entity);
        return new ResponseEntity<>(xml, HttpStatus.OK);
    }

    @RequestMapping(value = "/xml/xmltojs", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IXMLBase> getObjectFromXMl(@RequestBody String xml) throws IOException {
        IXMLBase value = xmlWorker.getXMLObjectFromString(xml);
        return new ResponseEntity<>(value, HttpStatus.OK);
    }

    @RequestMapping(value = "/xml/save", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> saveXml(@RequestBody IXMLBase entity, @RequestParam("path") String path) throws IOException {
        String xml = xmlWorker.getXMLString(entity);
        FileHelper.WriteFile(systemSettingsService.getXmlBasePath() + path, xml);
        return new ResponseEntity<>(xml, HttpStatus.OK);
    }

    @RequestMapping(value = "/xml/getxml", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IXMLBase> getXml(@RequestBody IFile file) throws IOException {
        String xmlString = FileHelper.ReadCompleteFile(systemSettingsService.getXmlBasePath() + file.getPath());
        IXMLBase value = xmlWorker.getXMLObjectFromString(xmlString);
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
        if (ixmlBase instanceof IEntity) {
            IEntity entity = (IEntity) ixmlBase;
            String xml = xmlWorker.getXMLString(entity);
            FileHelper.CreateAndWriteFile(systemSettingsService.getXmlBasePath() + path + "/" + entity.getName() + ".ent.xml", xml);
            if (createModel) {
                ModelHelper.createModel(path, entity,
                        systemSettingsService.getPackageName() + "." + path.replace("/", "."),
                        systemSettingsService.getBusinessModelPath());
            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = "/xml/getSymbols", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String[]> getSymbols(@RequestBody IFile file, @RequestParam(name = "query", required = false) String query) throws IOException {
        String xmlString = FileHelper.ReadCompleteFile(systemSettingsService.getXmlBasePath() + file.getPath());
        IEntity value = (IEntity) xmlWorker.getXMLObjectFromString(xmlString);
        String ClassPath = systemSettingsService.getPackageName() + "." + file.getPath().replace("\\" + file.getName(), "") + "." + value.getModelName();
        List<String> symbols = symbolProvider.getMatchingSymbols(ClassPath, query);
        return new ResponseEntity<>(symbols.toArray(new String[symbols.size()]), HttpStatus.OK);
    }

    @RequestMapping(value = "/xml/GetFiles", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IFile[]> GetFiles() {
        List<IFile> files = FileHelper.ListAllFiles(systemSettingsService.getXmlBasePath(), systemSettingsService.getXmlBasePath());
        return new ResponseEntity<>(files.toArray(new IFile[files.size()]), HttpStatus.OK);
    }

    @PostConstruct
    public void LoadSymbols() {
        symbolProvider.LoadJar(systemSettingsService.getBinPath() + "/BusinessObjects.jar");
    }

}
