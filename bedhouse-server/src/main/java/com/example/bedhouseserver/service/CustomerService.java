package com.example.bedhouseserver.service;

import com.example.bedhouseserver.POJO.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> allCustomerInfo();

    List<Customer>search(String word);

    boolean add(Customer customer);

    boolean del(Integer id);

    boolean upd(Customer customer);
}
