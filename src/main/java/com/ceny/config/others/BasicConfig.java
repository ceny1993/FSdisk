package com.ceny.config.others;

import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by chensongkui on 2017/3/16.
 */
public class BasicConfig implements WebApplicationInitializer {


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //TODO: 2017/3/13
        // profile or filter or servlet etc
        // servletContext.setInitParameter("spring.profiles.default", "dev");
    }
}
