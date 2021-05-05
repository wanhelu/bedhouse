package com.example.bedhouseserver.POJO;

import java.util.Date;
import java.util.Objects;

public class ServicePurchase {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column service_purchase.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column service_purchase.customer_id
     *
     * @mbggenerated
     */
    private Integer customerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column service_purchase.nursing_content_id
     *
     * @mbggenerated
     */
    private Integer nursingContentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column service_purchase.purchase_date
     *
     * @mbggenerated
     */
    private Date purchaseDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column service_purchase.effective_t
     *
     * @mbggenerated
     */
    private Date effectiveT;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column service_purchase.id
     *
     * @return the value of service_purchase.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column service_purchase.id
     *
     * @param id the value for service_purchase.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column service_purchase.customer_id
     *
     * @return the value of service_purchase.customer_id
     *
     * @mbggenerated
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column service_purchase.customer_id
     *
     * @param customerId the value for service_purchase.customer_id
     *
     * @mbggenerated
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column service_purchase.nursing_content_id
     *
     * @return the value of service_purchase.nursing_content_id
     *
     * @mbggenerated
     */
    public Integer getNursingContentId() {
        return nursingContentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column service_purchase.nursing_content_id
     *
     * @param nursingContentId the value for service_purchase.nursing_content_id
     *
     * @mbggenerated
     */
    public void setNursingContentId(Integer nursingContentId) {
        this.nursingContentId = nursingContentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column service_purchase.purchase_date
     *
     * @return the value of service_purchase.purchase_date
     *
     * @mbggenerated
     */
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column service_purchase.purchase_date
     *
     * @param purchaseDate the value for service_purchase.purchase_date
     *
     * @mbggenerated
     */
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column service_purchase.effective_t
     *
     * @return the value of service_purchase.effective_t
     *
     * @mbggenerated
     */
    public Date getEffectiveT() {
        return effectiveT;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column service_purchase.effective_t
     *
     * @param effectiveT the value for service_purchase.effective_t
     *
     * @mbggenerated
     */
    public void setEffectiveT(Date effectiveT) {
        this.effectiveT = effectiveT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServicePurchase that = (ServicePurchase) o;
        return Objects.equals(id, that.id) && Objects.equals(customerId, that.customerId) && Objects.equals(nursingContentId, that.nursingContentId) && Objects.equals(purchaseDate, that.purchaseDate) && Objects.equals(effectiveT, that.effectiveT);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, nursingContentId, purchaseDate, effectiveT);
    }
}