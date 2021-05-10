package com.example.bedhouseserver.service.impl;

import com.example.bedhouseserver.POJO.OutRecord;
import com.example.bedhouseserver.mapper.OutRecordMapper;
import com.example.bedhouseserver.service.OutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class OutServiceImpl implements OutService {
    @Autowired
    OutRecordMapper outRecordMapper;

    @Override
    public List<OutRecord> allOutInfo() {
        return outRecordMapper.allInfo();
    }

    @Override
    public List<OutRecord> search(String word, String date) {
        Pattern pattern = Pattern.compile("[0-9]*");
        List<OutRecord> res;
        if (pattern.matcher(word).matches()) {
            res = outRecordMapper.searchByNum(Integer.valueOf(word), date);
            res.addAll(outRecordMapper.searchByString(word, date));
        } else {
            res = outRecordMapper.searchByString(word, date);
        }
        LinkedHashSet<OutRecord> hashSet = new LinkedHashSet<>(res);
        return new ArrayList<>(hashSet);
    }

    @Override
    public boolean add(OutRecord outRecord) {
        return outRecordMapper.insertSelective(outRecord) > 0;
    }

    @Override
    public boolean upd(OutRecord outRecord) {
        return outRecordMapper.updateByPrimaryKeySelective(outRecord) > 0;
    }

    @Override
    public boolean del(int id) {
        return outRecordMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean goOut(int id) {
        OutRecord selectRes = outRecordMapper.selectByPrimaryKey(id);
        if (selectRes == null || selectRes.getState() != 3) return false;

        OutRecord outRecord = new OutRecord();
        outRecord.setId(id);
        outRecord.setState(4L);
        return outRecordMapper.updateByPrimaryKeySelective(outRecord) > 0;
    }

    @Override
    public boolean goBack(int id) {
        OutRecord selectRes = outRecordMapper.selectByPrimaryKey(id);
        if (selectRes == null || selectRes.getState() != 4) return false;

        OutRecord outRecord = new OutRecord();
        outRecord.setId(id);
        outRecord.setBackTime(new Date());
        outRecord.setState(5L);
        return outRecordMapper.updateByPrimaryKeySelective(outRecord) > 0;
    }
}
