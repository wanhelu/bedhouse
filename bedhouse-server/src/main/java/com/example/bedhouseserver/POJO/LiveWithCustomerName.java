package com.example.bedhouseserver.POJO;

import java.util.Objects;

public class LiveWithCustomerName extends Live{
    private String CustomerName;

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LiveWithCustomerName that = (LiveWithCustomerName) o;
        return Objects.equals(CustomerName, that.CustomerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), CustomerName);
    }
}
