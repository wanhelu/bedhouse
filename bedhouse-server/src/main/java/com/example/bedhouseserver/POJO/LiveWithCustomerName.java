package com.example.bedhouseserver.POJO;

import java.util.Objects;

public class LiveWithCustomerName extends Live{
    private String customerName;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LiveWithCustomerName that = (LiveWithCustomerName) o;
        return Objects.equals(customerName, that.customerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), customerName);
    }
}
