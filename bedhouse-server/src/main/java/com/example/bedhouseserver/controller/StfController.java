package com.example.bedhouseserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.bedhouseserver.POJO.Stf;
import com.example.bedhouseserver.service.StfService;
import com.example.bedhouseserver.service.impl.StfServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Controller
public class StfController {
    @Autowired
    private StfServiceImpl stfService;

    private final Logger log= LoggerFactory.getLogger(this.getClass());

    //获取roleId小于参数的员工信息
    @ResponseBody
    @RequestMapping(value = "/stf/infoLessRole", method = RequestMethod.GET)
    public Object StfInfoLessRoleId(@RequestParam("roleId")Integer roleId){
        return stfService.stfInfoLessRoleId(roleId);
    }

    //获取指定id的员工信息
    @ResponseBody
    @RequestMapping(value = "/stf/infoById", method = RequestMethod.GET)
    public Object StfInfoById(@RequestParam("id")Integer id){
        return stfService.stfInfoById(id);
    }

    //更新员工信息
    @ResponseBody
    @RequestMapping(value = "/stf/update", method = RequestMethod.POST)
    public Object update(HttpServletRequest req){
        Stf stf=new Stf();
        JSONObject jsonObject=new JSONObject();
        boolean res=false;

        stf.setId(Integer.valueOf(req.getParameter("id").trim()));
        stf.setAge(Integer.valueOf(req.getParameter("age").trim()));
        stf.setName(req.getParameter("name"));
        stf.setGender(req.getParameter("gender"));
        stf.setLoginName(req.getParameter("loginName"));
        stf.setPassword(req.getParameter("password"));

        res=stfService.updateSelective(stf);

        if(res){
            jsonObject.put("code",1);
        }
        else{
            jsonObject.put("code",0);
        }
        return jsonObject;
    }

    @ResponseBody
    @RequestMapping(value = "/stf/search", method = RequestMethod.GET)
    public Object search(@RequestParam("word")String word){
        return stfService.search(word);
    }
}
