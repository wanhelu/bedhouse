package com.example.bedhouseserver.service;

import com.example.bedhouseserver.POJO.LiveWithCustomerName;

import java.util.List;

public interface LiveService {
    Integer useBed(Integer id);

    List<LiveWithCustomerName> allLiveInfo();
}
