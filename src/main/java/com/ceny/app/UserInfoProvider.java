package com.ceny.app;

import com.ceny.domain.UserInfo;
import com.ceny.utils.AppInfo;
import com.ceny.utils.ScanUserFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chensongkui on 2017/3/24.
 */
public class UserInfoProvider {

    private static final Logger LOGGER = LogManager.getLogger(UserInfoProvider.class);

    private UserInfo userInfo;
    private UserFile rootFile;

    private long diskUsed;
    private long diskLimit;
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
        rootFile = generateRootFile(rootDir,userName);
        LOGGER.info("init",userName);
        isDone = true;
    }

    private File getRootDir(String userName){
        String filePath = AppInfo.getInstance().getDiskPath(userName);
        File rootDir = new File(filePath);
        if(!rootDir.exists() || rootDir.isFile()){
            rootDir.mkdirs();
        }
        return rootDir;
    }

    private UserFile generateRootFile(File rootDir,String userName){
        UserFile rootFile = new UserFile("ROOTFILE","/",0);
        ScanUserFile suf = new ScanUserFile();
        rootFile.setChilds(suf.scan(rootDir,0));
        return rootFile;
    }

    public UserFile getRootFile(){
        return rootFile;
    }
}
