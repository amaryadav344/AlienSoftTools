package com.webstudio.hub.controllers;

import com.business.utils.models.Entity.IFile;
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
@RequestMapping("File")
public class FileController {
    @Autowired
    FileStore fileStore;

    @RequestMapping(value = "/GetFiles", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IFile[]> GetFiles() {
        List<IFile> files = fileStore.getFiles();
        return new ResponseEntity<>(files.toArray(new IFile[files.size()]), HttpStatus.OK);
    }

}
