package com.example.bedhouseserver.mapper;

import com.example.bedhouseserver.POJO.NursingRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface NursingRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nursing_record
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nursing_record
     *
     * @mbggenerated
     */
    int insert(NursingRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nursing_record
     *
     * @mbggenerated
     */
    int insertSelective(NursingRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nursing_record
     *
     * @mbggenerated
     */
    NursingRecord selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nursing_record
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(NursingRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nursing_record
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(NursingRecord record);
}