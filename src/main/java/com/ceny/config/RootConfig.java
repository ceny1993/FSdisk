package com.ceny.config;

import com.ceny.Bean.TestBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by chensongkui on 2017/3/3.
 */

//ContentLoadListener

@Configuration
public class RootConfig {
    //can be removed
    //do nothing
    //if create bean here ,the bean will be created twice
}
