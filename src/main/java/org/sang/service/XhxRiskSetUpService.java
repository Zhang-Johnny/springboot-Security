package org.sang.service;

import org.sang.bean.XhxRiskSetUp;
import org.sang.common.SnowFlake;
import org.sang.mapper.XhxRiskSetUpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class XhxRiskSetUpService {
    @Autowired
    XhxRiskSetUpMapper moduleMapper;
    public int addDep(XhxRiskSetUp modul) {
       return 1;
    }

    public int deleteModule(Long did) {
       return moduleMapper.deleteByPrimaryKey(did);
    }

   public List<XhxRiskSetUp> getModelByUserId(Long pid) {
        return moduleMapper.getModelByUserId(pid);
    }

    public List<XhxRiskSetUp> getAllDeps() {
        return moduleMapper.getAllDeps();
    }

    public int addRiskSetUp(Map map) {
        map.put("id", SnowFlake.getSnowFlakeId("xhx_risk_set_up"));
        map.put("createTime",new Date());
        map.put("updateTime",new Date());
        return moduleMapper.addRiskSetUp(map);
    }

    public int updateRiskSetUp(Map map) {
        map.put("updateTime",new Date());
        return moduleMapper.updateSelective(map);
    }
}
