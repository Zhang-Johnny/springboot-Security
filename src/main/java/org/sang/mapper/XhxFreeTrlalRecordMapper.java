package org.sang.mapper;

import org.sang.bean.XhxFreeTrlalRecord;

import java.util.List;
import java.util.Map;

public interface XhxFreeTrlalRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(XhxFreeTrlalRecord record);

    int insertSelective(XhxFreeTrlalRecord record);

    XhxFreeTrlalRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XhxFreeTrlalRecord record);

    int updateByPrimaryKey(XhxFreeTrlalRecord record);

    int count(Map map);

    List<XhxFreeTrlalRecord> findAll(Map map);

    int countNo(Map map);

    List<XhxFreeTrlalRecord> findAllByParam(Map map);

    XhxFreeTrlalRecord selectByUserId(Long userId);
}