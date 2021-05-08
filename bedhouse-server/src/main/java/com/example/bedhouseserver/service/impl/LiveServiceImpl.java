package com.example.bedhouseserver.service.impl;

import com.example.bedhouseserver.POJO.Live;
import com.example.bedhouseserver.POJO.LiveWithCustomerName;
import com.example.bedhouseserver.mapper.LiveMapper;
import com.example.bedhouseserver.service.LiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LiveServiceImpl implements LiveService {
    @Autowired
    LiveMapper liveMapper;

    @Override
    public Integer useBed(Integer id) {
        return liveMapper.getUsedBedIdByCustomerId(id);
    }

    @Override
    public List<LiveWithCustomerName> allLiveInfo() {
        return liveMapper.allInfo();
    }


    @Override
    public List<LiveWithCustomerName> search(Integer num, String fromDate, String toDate) {
        return liveMapper.search(num,fromDate,toDate);
    }

    @Override
    public boolean add(Live live) {
        if(isBedOrCustomerUser(live)) return false;
        return liveMapper.insertSelective(live)>0;
    }

    @Override
    public boolean upd(Live live) {
        if(isBedOrCustomerUser(live)^live.getLeaveDate()!=null)return false;
        return liveMapper.updateByPrimaryKeySelective(live)>0;
    }

    @Override
    public boolean del(Integer id) {
        return liveMapper.deleteByPrimaryKey(id)>0;
    }

    @Override
    public boolean leave(Integer id) {
        Live live=new Live();
        live.setId(id);
        live.setLeaveDate(new Date());
        return liveMapper.updateByPrimaryKeySelective(live)>0;
    }

    private boolean isBedOrCustomerUser(Live live){
        return (live.getCustomerId()!=null&&liveMapper.getUsedBedIdByCustomerId(live.getCustomerId())!=null)||(live.getBedId()!=null&&liveMapper.getUsedCustomerIdByBedId(live.getBedId())!=null);
    }
}
