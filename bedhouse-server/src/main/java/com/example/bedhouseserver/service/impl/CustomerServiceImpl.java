package com.example.bedhouseserver.service.impl;

import com.example.bedhouseserver.POJO.Customer;
import com.example.bedhouseserver.mapper.CustomerMapper;
import com.example.bedhouseserver.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerMapper customerMapper;

    @Override
    public List<Customer> allCustomerInfo() {
        return customerMapper.allInfo();
    }
}
