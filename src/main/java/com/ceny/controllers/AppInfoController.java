package com.ceny.controllers;

import com.ceny.config.database.CustomerRepo;
import com.ceny.domain.Customer;
import com.ceny.domain.TmpClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by chensongkui on 2017/3/20.
 */

@RestController
public class AppInfoController {

    private static final Logger LOGGER = LogManager.getLogger(AppInfoController.class);
    private static Properties properties;
    private static Map<String,String> version = new HashMap<>();
    private static Map<String,String> name = new HashMap<>();

    @Autowired
    CustomerRepo repo;

    public AppInfoController(){
        try {
            properties = PropertiesLoaderUtils.loadAllProperties("app.properties");
            version.put("version",properties.getProperty("version"));
            name.put("name",properties.getProperty("name"));
            LOGGER.info("the app.properties file has been processed successfully!");
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }


    @RequestMapping(value = "/app/version",method = RequestMethod.GET)
    public Map<String,String> getVersion() throws IOException {
        return version;
    }

    @RequestMapping(value = "/app/name",method = RequestMethod.GET)
    public Map<String,String> getName() throws IOException {
        return name;
    }


    @RequestMapping(value = "/table",method = RequestMethod.GET)
    public Map<String,String> testTable() throws IOException {
        try{
            System.out.println("====");
            repo.save(new Customer("hello","world",new TmpClass("aaaa",777)));
            System.out.println(repo.count());
            System.out.println("====");
        }
        catch (Exception e){
            System.out.println(e);

        }
        return name;
    }

}
