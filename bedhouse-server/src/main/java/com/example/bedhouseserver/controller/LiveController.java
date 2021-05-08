package com.example.bedhouseserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.bedhouseserver.POJO.Live;
import com.example.bedhouseserver.myUtil.Httpreq;
import com.example.bedhouseserver.service.impl.LiveServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

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

    //获取所有入住信息
    @ResponseBody
    @RequestMapping(value = "/live/info", method = RequestMethod.GET)
    public Object allLiveInfo(){
        return liveService.allLiveInfo();
    }

    //搜索
    @ResponseBody
    @RequestMapping(value = "/live/search", method = RequestMethod.POST)
    public Object search(HttpServletRequest req){
        String word= Httpreq.getString(req,"word");
        String fromDate=Httpreq.getString(req,"fromDate");
        String toDate=Httpreq.getString(req,"toDate");
        Integer num;
        Pattern pattern= Pattern.compile("[0-9]*");
        if(word!=null&&pattern.matcher(word).matches()){
            num=Integer.valueOf(word);
        }else{
            num=null;
        }
        return liveService.search(num,fromDate,toDate);
    }

    //新增
    @ResponseBody
    @RequestMapping(value = "/live/add", method = RequestMethod.POST)
    public Object add(HttpServletRequest req){
        JSONObject jsonObject=new JSONObject();
        Live live=new Live();

        try {
            live=getLiveByReq(req);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("code",0);
            jsonObject.put("msg","数据转换错误");
            return jsonObject;
        }
        live.setBeginDate(new Date());

        boolean res=false;

        try {
            res= liveService.add(live);
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


    //修改
    @ResponseBody
    @RequestMapping(value = "/live/edit", method = RequestMethod.POST)
    public Object upd(HttpServletRequest req){
        JSONObject jsonObject=new JSONObject();
        Live live;

        try {
            live=getLiveByReq(req);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("code",0);
            jsonObject.put("msg","数据转换错误");
            return jsonObject;
        }

        boolean res=false;
        try {
            res=liveService.upd(live);
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

    //删除入住信息
    @ResponseBody
    @RequestMapping(value = "/live/del", method = RequestMethod.DELETE)
    public Object del(@RequestParam("id")Integer id){
        JSONObject jsonObject=new JSONObject();
        if(id==null)jsonObject.put("code",0);
        else if(liveService.del(id)){
            jsonObject.put("code",1);
        }
        else{
            jsonObject.put("code",0);
        }
        return jsonObject;
    }

    //退住
    @ResponseBody
    @RequestMapping(value = "/live/leave", method = RequestMethod.GET)
    public Object leave(@RequestParam("id")Integer id){
        JSONObject jsonObject=new JSONObject();
        if(id==null){
            jsonObject.put("code",0);
            jsonObject.put("msg","id为空");
            return jsonObject;
        }
        boolean res=liveService.leave(id);

        if(res){
            jsonObject.put("code",1);
        }else{
            jsonObject.put("code",0);
        }
        return jsonObject;
    }

    private Live getLiveByReq(HttpServletRequest req)throws Exception{
        Live live=new Live();

        String idS= Httpreq.getString(req,"id");
        if(idS==null)live.setId(null);
        else live.setId(Integer.valueOf(idS));

        String bedIdS=Httpreq.getString(req,"bedId");
        live.setBedId(bedIdS==null?null:Integer.valueOf(bedIdS));

        String customerIdS=Httpreq.getString(req,"customerId");
        live.setCustomerId(customerIdS==null?null:Integer.valueOf(customerIdS));

        String beginDateS=Httpreq.getString(req,"beginDate");
        if(beginDateS==null)live.setBeginDate(null);
        else{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            if (simpleDateFormat.parse(beginDateS) == null) {
                date = null;
            } else {
                date = simpleDateFormat.parse(beginDateS);
            }
            live.setBeginDate(date);
        }

        String leaveDateS=Httpreq.getString(req,"leaveDate");
        if(leaveDateS==null)live.setLeaveDate(null);
        else{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            if (simpleDateFormat.parse(leaveDateS) == null) {
                date = null;
            } else {
                date = simpleDateFormat.parse(leaveDateS);
            }
            live.setLeaveDate(date);
        }

        return live;
    }
}
