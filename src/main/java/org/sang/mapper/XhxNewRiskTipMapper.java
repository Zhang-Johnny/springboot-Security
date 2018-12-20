package org.sang.mapper;

import org.sang.bean.XhxNewRiskTip;

import java.util.List;
import java.util.Map;

public interface XhxNewRiskTipMapper {

    List<XhxNewRiskTip> getNewRiskTipByUserId(Map map);

    int count(Map map);
}
