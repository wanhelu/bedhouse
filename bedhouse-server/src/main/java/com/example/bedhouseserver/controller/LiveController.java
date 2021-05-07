package com.example.bedhouseserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.bedhouseserver.service.impl.LiveServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
public class LiveController {
    @Autowired
    LiveServiceImpl liveService;

    private final Logger log= LoggerFactory.getLogger(this.getClass());

    //获取指定客户所使用的床位
    @ResponseBody
    @RequestMapping(value = "/live/useBed", method = RequestMethod.GET)
    public Object useBed(@RequestParam("id")Integer id){
        JSONObject jsonObject=new JSONObject();

        Integer bedId= liveService.useBed(id);
        jsonObject.put("code",bedId==null?0:1);
        jsonObject.put("bedId",bedId);
        return jsonObject;
    }

    @ResponseBody
    @RequestMapping(value = "/live/info", method = RequestMethod.GET)
    public Object allLiveInfo(){
        return liveService.allLiveInfo();
    }
}
