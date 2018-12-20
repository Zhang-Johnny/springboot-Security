package org.sang.service;

import org.sang.bean.XhxNewRiskTip;
import org.sang.mapper.XhxNewRiskTipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class XhxNewRiskTipService {
    @Autowired
    private XhxNewRiskTipMapper newRiskTipMapper;
    public List<XhxNewRiskTip> getNewRiskTipByUserId(Map map) {
        return newRiskTipMapper.getNewRiskTipByUserId(map);
    }

    public int count(Map map) {
        return newRiskTipMapper.count(map);
    }
}
