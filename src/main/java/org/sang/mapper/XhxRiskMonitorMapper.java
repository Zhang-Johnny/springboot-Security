package org.sang.mapper;

import org.sang.bean.XhxRiskMonitor;

import java.util.List;
import java.util.Map;

public interface XhxRiskMonitorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(XhxRiskMonitor record);

    int insertSelective(XhxRiskMonitor record);

    XhxRiskMonitor selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XhxRiskMonitor record);

    int updateByPrimaryKey(XhxRiskMonitor record);

    int count(Map map);

    List<XhxRiskMonitor> findAll(Map map);
}