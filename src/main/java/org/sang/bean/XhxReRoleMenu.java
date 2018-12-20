package org.sang.bean;

import org.sang.entity.ModuleEntity;

import java.util.Date;

public class XhxReRoleMenu extends ModuleEntity {
    //模块ID
    private Long mId;

    //角色ID
    private Long roleId;

    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}