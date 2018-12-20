package org.sang.service;

import org.joda.time.DateTime;
import org.sang.bean.XhxMerchandiseOrder;
import org.sang.bean.XhxRiskOrder;
import org.sang.common.SnowFlake;
import org.sang.common.tool.ResultUtil;
import org.sang.enums.ResultEnum;
import org.sang.mapper.XhxMerchandiseOrderMapper;
import org.sang.mapper.XhxRiskOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

@Service
@Transactional
public class XhxMerchandiseOrderService {

    @Autowired
    XhxMerchandiseOrderMapper merchandiseOrderMapper;
    @Autowired
    XhxRiskOrderMapper riskOrderMapper;

    public List<XhxMerchandiseOrder> findAll(Map map){
        return merchandiseOrderMapper.findAll(map);
    }

    public XhxMerchandiseOrder findById(Long id){
        return merchandiseOrderMapper.selectByPrimaryKey(id);
    }

    public int update(XhxMerchandiseOrder xhxMerchandiseOrder){
        xhxMerchandiseOrder.setUpdateTime(new Date());
        return merchandiseOrderMapper.updateByPrimaryKeySelective(xhxMerchandiseOrder);
    }

    public List<XhxMerchandiseOrder> findByObject(Map map){
        return merchandiseOrderMapper.findByObject(map);
    }

    public int delete(Long id){
        return merchandiseOrderMapper.deleteByPrimaryKey(id);
    }

    public int count(Map map){
        return merchandiseOrderMapper.count(map);
    };

    public int findByParamHdCount(Map map){
        return merchandiseOrderMapper.findByParamHdCount(map);
    }

    public List<XhxMerchandiseOrder> findByParamHd(Map map){
        return merchandiseOrderMapper.findByParamHd(map);
    }

    public int findByParamQdCount(Map map){
        return merchandiseOrderMapper.findByParamQdCount(map);
    }

    public List<XhxMerchandiseOrder> findByParamQd(Map map) {
        return merchandiseOrderMapper.findByParamQd(map);
    }
    public List<XhxMerchandiseOrder> findByUserId(Long userId) {
        return merchandiseOrderMapper.selectByUserId(userId);
    }

    public int findMerchandiseByGroupIdCount(Map map){
        return merchandiseOrderMapper.findMerchandiseByGroupIdCount(map);
    }

    public List<XhxMerchandiseOrder> findMerchandiseByGroupId(Map map){
        return merchandiseOrderMapper.findMerchandiseByGroupId(map);
    }

    public int insert(XhxMerchandiseOrder xhxMerchandiseOrder){
        Long Id = SnowFlake.getSnowFlakeId("xhx_merchandise_order");
        xhxMerchandiseOrder.setId(Id);
        xhxMerchandiseOrder.setOrderStatus(1);
        xhxMerchandiseOrder.setCreateTime(new Date());
        xhxMerchandiseOrder.setOrderTime(new Date());
        /*//添加XhxRiskOrder
        XhxRiskOrder riskOrderb = new XhxRiskOrder();
        riskOrderb.setOrderId(xhxMerchandiseOrder.getOrderNo());
        riskOrderb.setId(SnowFlake.getSnowFlakeId("xhx_risk_order"));
        riskOrderb.setUserId(xhxMerchandiseOrder.getUserId());
        Date orderTime = xhxMerchandiseOrder.getOrderTime();
        String orderTimeLong = xhxMerchandiseOrder.getOrderTimeLong();
        riskOrderb.setStartTime(orderTime);
        Calendar c = Calendar.getInstance();
        c.setTime(orderTime);
        c.add(Calendar.YEAR, Integer.parseInt(orderTimeLong));
        riskOrderb.setEndTime(c.getTime());
        int i = riskOrderMapper.insertSelective(riskOrderb);
        if(i<1){
            return i;
        }*/
        return merchandiseOrderMapper.insertSelective(xhxMerchandiseOrder);
    }

    public String findByOrderId(Long orderId) {
        return merchandiseOrderMapper.selectByOrderNo(orderId);
    }
}
