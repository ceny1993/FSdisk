package com.ceny.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * Created by chensongkui on 2017/3/23.
 */
public class AppInfo {

    private static AppInfo instance = new AppInfo();
    private Environment env;

    private AppInfo(){
        env = ContextProvider.getContext().getEnvironment();
    }

    public static AppInfo getInstance(){
        return instance;
    }

    public String getName(){
        return env.getProperty("name");
    }

    public String getVersion(){
        return env.getProperty("version");
    }
    public String getDiskPath(String userName){
        return env.getProperty("userFilePath")+"/"+userName;
    }
    public String getDiskPath(){
        return env.getProperty("userFilePath");
    }

/*
* 因吹斯听
 http://stackoverflow.com/questions/19421092/autowired-environment-is-null
    @Autowired
    Environment env;
*
*
* properties = PropertiesLoaderUtils.loadAllProperties("app.properties");
            version.put("version",properties.getProperty("version"));
            name.put("name",properties.getProperty("name"));
*
*
* */
}
