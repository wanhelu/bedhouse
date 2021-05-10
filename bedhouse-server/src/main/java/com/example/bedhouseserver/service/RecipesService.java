package com.example.bedhouseserver.service;

import com.example.bedhouseserver.POJO.Recipes;
import com.sun.org.apache.regexp.internal.RE;

import java.util.List;

public interface RecipesService {
    List<Recipes> allInfo();

    List<Recipes> search(String word);

    boolean add(Recipes recipes);

    boolean del(int id);

    boolean upd(Recipes recipes);
}
