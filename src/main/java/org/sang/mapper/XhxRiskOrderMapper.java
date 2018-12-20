package org.sang.mapper;

import org.sang.bean.XhxRiskOrder;

import java.util.List;
import java.util.Map;

public interface XhxRiskOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(XhxRiskOrder record);

    int insertSelective(XhxRiskOrder record);

    XhxRiskOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XhxRiskOrder record);

    int updateByPrimaryKey(XhxRiskOrder record);

    int count(Map map);

    List<XhxRiskOrder> findAll(Map map);

    List<XhxRiskOrder> findRiskOrderByUserId(Map map);

    int countByUserId(Map map);
}