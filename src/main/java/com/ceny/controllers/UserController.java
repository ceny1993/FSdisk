package com.ceny.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ceny.app.UserInfoProvider;
import com.ceny.domain.UserInfo;
import com.ceny.domain.UserInfoRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chensongkui on 2017/3/23.
 */
@RestController
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);
    private static final StandardPasswordEncoder ENCODER = new StandardPasswordEncoder("ceny");
    @Autowired
    UserInfoRepo userInfoRepo;

    @Autowired
    UserInfoProvider userInfoProvider;


    @RequestMapping(value = "/user/check",method = RequestMethod.GET)
    public boolean testPassword(HttpServletRequest request){
        String rawPassword = request.getHeader("rawPassword");
        String encodedPassword = request.getHeader("encodedPassword");
        return ENCODER.matches(rawPassword,encodedPassword);
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public UserInfo register(@RequestBody JSONObject jsonObject){
        // TODO: 2017/3/23 check 
        String userName = jsonObject.getString("name");
        if(userName == null){
            userName = "checkyou";
        }
        String password = jsonObject.getString("passwd");
        if(password == null){
            password = "checkyou";
        }
        password = ENCODER.encode(password);
        LOGGER.info(password);
        UserInfo userInfo = new UserInfo(userName,password);
        userInfoRepo.saveAndFlush(userInfo);
        return userInfo;
    }

    @RequestMapping(value = "/user/init",method = RequestMethod.POST)
    public boolean init(Authentication auth){
        userInfoProvider.init(auth.getName());
        return true;
    }
}
