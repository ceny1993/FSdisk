package com.ceny.app;

import java.util.List;

/**
 * Created by chensongkui on 2017/3/24.
 */
public class UserFile {

    private String fileName;
    private String parentName;
    private int level;
    private List<UserFile> children;

    public UserFile(String fileName, String parentName, int level){
        this.fileName = fileName;
        this.parentName = parentName;
        this.level = level;
    }
    public String getFileName(){
        return fileName;
    }

    public void setChilds(List<UserFile> children){
        this.children = children;
    }

    public List<UserFile> getChildren(){
        return children;
    }

    public int getLevel(){
        return level;
    }
    public String getParentName(){
        return parentName;
    }
}
