package com.example.bedhouseserver.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class TestController {
    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Object test(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("msg","helloworld");
        return jsonObject;
    }
}
