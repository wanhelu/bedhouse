package com.example.bedhouseserver.service;

import com.example.bedhouseserver.POJO.Stf;
import org.springframework.dao.DataAccessException;

import java.sql.SQLException;
import java.util.Date;

public interface LoginService  {
    Stf loginStatus(String name, String password);

    boolean updateStatus(Integer id,Date date,String add);
}
