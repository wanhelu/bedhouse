package com.example.bedhouseserver.mapper;

import com.example.bedhouseserver.POJO.Live;
import org.springframework.stereotype.Repository;

@Repository
public interface LiveMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table live
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table live
     *
     * @mbggenerated
     */
    int insert(Live record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table live
     *
     * @mbggenerated
     */
    int insertSelective(Live record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table live
     *
     * @mbggenerated
     */
    Live selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table live
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Live record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table live
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Live record);
}