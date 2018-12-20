package org.sang.bean;

import org.sang.entity.ModuleEntity;

import java.util.Date;

public class XhxReUserRole extends ModuleEntity {

    //用户ID
    private Long userId;

    //角色ID
    private Long roleId;

    private Date startTime;

    private Date endTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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
}