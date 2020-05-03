package com.webstudio.hub.common;


import com.business.utils.FileHelper;
import com.business.utils.models.Entity.IFile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FileStore {
    private List<IFile> files;


    void LoadFilesAndFolders(String path) {
        files = FileHelper.ListAllFiles(path);
    }

    public List<IFile> getFiles() {
        return files;
    }

}
