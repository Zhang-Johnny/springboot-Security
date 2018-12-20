package org.sang.service;

import org.sang.bean.XhxRiskOrder;
import org.sang.common.SnowFlake;
import org.sang.controller.RiskMonitorController;
import org.sang.mapper.XhxRiskOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class XhxRiskOrderService {
    public final static Logger logger = LoggerFactory.getLogger(XhxRiskOrderService.class);
    @Autowired
    XhxRiskOrderMapper riskOrderMapper;
    @Autowired
    XhxMerchandiseOrderService merchandiseOrderService;
    public int count(Map map){
        return riskOrderMapper.count(map);
    }

    public List<XhxRiskOrder> findAll(Map map){
        return riskOrderMapper.findAll(map);
    }

    public XhxRiskOrder findById(Long id){
        return riskOrderMapper.selectByPrimaryKey(id);
    }

    public int update(XhxRiskOrder riskOrder){
        riskOrder.setUpdateTime(new Date());
        return riskOrderMapper.updateByPrimaryKeySelective(riskOrder);
    }

    public int delete(Long id){
        return riskOrderMapper.deleteByPrimaryKey(id);
    }

    public int insert(XhxRiskOrder xhxRiskOrder){
        String orderTimeLong = merchandiseOrderService.findByOrderId(xhxRiskOrder.getOrderId());
        if(orderTimeLong==null){
            return 0;
        }
        int orderTimeLongInt = Integer.parseInt(orderTimeLong);
        Long Id = SnowFlake.getSnowFlakeId("XhxRiskOrder");
        xhxRiskOrder.setId(Id);
        xhxRiskOrder.setCreateTime(new Date());
        xhxRiskOrder.setUpdateTime(new Date());
        xhxRiskOrder.setStartTime(new Date());
        Date endTime = new Date();
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(endTime);
        rightNow.add(Calendar.YEAR,orderTimeLongInt);
        endTime=rightNow.getTime();
        xhxRiskOrder.setEndTime(endTime);
        return riskOrderMapper.insertSelective(xhxRiskOrder);
    }

    public List<XhxRiskOrder> findRiskOrderByUserId(Map map) {
        return riskOrderMapper.findRiskOrderByUserId(map);
    }

    public int countByUserId(Map map) {
        return riskOrderMapper.countByUserId(map);
    }
}
