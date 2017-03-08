package com.ceny.controller;

import com.ceny.Bean.TestBean;
import com.ceny.model.TestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.annotation.XmlRootElement;
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


    @RequestMapping(value = "/list",produces = "application/json")
    public List<String> getList(){
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("spring");
        return list;
    }

    @RequestMapping(value = "/list/{id}",method = RequestMethod.GET)
    public ResponseEntity<String> findList(@PathVariable int id){
        System.out.println(id);
        //just a test,not correct
        return new ResponseEntity<String>(testBean.toString(), HttpStatus.BAD_REQUEST);
    }


    //http://localhost:8015/model.xml?name=xiaoceny
    //http://localhost:8015/model.json?name=xiaoceny
    @RequestMapping(value = "/model",method = RequestMethod.GET)
    public TestModel getModel(@RequestParam(value = "name",defaultValue = "ceny") String name){
        TestModel testModel = new TestModel();
        Map<String,Integer> tmp = new HashMap<>();
        tmp.put(name,789);
        testModel.setInfo(tmp);
        return testModel;
    }


    //need set the Content-Type as application/json
    @RequestMapping(value = "/model",method = RequestMethod.POST,consumes = "application/json")
    public Map<String,Integer> saveModel(@RequestBody TestModel model){
        System.out.println(model.getName());
        Map<String,Integer> tmp = new HashMap<>();
        tmp.put("status",1);
        return tmp;
    }

    // TODO: 2017/3/8
    // 在响应中设置头部信息以及REST client


}
