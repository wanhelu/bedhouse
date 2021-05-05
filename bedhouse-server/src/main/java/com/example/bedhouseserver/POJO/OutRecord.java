package com.example.bedhouseserver.POJO;

import java.util.Date;
import java.util.Objects;

public class OutRecord {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column out_record.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column out_record.stf_id
     *
     * @mbggenerated
     */
    private Integer stfId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column out_record.customer_id
     *
     * @mbggenerated
     */
    private Integer customerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column out_record.submit_time
     *
     * @mbggenerated
     */
    private Date submitTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column out_record.out_time
     *
     * @mbggenerated
     */
    private Date outTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column out_record.forcast_bac
     *
     * @mbggenerated
     */
    private Date forcastBac;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column out_record.back_time
     *
     * @mbggenerated
     */
    private Date backTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column out_record.text
     *
     * @mbggenerated
     */
    private String text;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column out_record.state
     *
     * @mbggenerated
     */
    private Long state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column out_record.checker_id
     *
     * @mbggenerated
     */
    private Integer checkerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column out_record.check_time
     *
     * @mbggenerated
     */
    private Date checkTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column out_record.id
     *
     * @return the value of out_record.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column out_record.id
     *
     * @param id the value for out_record.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column out_record.stf_id
     *
     * @return the value of out_record.stf_id
     *
     * @mbggenerated
     */
    public Integer getStfId() {
        return stfId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column out_record.stf_id
     *
     * @param stfId the value for out_record.stf_id
     *
     * @mbggenerated
     */
    public void setStfId(Integer stfId) {
        this.stfId = stfId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column out_record.customer_id
     *
     * @return the value of out_record.customer_id
     *
     * @mbggenerated
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column out_record.customer_id
     *
     * @param customerId the value for out_record.customer_id
     *
     * @mbggenerated
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column out_record.submit_time
     *
     * @return the value of out_record.submit_time
     *
     * @mbggenerated
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column out_record.submit_time
     *
     * @param submitTime the value for out_record.submit_time
     *
     * @mbggenerated
     */
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column out_record.out_time
     *
     * @return the value of out_record.out_time
     *
     * @mbggenerated
     */
    public Date getOutTime() {
        return outTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column out_record.out_time
     *
     * @param outTime the value for out_record.out_time
     *
     * @mbggenerated
     */
    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column out_record.forcast_bac
     *
     * @return the value of out_record.forcast_bac
     *
     * @mbggenerated
     */
    public Date getForcastBac() {
        return forcastBac;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column out_record.forcast_bac
     *
     * @param forcastBac the value for out_record.forcast_bac
     *
     * @mbggenerated
     */
    public void setForcastBac(Date forcastBac) {
        this.forcastBac = forcastBac;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column out_record.back_time
     *
     * @return the value of out_record.back_time
     *
     * @mbggenerated
     */
    public Date getBackTime() {
        return backTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column out_record.back_time
     *
     * @param backTime the value for out_record.back_time
     *
     * @mbggenerated
     */
    public void setBackTime(Date backTime) {
        this.backTime = backTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column out_record.text
     *
     * @return the value of out_record.text
     *
     * @mbggenerated
     */
    public String getText() {
        return text;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column out_record.text
     *
     * @param text the value for out_record.text
     *
     * @mbggenerated
     */
    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column out_record.state
     *
     * @return the value of out_record.state
     *
     * @mbggenerated
     */
    public Long getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column out_record.state
     *
     * @param state the value for out_record.state
     *
     * @mbggenerated
     */
    public void setState(Long state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column out_record.checker_id
     *
     * @return the value of out_record.checker_id
     *
     * @mbggenerated
     */
    public Integer getCheckerId() {
        return checkerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column out_record.checker_id
     *
     * @param checkerId the value for out_record.checker_id
     *
     * @mbggenerated
     */
    public void setCheckerId(Integer checkerId) {
        this.checkerId = checkerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column out_record.check_time
     *
     * @return the value of out_record.check_time
     *
     * @mbggenerated
     */
    public Date getCheckTime() {
        return checkTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column out_record.check_time
     *
     * @param checkTime the value for out_record.check_time
     *
     * @mbggenerated
     */
    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutRecord outRecord = (OutRecord) o;
        return Objects.equals(id, outRecord.id) && Objects.equals(stfId, outRecord.stfId) && Objects.equals(customerId, outRecord.customerId) && Objects.equals(submitTime, outRecord.submitTime) && Objects.equals(outTime, outRecord.outTime) && Objects.equals(forcastBac, outRecord.forcastBac) && Objects.equals(backTime, outRecord.backTime) && Objects.equals(text, outRecord.text) && Objects.equals(state, outRecord.state) && Objects.equals(checkerId, outRecord.checkerId) && Objects.equals(checkTime, outRecord.checkTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stfId, customerId, submitTime, outTime, forcastBac, backTime, text, state, checkerId, checkTime);
    }
}