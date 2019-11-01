package com.webstudio.connectionhub.common;

import com.webstudio.connectionhub.models.IFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {
    public static void WriteFile(String PathToFile, String Value) throws IOException {
        File file = new File(PathToFile);
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(Value);
        out.close();
    }

    public static void CreateAndWriteFile(String PathToFile, String Value) throws IOException {
        File file = new File(PathToFile);
        file.createNewFile();
        WriteFile(PathToFile, Value);
    }

    public static String ReadCompleteFile(String PathToFile) throws IOException {
        File file = new File(PathToFile);
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();
        return new String(data, StandardCharsets.UTF_8);
    }

    public static List<IFile> ListAllFiles(String directory, String replaceChar) {
        List<IFile> files = new ArrayList<>();
        ListF(directory, replaceChar, files);
        return files;
    }

    public static List<String> ListAllFolders(String targetFolderPath, String prefix, String query) {
        List<String> folders = new ArrayList<>();
        ListFD(targetFolderPath, folders, prefix, query);
        return folders;
    }

    public static void ListFD(String targetFolderPath, List<String> folders, String prefix, String query) {
        File file = new File(targetFolderPath);
        file.list((file1, s) -> {
            File current = new File(file1, s);
            boolean isFolder = current.isDirectory();
            if (isFolder) {
                ListFD(current.getAbsolutePath(), folders, current.getName() + "/", query);
                if (current.getAbsolutePath().toLowerCase().contains(query.toLowerCase()) || query.isEmpty()) {
                    folders.add(prefix + current.getName());
                }
            }
            return isFolder;
        });
    }

    private static void ListF(String directory, String replaceChar, List<IFile> files) {
        File file = new File(directory);
        file.listFiles((file1, s) -> {
            File current = new File(file1, s);
            if (current.isDirectory()) {
                ListF(current.getAbsolutePath(), replaceChar, files);
            } else {
                if (current.getName().endsWith(".ent.xml")) {
                    IFile iFile = new IFile();
                    iFile.setName(current.getName());
                    iFile.setPath(current.getAbsolutePath().replace(replaceChar, ""));
                    files.add(iFile);
                }
            }
            return false;
        });
    }
}
