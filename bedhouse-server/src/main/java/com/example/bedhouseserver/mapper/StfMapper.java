package com.example.bedhouseserver.mapper;

import com.example.bedhouseserver.POJO.Stf;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StfMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stf
     *
     * @mbggenerated
     */
    int insert(Stf record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stf
     *
     * @mbggenerated
     */
    int insertSelective(Stf record);

    int loginStatus(@Param("name")String name,@Param("password")String password);
}