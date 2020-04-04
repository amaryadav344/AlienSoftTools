package com.webstudio.connectionhub.common;


import com.business.utils.FileHelper;
import com.business.utils.models.Entity.IFile;

import java.util.List;

public class FileStore {
    private static FileStore fileStore;
    private List<IFile> files;
    private List<String> folders;

    public static FileStore getInstance() {
        if (fileStore == null) {
            fileStore = new FileStore();
        }
        return fileStore;
    }

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
