package org.sang.mapper;

import org.sang.bean.XhxInvoiceOrder;

import java.util.List;
import java.util.Map;

public interface XhxInvoiceOrderMapper {

    int deleteByPrimaryKey(Long id);

    int insert(XhxInvoiceOrder record);

    int insertSelective(XhxInvoiceOrder record);

    XhxInvoiceOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XhxInvoiceOrder record);

    int updateByPrimaryKey(XhxInvoiceOrder record);

    int count(Map map);

    List<XhxInvoiceOrder> findAll(Map map);

    XhxInvoiceOrder selectByInvoiceNo(String invoiceNo);

    List<XhxInvoiceOrder> selectAllInvoiceOrder(Map map);

    /*int saveInvoiceOrder(XhxInvoiceOrder invoiceOrder);*/

}