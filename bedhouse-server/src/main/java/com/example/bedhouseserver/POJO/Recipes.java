package com.example.bedhouseserver.POJO;

import java.util.Date;
import java.util.Objects;

public class Recipes {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recipes.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recipes.customer_id
     *
     * @mbggenerated
     */
    private Integer customerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recipes.food_id
     *
     * @mbggenerated
     */
    private Integer foodId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recipes.type
     *
     * @mbggenerated
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recipes.provide_dat
     *
     * @mbggenerated
     */
    private Date provideDat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recipes.week
     *
     * @mbggenerated
     */
    private Long week;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recipes.id
     *
     * @return the value of recipes.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recipes.id
     *
     * @param id the value for recipes.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recipes.customer_id
     *
     * @return the value of recipes.customer_id
     *
     * @mbggenerated
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recipes.customer_id
     *
     * @param customerId the value for recipes.customer_id
     *
     * @mbggenerated
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recipes.food_id
     *
     * @return the value of recipes.food_id
     *
     * @mbggenerated
     */
    public Integer getFoodId() {
        return foodId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recipes.food_id
     *
     * @param foodId the value for recipes.food_id
     *
     * @mbggenerated
     */
    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recipes.type
     *
     * @return the value of recipes.type
     *
     * @mbggenerated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recipes.type
     *
     * @param type the value for recipes.type
     *
     * @mbggenerated
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recipes.provide_dat
     *
     * @return the value of recipes.provide_dat
     *
     * @mbggenerated
     */
    public Date getProvideDat() {
        return provideDat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recipes.provide_dat
     *
     * @param provideDat the value for recipes.provide_dat
     *
     * @mbggenerated
     */
    public void setProvideDat(Date provideDat) {
        this.provideDat = provideDat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recipes.week
     *
     * @return the value of recipes.week
     *
     * @mbggenerated
     */
    public Long getWeek() {
        return week;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recipes.week
     *
     * @param week the value for recipes.week
     *
     * @mbggenerated
     */
    public void setWeek(Long week) {
        this.week = week;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipes recipes = (Recipes) o;
        return Objects.equals(id, recipes.id) && Objects.equals(customerId, recipes.customerId) && Objects.equals(foodId, recipes.foodId) && Objects.equals(type, recipes.type) && Objects.equals(provideDat, recipes.provideDat) && Objects.equals(week, recipes.week);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, foodId, type, provideDat, week);
    }
}