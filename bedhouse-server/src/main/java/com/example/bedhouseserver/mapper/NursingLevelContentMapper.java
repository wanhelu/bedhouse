package com.example.bedhouseserver.mapper;

import com.example.bedhouseserver.POJO.NursingLevelContent;
import org.springframework.stereotype.Repository;

@Repository
public interface NursingLevelContentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nursing_level_content
     *
     * @mbggenerated
     */
    int insert(NursingLevelContent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nursing_level_content
     *
     * @mbggenerated
     */
    int insertSelective(NursingLevelContent record);
}