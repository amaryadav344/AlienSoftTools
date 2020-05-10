package com.webstudio.hub.controllers;


import com.business.utils.models.Entity.*;
import com.business.utils.models.IXMLBase;
import com.business.utils.models.UI.NavigationParameter;
import com.webstudio.hub.common.Constants;
import com.webstudio.hub.common.ProjectStore;
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
@RequestMapping(Constants.XMLRequestMapping.XML)
public class XMLController {

    private Branch CurrentBranch;
    private XMLStore xmlStore;
    private ProjectStore projectStore;

    @RequestMapping(value = Constants.XMLRequestMapping.JS_TO_XML, method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)

    public ResponseEntity<String> getXmlFromObject(@RequestBody IXMLBase entity) throws IOException {
        String xml = xmlStore.getXMLString(entity);
        return new ResponseEntity<>(xml, HttpStatus.OK);
    }

    @RequestMapping(value = Constants.XMLRequestMapping.XML_TO_JS, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IXMLBase> getObjectFromXMl(@RequestBody String xml) throws IOException {
        IXMLBase value = xmlStore.getXMLObjectFromString(xml);
        return new ResponseEntity<>(value, HttpStatus.OK);
    }

    @RequestMapping(value = Constants.XMLRequestMapping.SAVE, method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> saveXml(@RequestBody IXMLBase entity, @RequestParam("path") String path) throws IOException {
        String xml = xmlStore.SaveXml(entity, path);
        return new ResponseEntity<>(xml, HttpStatus.OK);
    }

    @RequestMapping(value = Constants.XMLRequestMapping.GET_XML, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IXMLBase> getXml(@RequestBody IFile file) {
        IXMLBase value = xmlStore.GetXml(file);
        return new ResponseEntity<>(value, HttpStatus.OK);
    }


    @RequestMapping(value = Constants.XMLRequestMapping.CREATE_NEW_XML, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> CreateNewXml(@RequestBody IXMLBase ixmlBase, @RequestParam("path") String path, @RequestParam("createModel") boolean createModel) throws IOException {
        String Path = xmlStore.CreateEntity(ixmlBase, path, createModel, CurrentBranch);
        projectStore.LoadProject(CurrentBranch);
        return new ResponseEntity<>(Path, HttpStatus.OK);
    }


   /* @RequestMapping(value = Constants.XMLRequestMapping.GET_ENTITY_FIELDS, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ISymbol[]> getEntityFields(@RequestBody String entity, @RequestParam(name = "query", required = false) String query) throws IOException {
        if (query == null) {
            query = Constants.Common.EMPTY_STRING;
        }
        List<ISymbol> Rfields = new ArrayList<>();
        String[] fields = query.split("\\.");
        IEntity Last = (IEntity) getXml(new IFile(Constants.Common.EMPTY_STRING, 0, entity)).getBody();
        for (String field : fields) {
            assert Last != null;
            if (Last.getObjects() != null) {
                if (Arrays.stream(Last.getObjects()).anyMatch(object -> object.getName().equals(field))) {
                    IObject Iobject = Arrays.stream(Last.getObjects()).filter(object -> object.getName().equals(field)).findFirst().get();
                    Last = (IEntity) getXml(new IFile(Constants.Common.EMPTY_STRING, 0, Iobject.getEntity())).getBody();
                }
            }
            assert Last != null;
            if (Last.getCollections() != null) {
                if (Arrays.stream(Last.getCollections()).anyMatch(collection -> collection.getName().equals(field))) {
                    ICollection collection = Arrays.stream(Last.getCollections()).filter(col -> col.getName().equals(field)).findFirst().get();
                    Last = (IEntity) getXml(new IFile(Constants.Common.EMPTY_STRING, 0, collection.getEntity())).getBody();
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
                Rfields.addAll(Arrays.stream(Last.getColumns()).map(iColumn -> new ISymbol(iColumn.getName(), 0, Constants.Common.EMPTY_STRING, Constants.Common.EMPTY_STRING)).collect(Collectors.toList()));
            if (Last.getObjects() != null)
                Rfields.addAll(Arrays.stream(Last.getObjects()).map(iColumn -> new ISymbol(iColumn.getName(), 0, Constants.Common.EMPTY_STRING, Constants.Common.EMPTY_STRING)).collect(Collectors.toList()));
            if (Last.getProperties() != null)
                Rfields.addAll(Arrays.stream(Last.getProperties()).map(iColumn -> new ISymbol(iColumn.getName(), 0, Constants.Common.EMPTY_STRING, Constants.Common.EMPTY_STRING)).collect(Collectors.toList()));
            if (Last.getCollections() != null)
                Rfields.addAll(Arrays.stream(Last.getCollections()).map(iColumn -> new ISymbol(iColumn.getName(), 0, Constants.Common.EMPTY_STRING, Constants.Common.EMPTY_STRING)).collect(Collectors.toList()));
        }
        return new ResponseEntity<>(Rfields.toArray(new ISymbol[0]), HttpStatus.OK);

    }*/


    @RequestMapping(value = Constants.XMLRequestMapping.GET_FORMS, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String[]> GetForms(@RequestBody(required = false) String query) {
        if (query == null) {
            query = Constants.Common.EMPTY_STRING;
        }
        List<String> files = xmlStore.getFormsByQuery(query);
        return new ResponseEntity<>(files.toArray(new String[0]), HttpStatus.OK);
    }

    @RequestMapping(value = Constants.XMLRequestMapping.GET_NAVIGATION_PARAMETER_BY_FORM, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NavigationParameter[]> GetNavigationParameterByForm(@RequestBody(required = false) String form) {
        List<NavigationParameter> navigationParameters = xmlStore.GetNavigationParameterByForm(form);
        return new ResponseEntity<>(navigationParameters.toArray(new NavigationParameter[0]), HttpStatus.OK);
    }


    @RequestMapping(value = Constants.XMLRequestMapping.LIST_ENTITIES, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String[]> ListEntities(@RequestBody(required = false) String query) {
        if (query == null) {
            query = Constants.Common.EMPTY_STRING;
        }
        List<String> entities = xmlStore.getEntitiesByQuery(query);
        return new ResponseEntity<>(entities.toArray(new String[0]), HttpStatus.OK);
    }

    @Autowired
    @Qualifier(Constants.ApplicationBeans.DEFAULT_BRANCH)
    public void setCurrentBranch(Branch currentBranch) {
        CurrentBranch = currentBranch;
    }

    @Autowired
    public void setXmlStore(XMLStore xmlStore) {
        this.xmlStore = xmlStore;
    }

    @Autowired
    public void setProjectStore(ProjectStore projectStore) {
        this.projectStore = projectStore;
    }
}
