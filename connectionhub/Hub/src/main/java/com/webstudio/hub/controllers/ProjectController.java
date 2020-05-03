package com.webstudio.hub.controllers;

import com.webstudio.hub.common.Constants;
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
@RequestMapping(Constants.ProjectRequestMapping.PROJECT)
public class ProjectController {
    private Branch CurrentBranch;
    private ProjectStore projectStore;


    @RequestMapping(value = Constants.ProjectRequestMapping.LOAD_PROJECT, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity LoadProject() throws IOException {
        projectStore.LoadProject(CurrentBranch);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = Constants.ProjectRequestMapping.REFRESH_METADATA, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity RefreshMetaData() throws IOException {
        projectStore.LoadProject(CurrentBranch);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Autowired
    @Qualifier(Constants.ApplicationBeans.DEFAULT_BRANCH)
    public void setCurrentBranch(Branch currentBranch) {
        CurrentBranch = currentBranch;
    }

    @Autowired
    public void setProjectStore(ProjectStore projectStore) {
        this.projectStore = projectStore;
    }
}
