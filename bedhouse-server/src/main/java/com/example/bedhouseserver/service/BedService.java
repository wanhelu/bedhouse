package com.example.bedhouseserver.service;

import com.example.bedhouseserver.POJO.Bed;

import java.util.List;

public interface BedService {
    List<Bed> allBedInfo();

    Boolean bedUsedInfo(Integer id);

    List<Bed> search(String word);

    boolean add(Bed bed);
}
