package com.ceny.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ceny.app.Status;
import com.ceny.app.UserFile;
import com.ceny.app.UserInfoProvider;
import com.ceny.domain.FileInfo;
import com.ceny.utils.AppInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

/**
 * Created by chensongkui on 2017/3/20.
 */
@RestController
public class FileController {

    private static final Logger LOGGER = LogManager.getLogger(FileController.class);

    @Autowired
    UserInfoProvider userInfoProvider;

    @RequestMapping(value = "/user/file",method = RequestMethod.GET)
    public List<FileInfo> getUserFiles(@RequestParam(name = "parentId",defaultValue = "-1") long parentId){
        return userInfoProvider.getFileList(parentId);
//        if(userInfoProvider.getRootFile() == null){
//            return null;
//        }
//        return userInfoProvider.getRootFile().getChildren();
    }

    @RequestMapping(value = "/user/file/{id}")
    public Status deleteFile(@PathVariable long id){
        LOGGER.info(id);
        return Status.SUCCESS;
    }

    @RequestMapping(value = "/file",method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("parentId") long parentId, Authentication auth){
        // TODO: 2017/3/27 多文件上传
        userInfoProvider.uploadFile(file,auth.getName(),parentId,"notes","tags");
        return "done";
    }

    @RequestMapping(value = "/user/folder", method = RequestMethod.POST)
    public String createFolder(@RequestBody JSONObject jsonObject, Authentication auth){
        userInfoProvider.createFolder(auth.getName(),jsonObject.getLongValue("parentId"),jsonObject.getString("folderName"),"foldernotes","foldertags");
        return "folder done";
    }




}
