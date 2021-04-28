package com.example.bedhouseserver.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Controller
public class TestController {
    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public Object test(HttpServletRequest req){
        String get=req.getParameter("data");
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("msg",get+"helloworld");
        return jsonObject;
    }
}
