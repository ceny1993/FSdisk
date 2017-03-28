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
 * 很多细节没完善
 * 分页
 * 身份检查
 * 文件大小
 * 参数合法。。。
 * 统一状态码
 */
@RestController
public class FileController {

    private static final Logger LOGGER = LogManager.getLogger(FileController.class);

    @Autowired
    UserInfoProvider userInfoProvider;

    //上传文件
    @RequestMapping(value = "/user/file",method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("parentId") long parentId, Authentication auth){
        // TODO: 2017/3/27 多文件上传
        userInfoProvider.uploadFile(file,auth.getName(),parentId,"notes","tags");
        return "done";
    }

    //下载文件
    @RequestMapping(value = "/user/file/{id}",method = RequestMethod.GET)
    public String downloadFile(@PathVariable long id, Authentication auth){
        // TODO: 2017/3/28
        return "download file";
    }



    //创建文件夹
    @RequestMapping(value = "/user/folder", method = RequestMethod.POST)
    public String createFolder(@RequestBody JSONObject jsonObject, Authentication auth){
        userInfoProvider.createFolder(auth.getName(),jsonObject.getLongValue("parentId"),jsonObject.getString("folderName"),"foldernotes","foldertags");
        return "folder done";
    }

    //列出文件夹下的文件
    @RequestMapping(value = "/user/file",method = RequestMethod.GET)
    public List<FileInfo> getUserFiles(@RequestParam(name = "parentId",defaultValue = "-1") long parentId){
        return userInfoProvider.getFileList(parentId);
    }



    //删除文件
    @RequestMapping(value = "/user/file/{id}",method = RequestMethod.POST)
    public String deleteFile(@PathVariable long id, @RequestParam(name = "isSoft",defaultValue = "true") boolean isSoft, Authentication auth){
        LOGGER.info(id);
        if(isSoft){
            userInfoProvider.softDelete(id);
            LOGGER.info("soft delete");
        }
        else{
            userInfoProvider.softDelete(id);
            // TODO: 2017/3/28 hardDelete
        }
        return "delete file done";
    }

    //恢复文件
    @RequestMapping(value = "/user/file/restore/{id}",method = RequestMethod.POST)
    public String restoreFile(@PathVariable long id,Authentication auth){
        userInfoProvider.restoreFile(id);
        return "restore file done";
    }




}
