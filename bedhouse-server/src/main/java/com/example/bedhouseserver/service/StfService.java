package com.example.bedhouseserver.service;

import com.example.bedhouseserver.POJO.Stf;

import java.util.List;

public interface StfService {
    List<Stf> stfInfoLessRoleId(Integer roleId);

    Stf stfInfoById(Integer id);

    boolean updateSelective(Stf stf);

    List<Stf>search(String word,Integer roleId);

    boolean add(Stf stf);
}
