package com.ceny.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.rolling.OnStartupTriggeringPolicy;
import org.apache.logging.log4j.core.util.IOUtils;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by chensongkui on 2017/3/3.
 */
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final Logger LOGGER = LogManager.getLogger(WebInit.class);
    static {
        //do something when init
        initLog();
        initProperties();
        LogManager.shutdown();//关闭以生效log4j2配置
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

//should not override here
//    @Override
//    public void onStartup(ServletContext var1) throws ServletException{
//        //eg: var1.setInitParameter("spring.profiles.default", "dev");
//    }

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
            LOGGER.info("the logDir is:"+System.getProperty("logDir"));
        }
    }

    private static void initProperties(){
        //LogManager.
        try {
            Properties properties = PropertiesLoaderUtils.loadAllProperties("tmp.properties");
            LOGGER.info("the example properties of hello is: "+properties.getProperty("hello"));
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}
