package com.example.bedhouseserver.service.impl;

import com.example.bedhouseserver.mapper.LiveMapper;
import com.example.bedhouseserver.service.LiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LiveServiceImpl implements LiveService {
    @Autowired
    LiveMapper liveMapper;

    @Override
    public Integer useBed(Integer id) {
        return liveMapper.getBedIdByCustomerId(id);
    }
}
