package org.sang.service;

import org.sang.bean.XhxFreeTrlalRecord;
import org.sang.common.SnowFlake;
import org.sang.mapper.XhxFreeTrlalRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class XhxFreeTrlalRecordService {
    @Autowired
    XhxFreeTrlalRecordMapper freeTrlalRecordMapper;

    public int count(Map map){
        return freeTrlalRecordMapper.count(map);
    }

    public List<XhxFreeTrlalRecord> findAll(Map map){
        return freeTrlalRecordMapper.findAll(map);
    }

    public XhxFreeTrlalRecord findById(Long id){
        return freeTrlalRecordMapper.selectByPrimaryKey(id);
    }

    public int update(XhxFreeTrlalRecord xhxFreeTrlalRecord){
        xhxFreeTrlalRecord.setUpdateTime(new Date());
        return freeTrlalRecordMapper.updateByPrimaryKeySelective(xhxFreeTrlalRecord);
    }

    public int delete(Long id){
        return freeTrlalRecordMapper.deleteByPrimaryKey(id);
    }

    public int countNo(Map map){
        return freeTrlalRecordMapper.countNo(map);
    }

    public List<XhxFreeTrlalRecord> findAllByParam(Map map){
        return freeTrlalRecordMapper.findAllByParam(map);
    }

    public int inset(XhxFreeTrlalRecord xhxFreeTrlalRecord){
        Long Id = SnowFlake.getSnowFlakeId("XhxFreeTrlalRecord");
        xhxFreeTrlalRecord.setId(Id);
        xhxFreeTrlalRecord.setCreateTime(new Date());
        xhxFreeTrlalRecord.setUpdateTime(new Date());
        return freeTrlalRecordMapper.insertSelective(xhxFreeTrlalRecord);
    }

    public XhxFreeTrlalRecord selectByUserId(Long userId) {
        return freeTrlalRecordMapper.selectByUserId(userId);
    }
}
