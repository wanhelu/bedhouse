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
        Stf stf;

        try {
            stf=getStfByReq(req);
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("code",0);
            jsonObject.put("msg","数据转换错误");
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

    //删除
    @ResponseBody
    @RequestMapping(value = "/stf/delete", method = RequestMethod.DELETE)
    public Object del(@RequestParam("id")Integer id){
        JSONObject jsonObject=new JSONObject();
        if(id==null)jsonObject.put("code",0);
        else if(stfService.del(id)){
            jsonObject.put("code",1);
        }
        else{
            jsonObject.put("code",0);
        }
        return jsonObject;
    }

    //修改
    @ResponseBody
    @RequestMapping(value = "/stf/edit", method = RequestMethod.POST)
    public Object upd(HttpServletRequest req){
        JSONObject jsonObject=new JSONObject();
        Stf stf;

        try {
            stf=getStfByReq(req);
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("code",0);
            jsonObject.put("msg","数据转换错误");
            return jsonObject;
        }

        if(stf.getId()==null){
            jsonObject.put("code",0);
            jsonObject.put("msg","无id");
            return jsonObject;
        }

        boolean res;
        try {
            res=stfService.upd(stf);
        }catch (DataAccessException e){
            e.printStackTrace();
            jsonObject.put("code",0);
            jsonObject.put("msg","数据库操作错误");
            return jsonObject;
        }
        jsonObject.put("code",res?1:0);
        return jsonObject;
    }

    private Stf getStfByReq(HttpServletRequest req) throws Exception {
        Stf stf = new Stf();

        String idS=req.getParameter("id");
        if (idS == null || idS.equals("")||idS.equals("null")) stf.setId(null);
        else stf.setId(Integer.valueOf(idS));

        stf.setName(getString(req,"name"));
        stf.setGender(getString(req,"gender"));

        String ageS = req.getParameter("age");
        if (ageS == null || ageS.equals("")||ageS.equals("null")) stf.setAge(null);
        else stf.setAge(Integer.valueOf(ageS));

        stf.setLoginName(getString(req,"loginName"));
        stf.setPassword(getString(req,"password"));
        stf.setPhone(getString(req,"phone"));

        String dateString = req.getParameter("entryTime");
        if (dateString == null || dateString.equals("")||dateString.equals("null")) stf.setEntryTime(null);
        else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            if (simpleDateFormat.parse(dateString) == null) {
                date = null;
            } else {
                date = simpleDateFormat.parse(req.getParameter("entryTime"));
            }
            stf.setEntryTime(date);
        }

        String roleIdS = req.getParameter("roleId");
        if (roleIdS == null || roleIdS.equals("")||roleIdS.equals("null")) stf.setRoleId(null);
        else stf.setRoleId(Integer.valueOf(req.getParameter("roleId")));

        return stf;
    }

    private String getString(HttpServletRequest req,String name){
        String s=req.getParameter(name);
        if(s!=null&&s.equals("null")) s=null;
        return s;
    }
}
