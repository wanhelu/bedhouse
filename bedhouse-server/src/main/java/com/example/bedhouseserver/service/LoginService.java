package com.example.bedhouseserver.service;

import com.example.bedhouseserver.POJO.Stf;

public interface LoginService {
    Stf loginStatus(String name, String password);
}
