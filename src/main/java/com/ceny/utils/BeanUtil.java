package com.ceny.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;

/**
 * Created by chensongkui on 2017/3/22.
 */
public class BeanUtil {

    private static Logger LOGGER = LogManager.getLogger(BeanUtil.class);

    public static void getBean(){
        LOGGER.info("hello");
        ApplicationContext context = ContextProvider.getContext();
        DataSource dataSource = (DataSource) context.getBean("datasource");
        LOGGER.info(dataSource);
    }

}
