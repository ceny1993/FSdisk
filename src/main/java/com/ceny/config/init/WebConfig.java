package com.ceny.config.init;

import com.ceny.config.security.Interceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by chensongkui on 2017/3/3.
 */

//DispatcherServlet

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.ceny"})
public class WebConfig extends WebMvcConfigurerAdapter{

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
        configurer.enable();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new Interceptor());
    }
    // TODO: 2017/3/24  http://stackoverflow.com/questions/18218386/cannot-autowire-service-in-handlerinterceptoradapter
}
