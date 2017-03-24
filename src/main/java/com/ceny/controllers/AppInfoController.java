package com.ceny.controllers;

import com.ceny.app.UserInfoProvider;
import com.ceny.utils.AppInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by chensongkui on 2017/3/20.
 */

@RestController
public class AppInfoController {

    private static final Logger LOGGER = LogManager.getLogger(AppInfoController.class);

    //funny
    @Autowired
    Environment env;

    @Autowired
    UserInfoProvider userInfoProvider;

    @RequestMapping(value = "/app/version",method = RequestMethod.GET)
    public String getVersion() throws IOException {
        LOGGER.info(userInfoProvider.getStatus());
        return AppInfo.getInstance().getVersion();
    }

    @RequestMapping(value = "/app/name",method = RequestMethod.GET)
    public String getName() throws IOException {
        return AppInfo.getInstance().getName();
    }


//    @RequestMapping(value = "/table",method = RequestMethod.GET)
//    public Map<String,String> testTable() throws IOException {
//        try{
//            System.out.println("====");
//            repo.save(new Customer("hello","world",new TmpClass("aaaa",777)));
//            System.out.println(repo.count());
//            System.out.println("====");
//        }
//        catch (Exception e){
//            System.out.println(e);
//
//        }
//        return name;
//    }

}
