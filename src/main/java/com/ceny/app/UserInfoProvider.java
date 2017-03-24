package com.ceny.app;

import com.alibaba.fastjson.JSONArray;
import com.ceny.domain.UserInfo;

/**
 * Created by chensongkui on 2017/3/24.
 */
public class UserInfoProvider {
    private UserInfo userInfo;
    private JSONArray userFiles;
    private int diskUsed;
    private long diskLimit;
    private boolean isDone = false;

    public UserInfo getUserInfo(){
        return userInfo;
    }
    public void setUserInfo(UserInfo userInfo){
        this.userInfo = userInfo;
    }
    public JSONArray getUserFiles(){
        return userFiles;
    }
    public void setUserFiles(JSONArray jsonArray){
        this.userFiles = jsonArray;
    }
    public void setStaus(boolean status){
        isDone = status;
    }
    public boolean getStatus(){
        return isDone;
    }
}
