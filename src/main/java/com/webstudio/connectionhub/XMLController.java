package com.webstudio.connectionhub;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.squareup.javapoet.*;
import com.webstudio.connectionhub.Models.IColumn;
import com.webstudio.connectionhub.Models.IEntity;
import com.webstudio.connectionhub.Models.IFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;
import spoon.JarLauncher;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtFieldReference;
import spoon.reflect.reference.CtTypeReference;

import javax.lang.model.element.Modifier;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


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
    public ResponseEntity CreateNewXml(@RequestBody IEntity entity, @RequestParam("path") String path, @RequestParam("createModel") boolean createModel) throws IOException {
        ObjectMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String xml = xmlMapper.writeValueAsString(entity);
        File file = new File(systemSettingsService.getXmlBasePath() + path + "/" + entity.getName() + ".ent.xml");
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(xml);
        out.close();
        file.createNewFile();
        if (createModel) {
            createModel(path, entity);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    private void createModel(String path, IEntity entity) throws IOException {
        String cdoClassName = "cdo" + entity.getName();
        String doClassName = "do" + entity.getName();
        String modelClassName = entity.getName();
        String PackageName = systemSettingsService.getPackageName() + "." + path.replace("/", ".");
        TypeSpec.Builder modelClassBuilder = TypeSpec.classBuilder(modelClassName);
        TypeSpec.Builder cdoClassBuilder = TypeSpec.classBuilder(cdoClassName);
        TypeSpec.Builder doClassBuilder = TypeSpec.classBuilder(doClassName)
                .addModifiers(Modifier.PUBLIC);
        for (IColumn column : entity.getColumns()) {
            if (column.getDataType().toLowerCase().equals("string")) {
                FieldSpec fieldSpec = FieldSpec.builder(ClassName.get(String.class), column.getName(), Modifier.PUBLIC).build();
                doClassBuilder.addField(fieldSpec);
                MethodSpec methodSpecGetter = MethodSpec.methodBuilder("get" + column.getName())
                        .addModifiers(Modifier.PUBLIC)
                        .returns(String.class)
                        .addStatement("return $L", column.getName())
                        .build();
                doClassBuilder.addMethod(methodSpecGetter);
                MethodSpec methodSpecSetter = MethodSpec.methodBuilder("set" + column.getName())
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(ParameterSpec.builder(String.class, column.getName()).build())
                        .addStatement("this.$L=$L", column.getName(), column.getName())
                        .build();
                doClassBuilder.addMethod(methodSpecSetter);
            }
            if (column.getDataType().toLowerCase().equals("integer")) {
                FieldSpec fieldSpec = FieldSpec.builder(ClassName.get(Integer.class), column.getName(), Modifier.PUBLIC).build();
                doClassBuilder.addField(fieldSpec);

                MethodSpec methodSpecGetter = MethodSpec.methodBuilder("get" + column.getName())
                        .addModifiers(Modifier.PUBLIC)
                        .returns(Integer.class)
                        .addStatement("return $L", column.getName())
                        .build();
                doClassBuilder.addMethod(methodSpecGetter);
                MethodSpec methodSpecSetter = MethodSpec.methodBuilder("set" + column.getName())
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(ParameterSpec.builder(Integer.class, column.getName()).build())
                        .addStatement("this.$L=$L", column.getName(), column.getName())
                        .build();
                doClassBuilder.addMethod(methodSpecSetter);
            }
            if (column.getDataType().toLowerCase().equals("datetime")) {
                FieldSpec fieldSpec = FieldSpec.builder(ClassName.get(Date.class), column.getName(), Modifier.PUBLIC).build();
                doClassBuilder.addField(fieldSpec);

                MethodSpec methodSpecGetter = MethodSpec.methodBuilder("get" + column.getName())
                        .addModifiers(Modifier.PUBLIC)
                        .returns(Date.class)
                        .addStatement("return $L", column.getName())
                        .build();
                doClassBuilder.addMethod(methodSpecGetter);
                MethodSpec methodSpecSetter = MethodSpec.methodBuilder("set" + column.getName())
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(ParameterSpec.builder(Date.class, column.getName()).build())
                        .addStatement("this.$L=$L", column.getName(), column.getName())
                        .build();
                doClassBuilder.addMethod(methodSpecSetter);
            }
        }
        cdoClassBuilder.superclass(ClassName.get(PackageName, doClassName));
        modelClassBuilder.addField(ClassName.get(PackageName, cdoClassName), "i" + cdoClassName, Modifier.PUBLIC);
        JavaFile javaFiledo = JavaFile.builder(PackageName, doClassBuilder.build())
                .build();
        JavaFile javfilecdo = JavaFile.builder(PackageName, cdoClassBuilder.build())
                .build();
        JavaFile javfilemodel = JavaFile.builder(PackageName, modelClassBuilder.build())
                .build();
        File file = new File(systemSettingsService.getBusinessModelPath());
        javaFiledo.writeTo(file);
        javfilecdo.writeTo(file);
        javfilemodel.writeTo(file);

    }

    @RequestMapping(value = "/xml/getSymbols", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String[]> getSymbols(@RequestBody IFile file, @RequestParam("model_name") String ModelName, @RequestParam(name = "query", required = false) String query) {
        String ClassPath = systemSettingsService.getPackageName() + "." + file.getPath().replace(".ent.xml", "").replace("\\", ".");
        JarLauncher launcher = new JarLauncher(systemSettingsService.getBinPath() + "/BusinessObjects.jar");
        launcher.buildModel();
        Factory factory = launcher.getFactory();
        CtTypeReference<?> type = factory.Type().get(ClassPath).getReference();
        String[] symobols = query.split("\\.");
        CtTypeReference<?> LastType = type;
        String lastQuery = "";
        String FullName = "";
        for (String symbol : symobols) {
            if (type.getDeclaredField(symbol) != null) {
                LastType = type.getDeclaredField(symbol).getType();
                FullName = FullName + symbol + ".";
            } else {
                lastQuery = symbol;
            }
        }
        List<String> symbols = new ArrayList<>();
        ListSymbols(symbols, LastType, lastQuery, FullName);
        return new ResponseEntity<>(symbols.toArray(new String[symbols.size()]), HttpStatus.OK);
    }

    public void ListSymbols(List<String> symbols, CtTypeReference<?> type, String query, String FullName) {
        Collection<CtFieldReference<?>> fieldReferences = type.getAllFields();
        for (CtFieldReference<?> fieldReference : fieldReferences) {
            if (fieldReference.getSimpleName().toLowerCase().contains(query.toLowerCase())) {
                symbols.add(fieldReference.getSimpleName());
            }

        }
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
                    ListAllfolders(current.getAbsolutePath(), folders, current.getName() + "/", query);
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
