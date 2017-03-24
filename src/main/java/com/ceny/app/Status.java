package com.ceny.app;

/**
 * Created by chensongkui on 2017/3/24.
 */
public enum Status {
    SUCCESS(1,"done"),
    NULL(-1,"null exception");

    private int code;
    private String description;

    private Status(int code,String description){
        this.code = code;
        this.description = description;
    }
}
