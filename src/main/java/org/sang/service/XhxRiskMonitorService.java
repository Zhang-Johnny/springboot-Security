package org.sang.service;

import org.sang.bean.XhxRiskMonitor;
import org.sang.common.SnowFlake;
import org.sang.mapper.XhxRiskMonitorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class XhxRiskMonitorService {

    @Autowired
    XhxRiskMonitorMapper riskMonitorMapper;

    public int count(Map map){
        return riskMonitorMapper.count(map);
    }

    public List<XhxRiskMonitor> findAll(Map map){
        return riskMonitorMapper.findAll(map);
    }

    public XhxRiskMonitor findById(Long id){
        return riskMonitorMapper.selectByPrimaryKey(id);
    }

    public int update(XhxRiskMonitor xhxRiskMonitor){
        xhxRiskMonitor.setUpdateTime(new Date());
        return riskMonitorMapper.updateByPrimaryKeySelective(xhxRiskMonitor);
    }

    public int delete(Long id){
        return riskMonitorMapper.deleteByPrimaryKey(id);
    }

    public int insert(XhxRiskMonitor xhxRiskMonitor){
        Long Id = SnowFlake.getSnowFlakeId("XhxRiskMonitor");
        xhxRiskMonitor.setId(Id);
        xhxRiskMonitor.setCreateTime(new Date());
        xhxRiskMonitor.setUpdateTime(new Date());
        return riskMonitorMapper.insert(xhxRiskMonitor);
    }
}
