package org.sang.bean;

import org.sang.entity.ModuleEntity;

import java.util.Date;

public class XhxFreeTrlalRecord extends ModuleEntity {

    private Long userId;

    private Integer trialType;

    private String trialDate;

    private Date startTime;

    private Date endTime;

    private XhxUser xhxUser;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getTrialType() {
        return trialType;
    }

    public void setTrialType(Integer trialType) {
        this.trialType = trialType;
    }

    public String getTrialDate() {
        return trialDate;
    }

    public void setTrialDate(String trialDate) {
        this.trialDate = trialDate == null ? null : trialDate.trim();
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