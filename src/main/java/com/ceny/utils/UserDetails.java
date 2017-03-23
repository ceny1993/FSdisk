package com.ceny.utils;

import com.alibaba.fastjson.JSONArray;
import com.ceny.domain.UserInfo;

/**
 * Created by chensongkui on 2017/3/23.
 * scope为session的bean
 * 维持用户的详细信息
 */
public class UserDetails {
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
}
