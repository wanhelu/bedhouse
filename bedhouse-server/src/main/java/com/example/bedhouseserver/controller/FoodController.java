package com.example.bedhouseserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.bedhouseserver.POJO.Food;
import com.example.bedhouseserver.myUtil.Httpreq;
import com.example.bedhouseserver.service.FoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Controller
public class FoodController {
    @Autowired
    FoodService foodService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //获取所有信息
    @ResponseBody
    @RequestMapping(value = "/food/info", method = RequestMethod.GET)
    public Object allInfo() {
        return foodService.allInfo();
    }

    //获取指定id信息
    @ResponseBody
    @RequestMapping(value = "/food/infoById", method = RequestMethod.GET)
    public Object infoById(@RequestParam("id") Integer id) {
        if (id == null) return null;
        else return foodService.infoById(id);
    }

    //搜索
    @ResponseBody
    @RequestMapping(value = "/food/search", method = RequestMethod.GET)
    public Object search(@RequestParam("word") String word) {
        return foodService.search(word);
    }

    //新增
    @ResponseBody
    @RequestMapping(value = "/food/add", method = RequestMethod.POST)
    public Object add(HttpServletRequest req){
        JSONObject jsonObject=new JSONObject();
        Food food;

        try {
            food=getFoodByReq(req);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("code",0);
            jsonObject.put("msg","数据转换错误");
            return jsonObject;
        }

        boolean res=false;
        try {
            res= foodService.add(food);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("code",0);
            jsonObject.put("msg","数据库操作错误");
            return jsonObject;
        }
        if(res)jsonObject.put("code",1);
        else jsonObject.put("code",0);
        return jsonObject;
    }

    //删除
    @ResponseBody
    @RequestMapping(value = "/food/del", method = RequestMethod.DELETE)
    public Object del(@RequestParam("id")Integer id){
        JSONObject jsonObject=new JSONObject();
        if(id==null)jsonObject.put("code",0);
        else if(foodService.del(id)){
            jsonObject.put("code",1);
        }else{
            jsonObject.put("code",0);
        }
        return jsonObject;
    }

    //修改
    @ResponseBody
    @RequestMapping(value = "/food/edit", method = RequestMethod.POST)
    public Object upd(HttpServletRequest req){
        JSONObject jsonObject=new JSONObject();
        Food food;

        try {
            food=getFoodByReq(req);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("code",0);
            jsonObject.put("msg","数据转换错误");
            return jsonObject;
        }

        boolean res=false;
        try {
            res= foodService.upd(food);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("code",0);
            jsonObject.put("msg","数据库操作错误");
            return jsonObject;
        }
        if(res)jsonObject.put("code",1);
        else jsonObject.put("code",0);
        return jsonObject;
    }

    private Food getFoodByReq(HttpServletRequest req) throws Exception{
        Food food=new Food();

        String idS= Httpreq.getString(req,"id");
        food.setId(idS==null?null:Integer.valueOf(idS));

        food.setName(Httpreq.getString(req,"name"));

        food.setType(Httpreq.getString(req,"type"));

        food.setLabel(Httpreq.getString(req,"label"));

        String priceS=Httpreq.getString(req,"price");
        food.setPrice(priceS==null?null:Double.valueOf(priceS));

        food.setPicurl(Httpreq.getString(req,"picurl"));

        food.setMuslim(Httpreq.getString(req,"muslim"));

        return food;
    }
}
