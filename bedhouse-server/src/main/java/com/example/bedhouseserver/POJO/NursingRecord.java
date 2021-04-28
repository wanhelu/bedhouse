package com.example.bedhouseserver.POJO;

import java.util.Date;

public class NursingRecord {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column nursing_record.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column nursing_record.stf_id
     *
     * @mbggenerated
     */
    private Integer stfId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column nursing_record.customer_id
     *
     * @mbggenerated
     */
    private Integer customerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column nursing_record.nursing_con
     *
     * @mbggenerated
     */
    private Integer nursingCon;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column nursing_record.date
     *
     * @mbggenerated
     */
    private Date date;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column nursing_record.id
     *
     * @return the value of nursing_record.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column nursing_record.id
     *
     * @param id the value for nursing_record.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column nursing_record.stf_id
     *
     * @return the value of nursing_record.stf_id
     *
     * @mbggenerated
     */
    public Integer getStfId() {
        return stfId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column nursing_record.stf_id
     *
     * @param stfId the value for nursing_record.stf_id
     *
     * @mbggenerated
     */
    public void setStfId(Integer stfId) {
        this.stfId = stfId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column nursing_record.customer_id
     *
     * @return the value of nursing_record.customer_id
     *
     * @mbggenerated
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column nursing_record.customer_id
     *
     * @param customerId the value for nursing_record.customer_id
     *
     * @mbggenerated
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column nursing_record.nursing_con
     *
     * @return the value of nursing_record.nursing_con
     *
     * @mbggenerated
     */
    public Integer getNursingCon() {
        return nursingCon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column nursing_record.nursing_con
     *
     * @param nursingCon the value for nursing_record.nursing_con
     *
     * @mbggenerated
     */
    public void setNursingCon(Integer nursingCon) {
        this.nursingCon = nursingCon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column nursing_record.date
     *
     * @return the value of nursing_record.date
     *
     * @mbggenerated
     */
    public Date getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column nursing_record.date
     *
     * @param date the value for nursing_record.date
     *
     * @mbggenerated
     */
    public void setDate(Date date) {
        this.date = date;
    }
}