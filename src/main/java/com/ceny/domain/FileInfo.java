package com.ceny.domain;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chensongkui on 2017/3/27.
 */
@Entity
@Table(name = "file_info")
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    public String userName;
    public String fileName;
    public String diskFileName;//物理存储的文件名，不会变
    public String contentType;
    public long size = 0L;
    public boolean isFolder = false;
    public long parentId;
    //public int level;
    public Date uploadTime;
    public Date deleteTime;
    public boolean isDelete = false;
    public boolean isShow = false;
    public boolean isShare = false;
    public int downloadCount = 0;
    public String notes;
    public String tags;

    protected FileInfo(){};

    public FileInfo(MultipartFile file, String userName, String diskFileName, long parentId, String notes, String tags){
        this.userName = userName;
        this.fileName = file.getOriginalFilename();
        this.diskFileName = diskFileName;
        this.parentId = parentId;
        this.contentType = file.getContentType();
        this.size = file.getSize();
        this.uploadTime = new Date();
        this.notes = notes;
        this.tags = tags;
    }

    public FileInfo(String userName, String folderName, String diskFolderName, long parentId, String notes, String tags){
        this.userName = userName;
        this.fileName = folderName;
        this.diskFileName = diskFolderName;
        this.parentId = parentId;
        this.isFolder = true;
        this.uploadTime = new Date();
        this.notes = notes;
        this.tags = tags;
        this.isFolder = true;
    }

}