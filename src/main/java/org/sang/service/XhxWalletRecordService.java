package org.sang.service;

import org.sang.bean.XhxWalletRecord;
import org.sang.common.SnowFlake;
import org.sang.mapper.XhxWalletRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class XhxWalletRecordService {
    @Autowired
    XhxWalletRecordMapper walletRecordMapper;

    public int count(Map map){
        return walletRecordMapper.count(map);
    }

    public List<XhxWalletRecord> findAll(Map map){
        return walletRecordMapper.findAll(map);
    }

    public List<XhxWalletRecord> findByUserIdList(Map map) {
        return walletRecordMapper.selectByUserIdList(map);
    }
    public List<XhxWalletRecord> findByParam(Map map) {
        return walletRecordMapper.selectByParam(map);
    }

    public int insertRecord(XhxWalletRecord walletRecord) {
        Long id = SnowFlake.getSnowFlakeId("XhxWalletRecord");
        walletRecord.setId(id);
        walletRecord.setCreateTime(new Date());
        walletRecord.setUpdateTime(new Date());
        return walletRecordMapper.insertSelective(walletRecord);
    }
}
