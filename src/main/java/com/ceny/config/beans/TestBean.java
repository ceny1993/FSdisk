package com.ceny.config.beans;

import org.springframework.context.annotation.Configuration;

/**
 * Created by chensongkui on 2017/3/16.
 */
@Deprecated
@Configuration
public class TestBean {
    private static int x=0;
    private String name;
    private int id;

    public TestBean(){
        x++;
        name="ceny";
        id=789;
        System.out.println("bean has been created "+x);
    }

    @Override
    public String toString(){
        return "name:"+name+" and id:"+id;
    }
}
