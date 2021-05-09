package com.example.bedhouseserver.controller;

import com.example.bedhouseserver.service.FoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class FoodController {
    @Autowired
    FoodService foodService;

    private final Logger log= LoggerFactory.getLogger(this.getClass());

    //获取所有信息
    @ResponseBody
    @RequestMapping(value = "/food/info", method = RequestMethod.GET)
    public Object allInfo(){
        return foodService.allInfo();
    }
}
