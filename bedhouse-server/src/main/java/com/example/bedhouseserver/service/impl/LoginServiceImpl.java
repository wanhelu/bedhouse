package com.example.bedhouseserver.service.impl;

import com.example.bedhouseserver.POJO.Stf;
import com.example.bedhouseserver.mapper.StfMapper;
import com.example.bedhouseserver.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    StfMapper stfMapper;

    private final Logger log= LoggerFactory.getLogger(this.getClass());

    @Override
    public Stf loginStatus(String name, String password) {
        return stfMapper.loginStatus(name,password);
    }
}
