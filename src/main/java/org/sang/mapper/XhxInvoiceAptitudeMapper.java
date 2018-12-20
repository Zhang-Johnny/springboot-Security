package org.sang.mapper;

import org.sang.bean.XhxInvoiceAptitude;

import java.util.List;
import java.util.Map;

public interface XhxInvoiceAptitudeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(XhxInvoiceAptitude record);

    int insertSelective(XhxInvoiceAptitude record);

    XhxInvoiceAptitude selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XhxInvoiceAptitude record);

    int updateByPrimaryKey(XhxInvoiceAptitude record);

    int count(Map map);

    List<XhxInvoiceAptitude> findAll(Map map);

    List<XhxInvoiceAptitude> findByNumber(Integer taxNumber);

    XhxInvoiceAptitude getInvoiceAptitudeByUserId(int userId);
}