package com.example.bedhouseserver.service.impl;

import com.example.bedhouseserver.POJO.LiveWithCustomerName;
import com.example.bedhouseserver.mapper.LiveMapper;
import com.example.bedhouseserver.service.LiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiveServiceImpl implements LiveService {
    @Autowired
    LiveMapper liveMapper;

    @Override
    public Integer useBed(Integer id) {
        return liveMapper.getBedIdByCustomerId(id);
    }

    @Override
    public List<LiveWithCustomerName> allLiveInfo() {
        return liveMapper.allInfo();
    }
}
