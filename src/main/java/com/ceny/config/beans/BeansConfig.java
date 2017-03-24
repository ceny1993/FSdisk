package com.ceny.config.beans;

import com.ceny.app.UserInfoProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

/**
 * Created by chensongkui on 2017/3/17.
 */
@Configuration
public class BeansConfig {

    private static final Logger LOGGER = LogManager.getLogger(BeansConfig.class);

    @Bean("multipartResolver")
    public StandardServletMultipartResolver standardServletMultipartResolver(){
        return new StandardServletMultipartResolver();
    }

    @Bean("userInfoProvider")
    @Scope(value = "session",proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UserInfoProvider userInfoProvider(){
        UserInfoProvider userInfoProvider = new UserInfoProvider();
        LOGGER.info("userInfoProvider bean!");
        return userInfoProvider;
    }

//    @Bean("multipartResolver")
//    public CommonsMultipartResolver commonsMultipartResolver(){
//        CommonsMultipartResolver tmp = new CommonsMultipartResolver();
//        tmp.setMaxUploadSize(100000000);
//        return tmp;
//    }
}
