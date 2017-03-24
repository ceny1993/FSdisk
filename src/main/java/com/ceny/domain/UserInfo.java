package com.ceny.domain;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONPOJOBuilder;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chensongkui on 2017/3/23.
 */
@Entity
@Table(name = "user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    public String userName;
    public String password;
    //give up
    //@Column(name = "pic_url")
    public String picUrl;
    public String role = "USER";
    public boolean accountEnable = false;
    public long diskUsed = 0L;
    public long diskLimit = 0L;
    public Date registerTime;
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    public Date lastActiveTime;
    public String email;
    public boolean emailChecked = false;
    public String phone;
    public boolean phoneChecked = false;
    @Column(length = 1000)
    public String signature;

    protected UserInfo(){}

    public UserInfo(String name,String passwd){
        this.userName = name;
        this.password = passwd;
        Date data = new Date();
        registerTime = data;
        lastActiveTime = data;
    }

    public String toString(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        jsonObject.put("name",userName);
        jsonObject.put("registerTime",registerTime);
        return jsonObject.toString();
    }



}
