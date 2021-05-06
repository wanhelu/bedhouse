package com.example.bedhouseserver.controller;

import com.example.bedhouseserver.service.impl.CustomerServiceImpl;
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
}
