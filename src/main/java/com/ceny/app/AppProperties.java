package com.ceny.app;

/**
 * Created by chensongkui on 2017/3/20.
 */
public class AppProperties {
// TODO: 2017/3/20  

    private static AppProperties instance = new AppProperties();

    private AppProperties(){

    }

    public AppProperties getInstance(){
        return instance;
    }
}
