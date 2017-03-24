package com.ceny.app;

import java.util.List;

/**
 * Created by chensongkui on 2017/3/24.
 */
public class UserFile {

    private String fileName;
    private String filePath;
    private int level;
    private List<UserFile> childs;

    public UserFile(String fileName, String filePath, int level){
        this.fileName = fileName;
        this.filePath = filePath;
        this.level = level;
    }
    public String getFileName(){
        return fileName;
    }

    public void setChilds(List<UserFile> childs){
        this.childs = childs;
    }

    public List<UserFile> getChilds(){
        return childs;
    }

    public int getLevel(){
        return level;
    }
}
