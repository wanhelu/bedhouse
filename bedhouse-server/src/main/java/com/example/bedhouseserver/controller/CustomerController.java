package com.example.bedhouseserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.bedhouseserver.POJO.Customer;
import com.example.bedhouseserver.myUtil.Httpreq;
import com.example.bedhouseserver.service.impl.CustomerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Controller
public class CustomerController {
    @Autowired
    CustomerServiceImpl customerService;

    private final Logger log= LoggerFactory.getLogger(this.getClass());

    //获取所有客户信息
    @ResponseBody
    @RequestMapping(value = "/customer/info", method = RequestMethod.GET)
    public Object allCustomerInfo(){
        return customerService.allCustomerInfo();
    }

    //获取所有客户信息
    @ResponseBody
    @RequestMapping(value = "/customer/search", method = RequestMethod.GET)
    public Object search(@RequestParam("word")String word){
        return customerService.search(word);
    }

    //添加客户信息
    @ResponseBody
    @RequestMapping(value = "/customer/add", method = RequestMethod.POST)
    public Object add(HttpServletRequest req){
        JSONObject jsonObject =new JSONObject();
        Customer customer;

        try {
            customer=getCustomerByReq(req);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("code",0);
            jsonObject.put("msg","数据转换错误");
            return jsonObject;
        }

        boolean res=false;
        try {
            res=customerService.add(customer);
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

    //删除客户信息
    @ResponseBody
    @RequestMapping(value = "/customer/del", method = RequestMethod.DELETE)
    public Object del(@RequestParam("id")Integer id){
        JSONObject jsonObject=new JSONObject();
        if(id==null)jsonObject.put("code",0);
        else if(customerService.del(id)){
            jsonObject.put("code",1);
        }
        else{
            jsonObject.put("code",0);
        }
        return jsonObject;
    }

    //修改客户信息
    @ResponseBody
    @RequestMapping(value = "/customer/edit", method = RequestMethod.POST)
    public Object upd(HttpServletRequest req){
        JSONObject jsonObject=new JSONObject();
        Customer customer;

        try {
            customer=getCustomerByReq(req);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("code",0);
            jsonObject.put("msg","数据转换错误");
            return jsonObject;
        }

        boolean res=false;
        try {
            res=customerService.upd(customer);
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

    private Customer getCustomerByReq(HttpServletRequest req)throws Exception{
        Customer customer=new Customer();

        String idS= Httpreq.getString(req,"id");
        if(idS==null||idS.equals(""))customer.setId(null);
        else customer.setId(Integer.valueOf(idS));

        customer.setName(Httpreq.getString(req,"name"));

        customer.setGender(Httpreq.getString(req,"gender"));

        String ageS= Httpreq.getString(req,"age");
        if(ageS==null||ageS.equals(""))customer.setAge(null);
        else customer.setAge(Integer.valueOf(ageS));

        return customer;
    }
}
