package org.sang.bean;

import org.sang.entity.ModuleEntity;

import java.util.Date;

public class XhxNewRiskTip extends ModuleEntity{
    //用户id
    private Long userId;

    //风险提示日期
    private Date riskTipDate;

    //风险提示项目
    private String riskTipItem;

    //公司名称
    private String companyName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getRiskTipDate() {
        return riskTipDate;
    }

    public void setRiskTipDate(Date riskTipDate) {
        this.riskTipDate = riskTipDate;
    }

    public String getRiskTipItem() {
        return riskTipItem;
    }

    public void setRiskTipItem(String riskTipItem) {
        this.riskTipItem = riskTipItem;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
