package org.sang.service;

import org.sang.bean.XhxInvoiceAptitude;
import org.sang.common.SnowFlake;
import org.sang.mapper.XhxInvoiceAptitudeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class XhxInvoiceAptitudeService {

    @Autowired
    XhxInvoiceAptitudeMapper invoiceAptitudeMapper;

    public int count(Map map){
        return invoiceAptitudeMapper.count(map);
    }

    public List<XhxInvoiceAptitude> findAll(Map map){
        return invoiceAptitudeMapper.findAll(map);
    }

    public List<XhxInvoiceAptitude> findByNumber(Integer taxNumber){
        return invoiceAptitudeMapper.findByNumber(taxNumber);
    }

    public int delete(Long id){
        return invoiceAptitudeMapper.deleteByPrimaryKey(id);
    }

    public XhxInvoiceAptitude getInvoiceAptitude(int userId) {
        return invoiceAptitudeMapper.getInvoiceAptitudeByUserId(userId);
    }

    public int saveInvoiceAptitude(XhxInvoiceAptitude invoiceAptitude) {
        Long id = SnowFlake.getSnowFlakeId("XhxInvoiceAptitude");
        invoiceAptitude.setId(id);
        invoiceAptitude.setCreateTime(new Date());
        invoiceAptitude.setUpdateTime(new Date());
        return invoiceAptitudeMapper.insertSelective(invoiceAptitude);
    }

    public XhxInvoiceAptitude findByUserId(Integer userId) {
        return invoiceAptitudeMapper.getInvoiceAptitudeByUserId(userId);
    }

    public int updateinvoiceAptitude(XhxInvoiceAptitude invoiceAptitude) {
        invoiceAptitude.setUpdateTime(new Date());
        return invoiceAptitudeMapper.updateByPrimaryKeySelective(invoiceAptitude);
    }


}
