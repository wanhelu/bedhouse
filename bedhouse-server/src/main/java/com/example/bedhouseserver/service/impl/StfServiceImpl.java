package com.example.bedhouseserver.service.impl;

import com.example.bedhouseserver.POJO.Stf;
import com.example.bedhouseserver.mapper.StfMapper;
import com.example.bedhouseserver.service.StfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StfServiceImpl implements StfService {
    @Autowired
    StfMapper stfMapper;

    @Override
    public List<Stf> stfInfoLessRoleId(Integer roleId) {
        List<Stf> stf=new ArrayList<>();
        for(Integer i=1;i<roleId;i++){
            stf.addAll(stfMapper.selectByRoleId(i));
        }
        return stf;
    }

    @Override
    public Stf stfInfoById(Integer id) {
        return stfMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateSelective(Stf stf) {
        return stfMapper.updateByPrimaryKeySelective(stf)>0;
    }

    @Override
    public List<Stf> search(String word) {
        return stfMapper.search(word);
    }
}
