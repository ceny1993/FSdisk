package com.ceny.Bean;

/**
 * Created by chensongkui on 2017/3/3.
 */

//create bean or service here

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
