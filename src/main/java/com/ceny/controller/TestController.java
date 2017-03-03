package com.ceny.controller;

import com.ceny.Bean.TestBean;
import com.ceny.model.TestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chensongkui on 2017/3/3.
 */

@RestController
public class TestController {

    @Autowired
    TestBean testBean;

    @RequestMapping("/")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping("/map")
    public Map<String,String> getMap(){
        Map<String,String> map = new HashMap<>();
        map.put("kk","sds");
        System.out.println(testBean.toString());
        return map;
    }

    @RequestMapping("/list")
    public List<String> getList(){
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("spring");
        return list;
    }

    @RequestMapping("/model")
    public TestModel getModel(){
        TestModel testModel = new TestModel();
        Map<String,Integer> tmp = new HashMap<>();
        tmp.put("ceny",789);
        tmp.put("ceny1993",1993);
        testModel.setInfo(tmp);
        return testModel;
    }

}
