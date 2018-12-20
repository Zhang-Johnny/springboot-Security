package org.sang.mapper;

import org.sang.bean.XhxRiskSetUp;

import java.util.List;
import java.util.Map;

public interface XhxRiskSetUpMapper {
    int insert(XhxRiskSetUp record);

    int deleteByPrimaryKey(Long id);

    int insertSelective(XhxRiskSetUp record);

    XhxRiskSetUp selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XhxRiskSetUp record);

    int updateByPrimaryKey(XhxRiskSetUp record);

    List<XhxRiskSetUp> getAllDeps();
    List<XhxRiskSetUp> getModelByUserId(Long userId);

    int addRiskSetUp(Map map);

    int updateSelective(Map map);
}