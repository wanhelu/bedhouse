package com.example.bedhouseserver.mapper;

import com.example.bedhouseserver.POJO.Food;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table food
     *
     * @mbggenerated
     */
    int insert(Food record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table food
     *
     * @mbggenerated
     */
    int insertSelective(Food record);
}