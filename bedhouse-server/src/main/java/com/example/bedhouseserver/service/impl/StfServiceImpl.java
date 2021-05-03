package com.example.bedhouseserver.service.impl;

import com.example.bedhouseserver.POJO.Stf;
import com.example.bedhouseserver.mapper.StfMapper;
import com.example.bedhouseserver.service.StfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Pattern;

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
    public List<Stf> search(String word,Integer roleId) {
        Pattern pattern= Pattern.compile("[0-9]*");
        List<Stf> res=new ArrayList<>();
        if(pattern.matcher(word).matches()){
            for(Integer i =1;i<roleId;i++) {
                res.addAll(stfMapper.searchOfNumber(Integer.valueOf(word), i));
                res.addAll(stfMapper.search(word, i));
            }
            LinkedHashSet<Stf> hashSet= new LinkedHashSet<>(res);
            res=new ArrayList<>(hashSet);
        }else{
            for(Integer i=1;i<roleId;i++) {
                res.addAll(stfMapper.search(word, i));
            }
        }
        return res;
    }

    @Override
    public boolean add(Stf stf) throws DataAccessException {
        return stfMapper.insertSelective(stf)>0;
    }

    @Override
    public boolean del(Integer id) {
        return stfMapper.deleteByPrimaryKey(id)>0;
    }

    @Override
    public boolean upd(Stf stf) throws DataAccessException{
        return stfMapper.updateByPrimaryKeySelective(stf)>0;
    }
}
