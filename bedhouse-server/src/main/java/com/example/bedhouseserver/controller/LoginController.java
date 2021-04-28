package com.example.bedhouseserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.bedhouseserver.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Controller
public class LoginController {
    @Autowired
    private LoginServiceImpl loginService;

    @ResponseBody
    @RequestMapping(value = "/login/status", method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest req){
        String name=req.getParameter("name");
        String password=req.getParameter("password");
        int res=loginService.loginStatus(name,password);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",1);
        jsonObject.put("status",res);
        return jsonObject;
    }
}
