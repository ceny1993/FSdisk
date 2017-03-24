package com.ceny.app;

import com.ceny.domain.UserInfo;
import com.ceny.domain.UserInfoRepo;
import com.ceny.utils.AppInfo;
import com.ceny.utils.ScanUserFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chensongkui on 2017/3/24.
 */
public class UserInfoProvider {

    private static final Logger LOGGER = LogManager.getLogger(UserInfoProvider.class);

    @Autowired
    UserInfoRepo userInfoRepo;

    private UserInfo userInfo;
    private UserFile rootFile;
    private String rootPath;

    private boolean isDone = false;

    public UserInfo getUserInfo(){
        return userInfo;
    }
    public void setUserInfo(UserInfo userInfo){
        this.userInfo = userInfo;
    }

    public void setStaus(boolean status){
        isDone = status;
    }
    public boolean getStatus(){
        return isDone;
    }

    public void init(String userName){
        File rootDir = getRootDir(userName);
        rootFile = generateRootFile(rootDir);
        userInfo = userInfoRepo.findOneByUserName(userName);
        userInfo.diskUsed += 100;
        userInfoRepo.saveAndFlush(userInfo);
        LOGGER.info("init done: "+userName);
        LOGGER.info(userInfo);
        isDone = true;
    }

    private File getRootDir(String userName){
        rootPath = AppInfo.getInstance().getDiskPath(userName);
        File rootDir = new File(rootPath);
        if(!rootDir.exists() || rootDir.isFile()){
            rootDir.mkdirs();
        }
        return rootDir;
    }

    private UserFile generateRootFile(File rootDir){
        UserFile rootFile = new UserFile(rootDir.getName(),"/",0);
        ScanUserFile suf = new ScanUserFile();
        rootFile.setChilds(suf.scan(rootDir,0));
        return rootFile;
    }

    public UserFile getRootFile(){
        return rootFile;
    }

    public void addFolder(String parentPath , String folderName){
        //暂不支持一次创建多个文件夹 如/cenytest/abc/def
        //parentName : /auto/abc

        UserFile parentFile = getParentFile(parentPath);
        UserFile newUserFile = new UserFile(folderName,parentFile.getFileName(),parentFile.getLevel()+1);
        newUserFile.setChilds(new ArrayList<UserFile>());
        parentFile.getChildren().add(newUserFile);

    }

    public void removeFolder(String parentName , String folderName){
        // TODO: 2017/3/25
    }

    private UserFile getParentFile(String parentPath){
        //根据父路径定位该父亲文件夹
        String[] folderList = parentPath.split("/");
        UserFile parentFile = rootFile;
        for(String str:folderList){
            if(str.equals("")){
                continue;
            }
            for(UserFile tmpFile:parentFile.getChildren()) {
                if (tmpFile.getFileName().equals(str)) {
                    parentFile = tmpFile;
                    break;
                }
            }
        }
        return parentFile;
    }

}
