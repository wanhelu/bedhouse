package com.example.bedhouseserver.mapper;

import com.example.bedhouseserver.POJO.Bed;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BedMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bed
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bed
     *
     * @mbggenerated
     */
    int insert(Bed record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bed
     *
     * @mbggenerated
     */
    int insertSelective(Bed record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bed
     *
     * @mbggenerated
     */
    Bed selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bed
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Bed record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bed
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Bed record);

    List<Bed> allInfo();

    int bedUsed(@Param("id")Integer id);

    List<Bed> searchByDetail(@Param("word")String word);

    List<Bed> searchByNum(@Param("num")Integer num);
}