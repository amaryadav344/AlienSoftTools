package com.webstudio.hub.controllers;

import com.business.utils.models.Entity.IFile;
import com.webstudio.hub.common.Constants;
import com.webstudio.hub.common.FileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Constants.FileRequestMapping.FILE)
public class FileController {
    private FileStore fileStore;

    @RequestMapping(value = Constants.FileRequestMapping.GET_FILES, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IFile[]> GetFiles() {
        List<IFile> files = fileStore.getFiles();
        return new ResponseEntity<>(files.toArray(new IFile[0]), HttpStatus.OK);
    }

    @Autowired
    public void setFileStore(FileStore fileStore) {
        this.fileStore = fileStore;
    }
}
