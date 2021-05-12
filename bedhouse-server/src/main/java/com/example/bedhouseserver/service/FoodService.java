package com.example.bedhouseserver.service;

import com.example.bedhouseserver.POJO.Food;

import java.util.List;


public interface FoodService {
    List<Food> allInfo();

    Food infoById(int id);

    List<Food> search(String word);

    boolean add(Food food);

    boolean del(Integer id);

    boolean upd(Food food);
}
