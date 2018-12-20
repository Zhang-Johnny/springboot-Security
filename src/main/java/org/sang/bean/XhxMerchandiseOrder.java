package org.sang.bean;

import org.sang.entity.ModuleEntity;

import java.util.Date;

public class XhxMerchandiseOrder extends ModuleEntity {

    private Long userId;

    public String getOrderTimeLong() {
        return orderTimeLong;
    }

    public void setOrderTimeLong(String orderTimeLong) {
        this.orderTimeLong = orderTimeLong;
    }

    private String orderTimeLong;
    //订单名称
    private String orderName;

    //订单类型
    private Integer orderType;

    //订单金额
    private Double orderAmount;

    //0、未开发票 1、已开发票/无余额2、已开发票/有余额
    private Integer invoiceStatus;

    //订购时间
    private Date orderTime;

    private Long orderNo;

    private String orderContent;

    private Integer orderStatus;

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName == null ? null : orderName.trim();
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

}