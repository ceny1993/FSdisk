package com.ceny.config.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.sql.DataSource;

/**
 * Created by chensongkui on 2017/3/17.
 */
@Configuration
public class BeansConfig {

    @Bean("multipartResolver")
    public StandardServletMultipartResolver standardServletMultipartResolver(){
        System.out.println("===========");
        return new StandardServletMultipartResolver();
    }
    
//    @Bean("multipartResolver")
//    public CommonsMultipartResolver commonsMultipartResolver(){
//        CommonsMultipartResolver tmp = new CommonsMultipartResolver();
//        tmp.setMaxUploadSize(100000000);
//        return tmp;
//    }
}
