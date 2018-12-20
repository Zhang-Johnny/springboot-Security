package org.sang.mapper;

import org.sang.bean.XhxMerchandiseOrder;

import java.util.List;
import java.util.Map;

public interface XhxMerchandiseOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(XhxMerchandiseOrder record);

    int insertSelective(XhxMerchandiseOrder record);

    XhxMerchandiseOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XhxMerchandiseOrder record);

    int updateByPrimaryKey(XhxMerchandiseOrder record);

    List<XhxMerchandiseOrder> findAll(Map map);

    List<XhxMerchandiseOrder> findByObject(Map map);

    int count(Map map);


    int findByParamHdCount(Map map);

    List<XhxMerchandiseOrder> findByParamHd(Map map);

    int findByParamQdCount(Map map);

    List<XhxMerchandiseOrder> findByParamQd(Map map);

    List<XhxMerchandiseOrder> selectByUserId(Long userId);

    int findMerchandiseByGroupIdCount(Map map);

    List<XhxMerchandiseOrder> findMerchandiseByGroupId(Map map);

    String selectByOrderNo(Long orderNo);

}