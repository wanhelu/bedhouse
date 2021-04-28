package com.example.bedhouseserver.service.impl;

import com.example.bedhouseserver.mapper.StfMapper;
import com.example.bedhouseserver.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    StfMapper stfMapper;

    @Override
    public int loginStatus(String name, String password) {
        return stfMapper.loginStatus(name,password);
    }
}
