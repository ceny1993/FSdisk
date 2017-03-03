package com.ceny.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by chensongkui on 2017/3/3.
 */
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {
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
}
