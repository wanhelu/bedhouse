package com.example.bedhouseserver.service;

import com.example.bedhouseserver.POJO.Live;
import com.example.bedhouseserver.POJO.LiveWithCustomerName;

import java.util.List;

public interface LiveService {
    Integer useBed(Integer id);

    List<LiveWithCustomerName> allLiveInfo();

    List<LiveWithCustomerName> search(Integer num,String fromDate,String toDate);

    boolean add(Live live);

    boolean upd(Live live);

    boolean del(Integer id);

    boolean leave(Integer id);
}
