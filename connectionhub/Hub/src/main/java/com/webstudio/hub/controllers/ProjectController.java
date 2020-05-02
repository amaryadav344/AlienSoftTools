package com.webstudio.hub.controllers;

import com.webstudio.hub.common.ProjectStore;
import com.webstudio.hub.models.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("Project")
public class ProjectController {
    @Autowired
    @Qualifier("DefaultBranch")
    Branch CurrentBranch;
    @Autowired
    ProjectStore projectStore;


    @RequestMapping(value = "/LoadProject", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity LoadProject() throws IOException {
        projectStore.LoadProject(CurrentBranch);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/RefreshMetaData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity RefreshMetaData() throws IOException {
        projectStore.LoadProject(CurrentBranch);
        return new ResponseEntity(HttpStatus.OK);
    }
}
