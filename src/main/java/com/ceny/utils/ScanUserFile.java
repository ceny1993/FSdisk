package com.ceny.utils;

import com.ceny.app.UserFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chensongkui on 2017/3/24.
 */
public class ScanUserFile {
//    private File file;
//
//    public ScanUserFile(File file){
//        this.file = file;
//    }

    public List<UserFile> scan(File rootFile,int level){
        if(rootFile == null || rootFile.isFile()){
            return null;
        }
        List<UserFile> userFiles = new ArrayList<>();
        for(File file:rootFile.listFiles()){
            UserFile userFile = new UserFile(file.getName(),file.getPath(),level+1);
            userFile.setChilds(scan(file,level+1));
            userFiles.add(userFile);
        }
        return userFiles;
    }
}
