package com.example.bedhouseserver.service.impl;

import com.example.bedhouseserver.POJO.Bed;
import com.example.bedhouseserver.POJO.Customer;
import com.example.bedhouseserver.mapper.CustomerMapper;
import com.example.bedhouseserver.mapper.LiveMapper;
import com.example.bedhouseserver.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerMapper customerMapper;


    @Override
    public List<Customer> allCustomerInfo() {
        return customerMapper.allInfo();
    }

    @Override
    public Customer customerInfoById(int id) {
        return customerMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Customer> search(String word) {
        Pattern pattern = Pattern.compile("[0-9]*");
        List<Customer> res;
        if (pattern.matcher(word).matches()) {
            res = customerMapper.searchByNum(Integer.valueOf(word));
            res.addAll(customerMapper.search(word));
        } else {
            res=customerMapper.search(word);
        }
        LinkedHashSet<Customer> hashSet=new LinkedHashSet<>(res);
        return new ArrayList<>(hashSet);
    }

    @Override
    public boolean add(Customer customer){
        return customerMapper.insertSelective(customer)>0;
    }

    @Override
    public boolean del(Integer id) {
        return customerMapper.deleteByPrimaryKey(id)>0;
    }

    @Override
    public boolean upd(Customer customer) {
        return customerMapper.updateByPrimaryKeySelective(customer)>0;
    }

}
