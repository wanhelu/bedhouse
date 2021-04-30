package com.example.bedhouseserver.controller;

import com.example.bedhouseserver.service.StfService;
import com.example.bedhouseserver.service.impl.StfServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
}
