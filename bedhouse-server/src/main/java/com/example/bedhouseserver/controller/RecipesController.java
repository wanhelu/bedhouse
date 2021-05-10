package com.example.bedhouseserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.bedhouseserver.POJO.Recipes;
import com.example.bedhouseserver.myUtil.Httpreq;
import com.example.bedhouseserver.service.impl.RecipesServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Controller
public class RecipesController {
    @Autowired
    RecipesServiceImpl recipesService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //获取所有信息
    @ResponseBody
    @RequestMapping(value = "/recipes/info", method = RequestMethod.GET)
    public Object allInfo() {
        return recipesService.allInfo();
    }

    //查找
    @ResponseBody
    @RequestMapping(value = "/recipes/search", method = RequestMethod.GET)
    public Object search(@RequestParam("word") String word) {
        return recipesService.search(word);
    }


    //新增
    @ResponseBody
    @RequestMapping(value = "/recipes/add", method = RequestMethod.POST)
    public Object add(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        Recipes recipes;

        try {
            recipes = getRecipesByReq(req);
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("code", 0);
            jsonObject.put("msg", "数据转换错误");
            return jsonObject;
        }

        boolean res = false;
        try {
            res = recipesService.add(recipes);
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("code", 0);
            jsonObject.put("msg", "数据库操作错误");
            return jsonObject;
        }
        if (res) jsonObject.put("code", 1);
        else jsonObject.put("code", 0);
        return jsonObject;
    }

    //删除
    @ResponseBody
    @RequestMapping(value = "/recipes/del", method = RequestMethod.DELETE)
    public Object del(@RequestParam("id") Integer id) {
        JSONObject jsonObject = new JSONObject();
        if (id == null) jsonObject.put("code", 0);
        else if (recipesService.del(id)) {
            jsonObject.put("code", 1);
        } else {
            jsonObject.put("code", 0);
        }
        return jsonObject;
    }

    //修改
    @ResponseBody
    @RequestMapping(value = "/recipes/edit", method = RequestMethod.POST)
    public Object upd(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        Recipes recipes;

        try {
            recipes = getRecipesByReq(req);
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("code", 0);
            jsonObject.put("msg", "数据转换错误");
            return jsonObject;
        }

        boolean res = false;
        try {
            res = recipesService.upd(recipes);
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("code", 0);
            jsonObject.put("msg", "数据库操作错误");
            return jsonObject;
        }
        if (res) jsonObject.put("code", 1);
        else jsonObject.put("code", 0);
        return jsonObject;
    }

    private Recipes getRecipesByReq(HttpServletRequest req) throws Exception {
        Recipes recipes = new Recipes();

        String idS = Httpreq.getString(req, "id");
        recipes.setId(idS == null ? null : Integer.valueOf(idS));

        String customerIdS = Httpreq.getString(req, "customerId");
        recipes.setCustomerId(customerIdS == null ? null : Integer.valueOf(customerIdS));

        String foodIdS = Httpreq.getString(req, "foodId");
        recipes.setFoodId(foodIdS == null ? null : Integer.valueOf(foodIdS));

        recipes.setType(Httpreq.getString(req, "type"));

        String provideDatS = Httpreq.getString(req, "provideDat");
        if (provideDatS == null) recipes.setProvideDat(null);
        else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            if (simpleDateFormat.parse(provideDatS) == null) {
                date = null;
            } else {
                date = simpleDateFormat.parse(provideDatS);
            }
            recipes.setProvideDat(date);
        }

        String weekS = Httpreq.getString(req, "week");
        recipes.setWeek(weekS == null ? null : Long.valueOf(weekS));

        return recipes;
    }
}
