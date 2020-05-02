package com.webstudio.hub.controllers;


import com.business.utils.FileHelper;
import com.business.utils.models.Entity.*;
import com.business.utils.models.IXMLBase;
import com.business.utils.models.UI.NavigationParameter;
import com.webstudio.hub.common.XMLStore;
import com.webstudio.hub.models.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@RequestMapping("XML")
public class XMLController {
    @Autowired
    @Qualifier("DefaultBranch")
    Branch CurrentBranch;
    @Autowired
    XMLStore xmlStore;

    @RequestMapping(value = "/jstoxml", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getXmlFromObject(@RequestBody IXMLBase entity) throws IOException {
        String xml = xmlStore.getXMLString(entity);
        return new ResponseEntity<>(xml, HttpStatus.OK);
    }

    @RequestMapping(value = "/xmltojs", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IXMLBase> getObjectFromXMl(@RequestBody String xml) throws IOException {
        IXMLBase value = xmlStore.getXMLObjectFromString(xml);
        return new ResponseEntity<>(value, HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> saveXml(@RequestBody IXMLBase entity, @RequestParam("path") String path) throws IOException {
        String xml = xmlStore.Save(entity, path);
        return new ResponseEntity<>(xml, HttpStatus.OK);
    }

    @RequestMapping(value = "/getxml", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IXMLBase> getXml(@RequestBody IFile file) {
        IXMLBase value = xmlStore.GetXml(file);
        return new ResponseEntity<>(value, HttpStatus.OK);
    }


    @RequestMapping(value = "/CreateNewXml/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> CreateNewXml(@RequestBody IXMLBase ixmlBase, @RequestParam("path") String path, @RequestParam("createModel") boolean createModel) throws IOException {
        String Path = xmlStore.CreateEntity(ixmlBase, path, createModel, CurrentBranch);
        return new ResponseEntity<>(Path, HttpStatus.OK);
    }


    @RequestMapping(value = "/getEntityFields", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
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
            if (Last.getCollections() != null) {
                if (Arrays.stream(Last.getCollections()).anyMatch(collection -> collection.getName().equals(field))) {
                    ICollection collection = Arrays.stream(Last.getCollections()).filter(col -> col.getName().equals(field)).findFirst().get();
                    Last = (IEntity) getXml(new IFile("", 0, collection.getEntity())).getBody();
                }
            }
            if (Last != null) {
                if (Last.getColumns() != null) {
                    if (Arrays.stream(Last.getColumns()).anyMatch(iColumn -> iColumn.getName().equals(field))) {
                        Last = null;
                    } else {
                        if (Last.getProperties() != null) {
                            if (Arrays.stream(Last.getProperties()).anyMatch(iProperty -> iProperty.getName().equals(field))) {
                                Last = null;
                            }
                        }
                    }

                }
            }
        }
        if (Last != null) {
            if (Last.getColumns() != null)
                Rfields.addAll(Arrays.stream(Last.getColumns()).map(iColumn -> new ISymbol(iColumn.getName(), 0, "", "")).collect(Collectors.toList()));
            if (Last.getObjects() != null)
                Rfields.addAll(Arrays.stream(Last.getObjects()).map(iColumn -> new ISymbol(iColumn.getName(), 0, "", "")).collect(Collectors.toList()));
            if (Last.getProperties() != null)
                Rfields.addAll(Arrays.stream(Last.getProperties()).map(iColumn -> new ISymbol(iColumn.getName(), 0, "", "")).collect(Collectors.toList()));
            if (Last.getCollections() != null)
                Rfields.addAll(Arrays.stream(Last.getCollections()).map(iColumn -> new ISymbol(iColumn.getName(), 0, "", "")).collect(Collectors.toList()));
        }
        return new ResponseEntity<>(Rfields.toArray(new ISymbol[Rfields.size()]), HttpStatus.OK);

    }


    @RequestMapping(value = "/GetForms", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String[]> GetForms(@RequestBody(required = false) String query) {
        if (query == null) {
            query = "";
        }
        List<String> files = xmlStore.getFormsByQuery(query);
        return new ResponseEntity<>(files.toArray(new String[files.size()]), HttpStatus.OK);
    }

    @RequestMapping(value = "/GetNavigationParameterByForm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NavigationParameter[]> GetNavigationParameterByForm(@RequestBody(required = false) String form) {
        List<NavigationParameter> navigationParameters = xmlStore.GetNavigationParameterByForm(form);
        return new ResponseEntity<>(navigationParameters.toArray(new NavigationParameter[navigationParameters.size()]), HttpStatus.OK);
    }


    @RequestMapping(value = "/ListEntities", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String[]> ListEntities(@RequestBody(required = false) String query) {
        if (query == null) {
            query = "";
        }
        List<String> entities = xmlStore.getEntitiesByQuery(query);
        return new ResponseEntity<>(entities.toArray(new String[entities.size()]), HttpStatus.OK);
    }

}
