package org.sang.service;

import org.sang.bean.XhxInvoiceOrder;
import org.sang.mapper.XhxInvoiceOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class XhxInvoiceOrderService {

    @Autowired
    XhxInvoiceOrderMapper invoiceOrderMapper;

    public int count(Map map){
        return invoiceOrderMapper.count(map);
    }

    public List<XhxInvoiceOrder> findAll(Map map){
        return invoiceOrderMapper.findAll(map);
    }

    public XhxInvoiceOrder findById(Long id){
        return invoiceOrderMapper.selectByPrimaryKey(id);
    }

    public XhxInvoiceOrder findByInvoiceNo(String invoiceNo){
        return invoiceOrderMapper.selectByInvoiceNo(invoiceNo);
    }

    public int update(XhxInvoiceOrder xhxInvoiceOrder){
        xhxInvoiceOrder.setUpdateTime(new Date());
        return invoiceOrderMapper.updateByPrimaryKeySelective(xhxInvoiceOrder);
    }

    public int delete(Long id){
        return invoiceOrderMapper.deleteByPrimaryKey(id);
    }

    public int saveInvoiceOrder(XhxInvoiceOrder InvoiceOrder) {
        return invoiceOrderMapper.insertSelective(InvoiceOrder);
    }

    public List<XhxInvoiceOrder> findInvoiceOrderByUserIdList(Map map) {
        return invoiceOrderMapper.selectAllInvoiceOrder(map);
    }
}
