package com.ceny.config.init;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.rolling.OnStartupTriggeringPolicy;
import org.apache.logging.log4j.core.util.IOUtils;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
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
        LogManager.shutdown();
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        //return new Class[]{RootConfig.class};
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration){
        MultipartConfigElement mce = new MultipartConfigElement("/FSdisk/tmpfile",2097152,4194304,0);
        registration.setMultipartConfig(mce);

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
            LOGGER.info("the logDir is:"+System.getProperty("logDir"));
        }
    }

    private static void initProperties(){
        try {
            Properties properties = PropertiesLoaderUtils.loadAllProperties("tmp.properties");
            LOGGER.info("the example properties of hello is: "+properties.getProperty("hello"));
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}
