package com.example.bedhouseserver.mapper;

import com.example.bedhouseserver.POJO.NursingLevel;
import org.springframework.stereotype.Repository;

@Repository
public interface NursingLevelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nursing_level
     *
     * @mbggenerated
     */
    int insert(NursingLevel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nursing_level
     *
     * @mbggenerated
     */
    int insertSelective(NursingLevel record);
}