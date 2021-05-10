package com.example.bedhouseserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.bedhouseserver.POJO.OutRecord;
import com.example.bedhouseserver.myUtil.Httpreq;
import com.example.bedhouseserver.service.impl.OutServiceImpl;
import org.apache.ibatis.annotations.Param;
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
public class OutController {
    @Autowired
    OutServiceImpl outService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //获取所有外出信息
    @ResponseBody
    @RequestMapping(value = "/out/info", method = RequestMethod.GET)
    public Object allOutInfo() {
        return outService.allOutInfo();
    }

    //查询
    @ResponseBody
    @RequestMapping(value = "/out/search", method = RequestMethod.GET)
    public Object search(HttpServletRequest req) {
        return outService.search(Httpreq.getString(req, "word"), Httpreq.getString(req, "date"));
    }

    //新增
    @ResponseBody
    @RequestMapping(value = "/out/add", method = RequestMethod.POST)
    public Object add(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        OutRecord outRecord;

        try {
            outRecord = getOutRecordByReq(req);
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("code", 0);
            jsonObject.put("msg", "数据转换错误");
            return jsonObject;
        }
        outRecord.setSubmitTime(new Date());

        boolean res = false;
        try {
            res = outService.add(outRecord);
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

    //修改
    @ResponseBody
    @RequestMapping(value = "/out/edit", method = RequestMethod.POST)
    public Object upd(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        OutRecord outRecord;

        try {
            outRecord = getOutRecordByReq(req);
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("code", 0);
            jsonObject.put("msg", "数据转换错误");
            return jsonObject;
        }

        boolean res = false;
        try {
            res = outService.upd(outRecord);
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
    @RequestMapping(value = "/out/del", method = RequestMethod.DELETE)
    public Object del(@RequestParam("id") Integer id) {
        JSONObject jsonObject = new JSONObject();
        if (id == null) jsonObject.put("code", 0);
        else if (outService.del(id)) {
            jsonObject.put("code", 1);
        } else {
            jsonObject.put("code", 0);
        }
        return jsonObject;
    }

    //外出
    @ResponseBody
    @RequestMapping(value = "/out/goOut", method = RequestMethod.GET)
    public Object goOut(@RequestParam("id") Integer id) {
        JSONObject jsonObject = new JSONObject();
        if (id == null) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "id为空");
            return jsonObject;
        } else if (outService.goOut(id)) {
            jsonObject.put("code", 1);
        } else {
            jsonObject.put("code", 0);
        }
        return jsonObject;
    }

    //返回
    @ResponseBody
    @RequestMapping(value = "/out/goBack", method = RequestMethod.GET)
    public Object goBack(@RequestParam("id") Integer id) {
        JSONObject jsonObject = new JSONObject();
        if (id == null) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "id为空");
            return jsonObject;
        } else if (outService.goBack(id)) {
            jsonObject.put("code", 1);
        } else {
            jsonObject.put("code", 0);
        }
        return jsonObject;
    }

    private OutRecord getOutRecordByReq(HttpServletRequest req) throws Exception {
        OutRecord outRecord = new OutRecord();

        String idS = Httpreq.getString(req, "id");
        outRecord.setId(idS == null ? null : Integer.valueOf(idS));

        String stfIdS = Httpreq.getString(req, "stfId");
        outRecord.setStfId(stfIdS == null ? null : Integer.valueOf(stfIdS));

        String customerId = Httpreq.getString(req, "customerId");
        outRecord.setCustomerId(customerId == null ? null : Integer.valueOf(customerId));

        outRecord.setSubmitTime(getDateByString(Httpreq.getString(req, "submitTime")));
        outRecord.setOutTime(getDateByString(Httpreq.getString(req, "outTime")));
        outRecord.setForcastBac(getDateByString(Httpreq.getString(req, "forcastBac")));
        outRecord.setBackTime(getDateByString(Httpreq.getString(req, "backTime")));
        outRecord.setText(Httpreq.getString(req, "text"));

        String stateS = Httpreq.getString(req, "state");
        outRecord.setState(stateS == null ? null : Long.valueOf(stateS));

        String checkerIdS = Httpreq.getString(req, "checkerId");
        outRecord.setCheckerId(checkerIdS == null ? null : Integer.valueOf(checkerIdS));

        outRecord.setCheckTime(getDateByString(Httpreq.getString(req, "checkTime")));

        return outRecord;
    }

    private Date getDateByString(String S) throws Exception {
        if (S == null) return null;
        else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date;
            if (simpleDateFormat.parse(S) == null) {
                date = null;
            } else {
                date = simpleDateFormat.parse(S);
            }
            return date;
        }
    }
}
