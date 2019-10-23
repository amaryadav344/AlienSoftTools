package com.webstudio.connectionhub;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.webstudio.connectionhub.Models.IColumn;
import com.webstudio.connectionhub.Models.IEntity;
import com.webstudio.connectionhub.Models.IFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@RestController
public class XMLController {
    @Autowired
    SystemSettingsService systemSettingsService;
    @Autowired
    TableRepository tableRepository;

    @RequestMapping(value = "/xml/jstoxml", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getXmlFromObject(@RequestBody IEntity entity) throws IOException {
        ObjectMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String xml = xmlMapper.writeValueAsString(entity);
        return new ResponseEntity<>(xml, HttpStatus.OK);
    }

    @RequestMapping(value = "/xml/save", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> saveXml(@RequestBody IEntity entity, @RequestParam("path") String path) throws IOException {
        ObjectMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String xml = xmlMapper.writeValueAsString(entity);
        File file = new File(systemSettingsService.getXmlBasePath() + path);
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(xml);
        out.close();
        return new ResponseEntity<>(xml, HttpStatus.OK);
    }

    @RequestMapping(value = "/xml/xmltojs", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IEntity> getObjectFromXMl(@RequestBody String xml) throws IOException {

        XmlMapper xmlMapper = new XmlMapper();
        IEntity value = xmlMapper.readValue(xml, IEntity.class);
        return new ResponseEntity<IEntity>(value, HttpStatus.OK);
    }

    @RequestMapping(value = "/xml/getxml", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IEntity> getXml(@RequestBody IFile file) throws IOException {
        File xmlFile = new File(systemSettingsService.getXmlBasePath() + file.getPath());
        FileInputStream fis = new FileInputStream(xmlFile);
        byte[] data = new byte[(int) xmlFile.length()];
        fis.read(data);
        fis.close();
        String xmlString = new String(data, StandardCharsets.UTF_8);
        XmlMapper xmlMapper = new XmlMapper();
        IEntity value = xmlMapper.readValue(xmlString, IEntity.class);
        return new ResponseEntity<IEntity>(value, HttpStatus.OK);
    }

    @RequestMapping(value = "/xml/getMatchingFolders", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String[]> getMatchingFolders(@RequestBody String query) {
        List<String> folders = new ArrayList<>();
        ListAllfolders(systemSettingsService.getXmlBasePath(), folders, "", query);
        return new ResponseEntity<>(folders.toArray(new String[folders.size()]), HttpStatus.OK);
    }

    @RequestMapping(value = "/xml/getMatchingTableNames", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String[]> getMatchingTableNames(@RequestBody String query) {
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
    public ResponseEntity CreateNewXml(@RequestBody IEntity entity, @RequestParam("path") String path) throws IOException {
        ObjectMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String xml = xmlMapper.writeValueAsString(entity);
        File file = new File(systemSettingsService.getXmlBasePath() + path + "/" + entity.getName() + ".ent.xml");
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(xml);
        out.close();
        file.createNewFile();
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/xml/GetFiles", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IFile[]> GetFiles() throws IOException {
        List<IFile> files = new ArrayList<>();
        ListAllFiles(systemSettingsService.getXmlBasePath(), systemSettingsService.getXmlBasePath(), files);
        return new ResponseEntity<>(files.toArray(new IFile[files.size()]), HttpStatus.OK);
    }

    public void ListAllFiles(String directory, String replaceChar, List<IFile> files) {
        File file = new File(directory);
        file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file, String s) {
                File current = new File(file, s);
                if (current.isDirectory()) {
                    ListAllFiles(current.getAbsolutePath(), replaceChar, files);
                } else {
                    if (current.getName().endsWith(".ent.xml")) {
                        IFile iFile = new IFile();
                        iFile.setName(current.getName());
                        iFile.setPath(current.getAbsolutePath().replace(replaceChar, ""));
                        files.add(iFile);
                    }
                }
                return false;
            }
        });

    }

    public void ListAllfolders(String BasePath, List<String> folders, String prefix, String query) {
        File file = new File(BasePath);
        file.list(new FilenameFilter() {
            @Override
            public boolean accept(File file1, String s) {
                File current = new File(file1, s);
                boolean isFolder = current.isDirectory();
                if (isFolder) {
                    ListAllfolders(current.getAbsolutePath(), folders, "/" + current.getName() + "/", query);
                    if (current.getAbsolutePath().toLowerCase().contains(query.toLowerCase()) || Objects.equals(query, " ")) {
                        folders.add(prefix + current.getName());
                    }
                }
                return isFolder;
            }
        });

    }

    public static boolean validateXMLSchema(String xml) {

        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File("resource/Entity.xsd"));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new StringReader(xml)));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
        return true;
    }
}
