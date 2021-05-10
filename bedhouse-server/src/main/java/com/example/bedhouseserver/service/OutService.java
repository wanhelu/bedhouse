package com.example.bedhouseserver.service;

import com.example.bedhouseserver.POJO.OutRecord;

import java.util.Date;
import java.util.List;

public interface OutService {
    List<OutRecord> allOutInfo();

    List<OutRecord> search(String word, String date);

    boolean add(OutRecord outRecord);

    boolean upd(OutRecord outRecord);

    boolean del(int id);

    boolean goOut(int id);

    boolean goBack(int id);
}
