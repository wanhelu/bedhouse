package com.example.bedhouseserver.service.impl;

import com.example.bedhouseserver.POJO.Food;
import com.example.bedhouseserver.mapper.FoodMapper;
import com.example.bedhouseserver.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    FoodMapper foodMapper;

    @Override
    public List<Food> allInfo() {
        return foodMapper.allInfo();
    }

    @Override
    public List<Food> search(String word) {
        Pattern pattern= Pattern.compile("[0-9]*");
        List<Food> res;
        if(pattern.matcher(word).matches()){
            res=foodMapper.searchByNum(Integer.valueOf(word));
            res.addAll(foodMapper.searchByString(word));
        }else{
            res=foodMapper.searchByString(word);
        }
        LinkedHashSet<Food> hashSet=new LinkedHashSet<>(res);
        return new ArrayList<>(hashSet);
    }

    @Override
    public boolean add(Food food) {
        return foodMapper.insertSelective(food)>0;
    }

    @Override
    public boolean del(Integer id) {
        return foodMapper.deleteByPrimaryKey(id)>0;
    }

    @Override
    public boolean upd(Food food) {
        return foodMapper.updateByPrimaryKeySelective(food)>0;
    }
}
