package com.example.bedhouseserver.service;

import com.example.bedhouseserver.POJO.Bed;

import java.util.List;

public interface BedService {
    List<Bed> allBedInfo();

    Bed BedInfoById(int id);

    Boolean bedUsedInfo(Integer id);

    List<Bed> search(String word);

    boolean add(Bed bed);

    boolean upd(Bed bed);

    boolean del(Integer id);
}
