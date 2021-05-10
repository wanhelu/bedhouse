package com.example.bedhouseserver.service.impl;

import com.example.bedhouseserver.POJO.Recipes;
import com.example.bedhouseserver.mapper.RecipesMapper;
import com.example.bedhouseserver.service.RecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class RecipesServiceImpl implements RecipesService {
    @Autowired
    RecipesMapper recipesMapper;

    @Override
    public List<Recipes> allInfo() {
        return recipesMapper.allInfo();
    }

    @Override
    public List<Recipes> search(String word) {
        Pattern pattern = Pattern.compile("[0-9]*");
        List<Recipes> res;
        if (pattern.matcher(word).matches()) {
            res = recipesMapper.searchByNum(Integer.valueOf(word));
            res.addAll(recipesMapper.searchByString(word));
        } else {
            res = recipesMapper.searchByString(word);
        }
        LinkedHashSet<Recipes> hashSet = new LinkedHashSet<>(res);
        return new ArrayList<>(hashSet);
    }

    @Override
    public boolean add(Recipes recipes) {
        return recipesMapper.insertSelective(recipes) > 0;
    }

    @Override
    public boolean del(int id) {
        return recipesMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean upd(Recipes recipes) {
        return recipesMapper.updateByPrimaryKeySelective(recipes) > 0;
    }
}
