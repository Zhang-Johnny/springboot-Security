package org.sang.service;

import org.sang.bean.XhxDeliveryMailingAddress;
import org.sang.common.SnowFlake;
import org.sang.mapper.XhxDeliveryMailingAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class XhxDeliveryMailingAddressService {
    @Autowired
    XhxDeliveryMailingAddressMapper moduleMapper;
    public int addDep(XhxDeliveryMailingAddress module) {
        /* department.setEnabled(true);
       departmentMapper.addDep(department);
        return department.getResult();*/
       return 1;
    }

    public int deleteDep(Long did) {
        XhxDeliveryMailingAddress module = new XhxDeliveryMailingAddress();
        module.setId(did);
       /* departmentMapper.deleteDep(department);
        return department.getResult();*/
       return 1;
    }

   public List<XhxDeliveryMailingAddress> getModelByUserId(Long pid) {
        return moduleMapper.getModelByUserId(pid);
    }

    public List<XhxDeliveryMailingAddress> getAllDeps() {
        return moduleMapper.getAllDeps();
    }

    public int addMailingAddress(Map map) {
        map.put("id", SnowFlake.getSnowFlakeId("xhx_delivery_mailing_address"));
        map.put("createTime",new Date());
        map.put("updateTime",new Date());
        return moduleMapper.addMailingAddress(map);
    }

    public int deleteMailingAddress(Long id) {
        return moduleMapper.deleteByPrimaryKey(id);
    }

    public int updateMailingAddress(Map map) {
        map.put("updateTime",new Date());
        return moduleMapper.updateSelective(map);
    }
}
