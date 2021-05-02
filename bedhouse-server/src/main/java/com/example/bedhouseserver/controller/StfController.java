package com.example.bedhouseserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.bedhouseserver.POJO.Stf;
import com.example.bedhouseserver.service.StfService;
import com.example.bedhouseserver.service.impl.StfServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

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
        stf.setPhone(req.getParameter("phone"));

        res=stfService.updateSelective(stf);

        if(res){
            jsonObject.put("code",1);
        }
        else{
            jsonObject.put("code",0);
        }
        return jsonObject;
    }

    //TODO 速度优化
    //查询
    @ResponseBody
    @RequestMapping(value = "/stf/search", method = RequestMethod.GET)
    public Object search(@RequestParam("word")String word,@RequestParam("roleId")Integer roleId){
        return stfService.search(word,roleId);
    }

    //TODO 其他模块仿照此模块异常处理
    //新增员工信息
    @ResponseBody
    @RequestMapping(value = "/stf/add", method = RequestMethod.POST)
    public Object add(HttpServletRequest req){
        JSONObject jsonObject=new JSONObject();
        Stf stf=new Stf();
        stf.setName(req.getParameter("name"));
        stf.setGender(req.getParameter("gender"));

        try {
            stf.setAge(Integer.valueOf(req.getParameter("age")));
        }catch( NumberFormatException e){
            e.printStackTrace();
            jsonObject.put("code",0);
            jsonObject.put("msg","年龄错误");
            return jsonObject;
        }

        stf.setLoginName(req.getParameter("loginName"));
        stf.setPassword(req.getParameter("password"));
        stf.setPhone(req.getParameter("phone"));

        try {
            String dateString=req.getParameter("entryTime");
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            if(simpleDateFormat.parse(dateString)==null){
                date=null;
            }else {
                date = simpleDateFormat.parse(req.getParameter("entryTime"));
            }
            stf.setEntryTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
            jsonObject.put("code",0);
            jsonObject.put("msg","日期错误");
            return jsonObject;
        }

        try {
            stf.setRoleId(Integer.valueOf(req.getParameter("roleId")));
        }catch( NumberFormatException e){
            e.printStackTrace();
            jsonObject.put("code",0);
            jsonObject.put("msg","权限错误");
            return jsonObject;
        }
        boolean res=false;
        try {
            res=stfService.add(stf);
        }catch (DataAccessException e){
            e.printStackTrace();
            jsonObject.put("code",0);
            jsonObject.put("msg","数据库操作错误");
            return jsonObject;
        }
        jsonObject.put("code",res);
        return jsonObject;
    }

    //TODO 编辑和删除
}
