package com.ceny.config.init;

import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by chensongkui on 2017/3/20.
 */
public class LoadProfile implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //TODO: 2017/3/13
        // profile or filter or servlet etc
        // servletContext.setInitParameter("spring.profiles.default", "dev");
    }
}
