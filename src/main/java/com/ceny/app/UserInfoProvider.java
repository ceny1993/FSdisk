package com.ceny.app;

import com.ceny.domain.FileInfo;
import com.ceny.domain.FileInfoRepo;
import com.ceny.domain.UserInfo;
import com.ceny.domain.UserInfoRepo;
import com.ceny.utils.AppInfo;
import com.ceny.utils.ScanUserFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by chensongkui on 2017/3/24.
 */
public class UserInfoProvider {

    private static final Logger LOGGER = LogManager.getLogger(UserInfoProvider.class);

    @Autowired
    UserInfoRepo userInfoRepo;
    @Autowired
    FileInfoRepo fileInfoRepo;

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

    public void uploadFile(MultipartFile file, String userName,long parentId, String notes, String tags){
        String diskFileName = System.currentTimeMillis()+"_"+file.getOriginalFilename();
        String parentPath = getParentPath(userName,parentId);
        try {
            File tmpFile = new File(parentPath+"/"+diskFileName);
            OutputStream out = new FileOutputStream(tmpFile);
            InputStream in = file.getInputStream();
            byte[] content = new byte[1024];
            int n;
            while ((n = in.read(content)) != -1){
                out.write(content, 0, n);
            }
            in.close();
            out.close();
            FileInfo fileInfo = new FileInfo(file,userName,diskFileName,parentId,notes,tags);
            fileInfoRepo.saveAndFlush(fileInfo);
        }
        catch (Exception e){
            LOGGER.error(e);
        }
    }


    public void createFolder(String userName, long parentId, String folderName, String notes, String tags){
        String diskFolderName = System.currentTimeMillis()+"_"+folderName;
        String parentPath = getParentPath(userName,parentId);
        LOGGER.info(parentPath);
        File file = new File(parentPath+"/"+diskFolderName);
        file.mkdirs();
        FileInfo fileInfo = new FileInfo(userName,folderName,diskFolderName,parentId,notes,tags);
        fileInfoRepo.saveAndFlush(fileInfo);
    }

    private String getParentPath(String userName, long parentId){
        Stack<String> stack = new Stack<>();
        while (parentId!=-1L){
            FileInfo fileInfo = fileInfoRepo.findOne(parentId);
            if(fileInfo == null || !fileInfo.isFolder){
                //detail check
                return null;
            }
            parentId = fileInfo.parentId;
            LOGGER.info(parentId);
            stack.push(fileInfo.diskFileName);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(AppInfo.getInstance().getDiskPath(userName));
        while (!stack.empty()){
            sb.append("/").append(stack.pop());
        }
        return sb.toString();
        // TODO: 2017/3/28
    }


    public List<FileInfo> getFileList(long parentId){
        return fileInfoRepo.findAllByParentId(parentId);
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

    @Deprecated
    public void removeFolder(String parentName , String folderName){
        // TODO: 2017/3/25
    }

    public void removeFile(String parentPath, String fileName){

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
