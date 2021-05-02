package com.example.bedhouseserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.bedhouseserver.POJO.Stf;
import com.example.bedhouseserver.service.impl.LoginServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@Controller
public class LoginController {
    @Autowired
    private LoginServiceImpl loginService;

    private final Logger log= LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @RequestMapping(value = "/login/status", method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest req){
        String name=req.getParameter("name");
        String password=req.getParameter("password");
        Stf res=loginService.loginStatus(name,password);
        JSONObject jsonObject=new JSONObject();
        if(res!=null) {
            loginService.updateStatus(res.getId(),new Date(),req.getRemoteAddr());

            jsonObject.put("code", 1);
            jsonObject.put("status", res.getRoleId());
            jsonObject.put("username",res.getName());
            jsonObject.put("id",res.getId());
        }
        else{
            jsonObject.put("code",1);
            jsonObject.put("status",0);
        }
        return jsonObject;
    }
}
