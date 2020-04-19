package com.webstudio.hub.common;


import com.business.utils.FileHelper;
import com.business.utils.models.Entity.IFile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FileStore {
    private List<IFile> files;
    private List<String> folders;


    public void LoadFilesAndFolders(String path) {
        files = FileHelper.ListAllFiles(path, path);
        folders = FileHelper.ListAllFolders(path, "", "");
    }

    public List<IFile> getFiles() {
        return files;
    }

    public List<String> getFolders() {
        return folders;
    }
}
