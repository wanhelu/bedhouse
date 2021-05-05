package com.example.bedhouseserver.POJO;

import java.util.Objects;

public class NursingContent {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column nursing_content.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column nursing_content.content
     *
     * @mbggenerated
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column nursing_content.title
     *
     * @mbggenerated
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column nursing_content.price
     *
     * @mbggenerated
     */
    private Double price;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column nursing_content.id
     *
     * @return the value of nursing_content.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column nursing_content.id
     *
     * @param id the value for nursing_content.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column nursing_content.content
     *
     * @return the value of nursing_content.content
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column nursing_content.content
     *
     * @param content the value for nursing_content.content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column nursing_content.title
     *
     * @return the value of nursing_content.title
     *
     * @mbggenerated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column nursing_content.title
     *
     * @param title the value for nursing_content.title
     *
     * @mbggenerated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column nursing_content.price
     *
     * @return the value of nursing_content.price
     *
     * @mbggenerated
     */
    public Double getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column nursing_content.price
     *
     * @param price the value for nursing_content.price
     *
     * @mbggenerated
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NursingContent that = (NursingContent) o;
        return Objects.equals(id, that.id) && Objects.equals(content, that.content) && Objects.equals(title, that.title) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, title, price);
    }
}