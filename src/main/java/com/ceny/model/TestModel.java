package com.ceny.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chensongkui on 2017/3/3.
 */
public class TestModel {
    private Map<String,Integer> info;
    private String name;

    public TestModel(){
        info = new HashMap<>();
        name = "default";
    }

    public void setInfo(Map<String,Integer> info){
        this.info = info;
    }
    public Map<String,Integer> getInfo(){
        return info;
    }
    public String getName(){
        return name;
    }
}
