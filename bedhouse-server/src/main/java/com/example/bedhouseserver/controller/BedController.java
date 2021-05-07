package com.example.bedhouseserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.bedhouseserver.POJO.Bed;
import com.example.bedhouseserver.myUtil.Httpreq;
import com.example.bedhouseserver.service.impl.BedServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Controller
public class BedController {
    @Autowired
    BedServiceImpl bedService;

    private final Logger log= LoggerFactory.getLogger(this.getClass());

    //获取所有员工信息
    @ResponseBody
    @RequestMapping(value = "/bed/info", method = RequestMethod.GET)
    public Object allBedInfo(){
        return bedService.allBedInfo();
    }

    //获取床位占用信息
    @ResponseBody
    @RequestMapping(value = "/bed/usedInfo", method = RequestMethod.GET)
    public Object bedUsedInfo(@RequestParam("id")Integer id){
        JSONObject jsonObject=new JSONObject();
        if(bedService.bedUsedInfo(id)){
            jsonObject.put("used",1);
        }
        else{
            jsonObject.put("used",0);
        }
        jsonObject.put("code",1);
        return jsonObject;
    }
    //查找
    @ResponseBody
    @RequestMapping(value = "/bed/search", method = RequestMethod.GET)
    public Object search(@RequestParam("word")String word){
        return bedService.search(word);
    }

    //添加
    @ResponseBody
    @RequestMapping(value = "/bed/add", method = RequestMethod.POST)
    public Object add(HttpServletRequest req){
        JSONObject jsonObject=new JSONObject();
        Bed bed;

        try{
            bed=getBedByReq(req);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("code",0);
            jsonObject.put("msg","数据转换错误");
            return jsonObject;
        }

        boolean res=false;

        try{
            res= bedService.add(bed);
        } catch (Exception e){
            e.printStackTrace();
            jsonObject.put("code",0);
            jsonObject.put("msg","数据库操作错误");
            return jsonObject;
        }
        if(res)jsonObject.put("code",1);
        else jsonObject.put("code",0);
        return jsonObject;
    }

    //修改
    @ResponseBody
    @RequestMapping(value = "/bed/edit", method = RequestMethod.POST)
    public Object upd(HttpServletRequest req){
        JSONObject jsonObject=new JSONObject();
        Bed bed;

        try{
            bed=getBedByReq(req);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("code",0);
            jsonObject.put("msg","数据转换错误");
            return jsonObject;
        }

        boolean res=false;

        try{
            res= bedService.upd(bed);
        } catch (Exception e){
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
    @RequestMapping(value = "/bed/delete", method = RequestMethod.DELETE)
    public Object del(@RequestParam("id")Integer id){
        JSONObject jsonObject=new JSONObject();
        if(id==null)jsonObject.put("code",0);
        else if(bedService.del(id)){
            jsonObject.put("code",1);
        }
        else{
            jsonObject.put("code",0);
        }
        return jsonObject;
    }

    private Bed getBedByReq(HttpServletRequest req) throws Exception{
        Bed bed=new Bed();

        String idS=Httpreq.getString(req,"id");
        if(idS==null||idS.equals(""))bed.setId(null);
        else bed.setId(Integer.valueOf(idS));

        String roomIdS=Httpreq.getString(req,"roomId");
        if(roomIdS==null||roomIdS.equals(""))bed.setRoomId(null);
        else bed.setRoomId(Integer.valueOf(roomIdS));

        bed.setDetail(Httpreq.getString(req,"detail"));

        return bed;
    }
}
