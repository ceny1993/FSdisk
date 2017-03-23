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
    private Long id;
    private String name;
    private String passwd;

    @Column(name = "pic_url")
    private String picUrl;
    private String role = "USER";

    @Column(name = "account_enable")
    private boolean accountEnable = false;

    @Column(name = "disk_used")
    private long diskUsed = 0L;

    @Column(name = "disk_limit")
    private long diskLimit = 0L;

    private Date registerTime;
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date lastActiveTime;

    private String email;
    @Column(name = "email_checked")
    private boolean emailChecked = false;
    private String phone;
    @Column(name = "phone_checked")
    private boolean phoneChecked = false;

    @Column(length = 1000)
    private String signature;

    protected UserInfo(){}

    public UserInfo(String name,String passwd){
        this.name = name;
        this.passwd = passwd;
        Date data = new Date();
        registerTime = data;
        lastActiveTime = data;
    }

    public String toString(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        jsonObject.put("name",name);
        jsonObject.put("registerTime",registerTime);
        return jsonObject.toString();
    }

    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public Date getRegisterTime(){
        return registerTime;
    }
    public String getSignature(){
        return signature;
    }
    public String getPhone(){
        return phone;
    }
    public String getEmail(){
        return email;
    }


}
