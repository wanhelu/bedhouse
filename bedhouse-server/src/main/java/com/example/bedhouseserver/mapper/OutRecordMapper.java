package com.example.bedhouseserver.mapper;

import com.example.bedhouseserver.POJO.OutRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface OutRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table out_record
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table out_record
     *
     * @mbggenerated
     */
    int insert(OutRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table out_record
     *
     * @mbggenerated
     */
    int insertSelective(OutRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table out_record
     *
     * @mbggenerated
     */
    OutRecord selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table out_record
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(OutRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table out_record
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(OutRecord record);
}