package org.sang.mapper;

import org.sang.bean.XhxDeliveryMailingAddress;

import java.util.List;
import java.util.Map;

public interface XhxDeliveryMailingAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(XhxDeliveryMailingAddress record);

    int insertSelective(XhxDeliveryMailingAddress record);

    XhxDeliveryMailingAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XhxDeliveryMailingAddress record);

    int updateByPrimaryKey(XhxDeliveryMailingAddress record);

    List<XhxDeliveryMailingAddress> getAllDeps();

    List<XhxDeliveryMailingAddress> getModelByUserId(Long userId);

    int addMailingAddress(Map map);

    int updateSelective(Map map);
}