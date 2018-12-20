package org.sang.bean;

import org.sang.entity.ModuleEntity;

import java.util.Date;

public class XhxRiskOrder extends ModuleEntity {

    private Long userId;

    private Long riskId;

    private Long companyId;

    private Long orderId;

    private String companyName;

    private Integer roType;

    private Date startTime;

    private Date endTime;

    private XhxUser xhxUser;

    private String menuName;//该字段只为封装查询结果，数据库表中无该字段

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    private Integer userType;//该字段只为封装查询结果，数据库表中无该字段

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRiskId() {
        return riskId;
    }

    public void setRiskId(Long riskId) {
        this.riskId = riskId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public Integer getRoType() {
        return roType;
    }

    public void setRoType(Integer roType) {
        this.roType = roType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public XhxUser getXhxUser() {
        return xhxUser;
    }

    public void setXhxUser(XhxUser xhxUser) {
        this.xhxUser = xhxUser;
    }
}