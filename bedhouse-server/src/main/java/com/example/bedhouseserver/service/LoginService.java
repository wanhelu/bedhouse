package com.example.bedhouseserver.service;

import com.example.bedhouseserver.POJO.Stf;
import org.springframework.dao.DataAccessException;

import java.sql.SQLException;

public interface LoginService  {
    Stf loginStatus(String name, String password) throws DataAccessException;
}
