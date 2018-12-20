package org.sang.bean;

import org.sang.entity.ModuleEntity;

import java.util.Date;

public class XhxWalletRecord extends ModuleEntity {

    private Long userId;

    private Double balance;

    private Double giveMoney;

    private Double rechMoney;

    private Double consume;

    private Integer isavailable;

    private Integer invoiceStatus;

    private String operateUser;

    private String consume_projects;

    private Long recordNo;

    private Integer paymentType;

    private String flowNumber;

    public Integer getIsavailable() {
        return isavailable;
    }

    public void setIsavailable(Integer isavailable) {
        this.isavailable = isavailable;
    }

    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }



    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getGiveMoney() {
        return giveMoney;
    }

    public void setGiveMoney(Double giveMoney) {
        this.giveMoney = giveMoney;
    }

    public Double getRechMoney() {
        return rechMoney;
    }

    public void setRechMoney(Double rechMoney) {
        this.rechMoney = rechMoney;
    }

    public Double getConsume() {
        return consume;
    }

    public void setConsume(Double consume) {
        this.consume = consume;
    }

    public String getOperateUser() {
        return operateUser;
    }

    public void setOperateUser(String operateUser) {
        this.operateUser = operateUser == null ? null : operateUser.trim();
    }

    public String getConsume_projects() {
        return consume_projects;
    }

    public void setConsume_projects(String consume_projects) {
        this.consume_projects = consume_projects;
    }

    public Long getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(Long recordNo) {
        this.recordNo = recordNo;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public String getFlowNumber() {
        return flowNumber;
    }

    public void setFlowNumber(String flowNumber) {
        this.flowNumber = flowNumber;
    }
}