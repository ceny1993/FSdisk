package com.ceny.controllers;

import com.alibaba.fastjson.JSONArray;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chensongkui on 2017/3/20.
 */
@RestController
public class FileController {

    @RequestMapping(value = "/user/files",method = RequestMethod.GET)
    public JSONArray getUserFiles(){
        JSONArray tmp = new JSONArray();
        tmp.add("sdsd");
        tmp.add("eee");
        return tmp;
    }
}
