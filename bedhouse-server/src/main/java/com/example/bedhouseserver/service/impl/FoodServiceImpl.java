package com.example.bedhouseserver.service.impl;

import com.example.bedhouseserver.POJO.Food;
import com.example.bedhouseserver.mapper.FoodMapper;
import com.example.bedhouseserver.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    FoodMapper foodMapper;

    @Override
    public List<Food> allInfo() {
        return foodMapper.allInfo();
    }
}
