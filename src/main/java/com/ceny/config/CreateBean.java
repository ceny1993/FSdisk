package com.ceny.config;

import com.ceny.Bean.TestBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by chensongkui on 2017/3/3.
 */

@Configuration
public class CreateBean {

    @Bean
    public TestBean testBean(){
        return new TestBean();
    }
}
