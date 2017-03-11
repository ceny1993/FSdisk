package com.ceny.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.core.util.IOUtils;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import java.io.File;
import java.io.FileReader;

/**
 * Created by chensongkui on 2017/3/3.
 */
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    static {
        //do something when init
        initLog();
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        //return new Class[]{RootConfig.class};
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        //return null;
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    private static void initLog(){
        try {
            String appConfPath = WebConfig.class.getClassLoader().getResource("appConf.json").getPath();
            JSONObject appConf = JSON.parseObject(IOUtils.toString(new FileReader(appConfPath)));
            File logDir = new File(appConf.getString("logDir"));
            logDir.mkdirs();
            if(logDir.exists() && logDir.isDirectory() && logDir.canWrite()){
                System.setProperty("logDir",appConf.getString("logDir"));
            }

        }
        catch (Exception e){
            //do nothing
        }
        finally {
            if(System.getProperty("logDir") == null){
                //need chmod in terminal
                System.setProperty("logDir",System.getProperty("user.home")+"/defaultAppLogs/FSdisk");
            }
        }
    }
}
