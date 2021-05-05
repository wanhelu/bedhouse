package com.example.bedhouseserver.service.impl;

import com.example.bedhouseserver.POJO.Bed;
import com.example.bedhouseserver.mapper.BedMapper;
import com.example.bedhouseserver.service.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class BedServiceImpl implements BedService {
    @Autowired
    BedMapper bedMapper;

    @Override
    public List<Bed> allBedInfo() {
        return bedMapper.allInfo();
    }

    @Override
    public Boolean bedUsedInfo(Integer id) {
        return bedMapper.bedUsed(id)>0;
    }

    @Override
    public List<Bed> search(String word) {
        Pattern pattern= Pattern.compile("[0-9]*");
        List<Bed> res;
        if(pattern.matcher(word).matches()){
            res=bedMapper.searchByNum(Integer.valueOf(word));
            res.addAll(bedMapper.searchByDetail(word));
        }
        else{
            res=bedMapper.searchByDetail(word);
        }
        LinkedHashSet<Bed> hashSet=new LinkedHashSet<>(res);
        return new ArrayList<>(hashSet);
    }

    @Override
    public boolean add(Bed bed) {
        return bedMapper.insertSelective(bed)>0;
    }
}
