package com.ceny.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by chensongkui on 2017/3/22.
 */
//not bean
@Configuration
@PropertySource("classpath:app.properties")
public class ContextProvider implements ApplicationContextAware {

    private static ApplicationContext context;
    public static ApplicationContext getContext(){
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
