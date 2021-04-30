package com.example.bedhouseserver.mapper;

import com.example.bedhouseserver.POJO.Stf;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StfMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stf
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

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

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stf
     *
     * @mbggenerated
     */
    Stf selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stf
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Stf record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stf
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Stf record);

    Stf loginStatus(@Param("name")String name,@Param("password")String password);

    List<Stf> selectByRoleId(@Param("roleId")Integer roleId);
}