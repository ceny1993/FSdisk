package com.ceny.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

/**
 * Created by chensongkui on 2017/3/15.
 */

@Configuration
@EnableWebSecurity
//@EnableWebMvcSecurity
//deprecated
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("ceny").password("123456").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .formLogin().and()
        .logout().and()
        .authorizeRequests()
                .anyRequest().permitAll().and()
                //.antMatchers("/abc.html").permitAll()
                //.antMatchers(HttpMethod.GET,"/map").hasRole("ADMIN")
                //.anyRequest().authenticated().and()
        .csrf().disable();
    }
}
