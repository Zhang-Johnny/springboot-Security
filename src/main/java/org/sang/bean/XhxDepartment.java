package org.sang.bean;

import org.sang.entity.ModuleEntity;

import java.util.Date;

public class XhxDepartment extends ModuleEntity {

    //部门名称
    private String name;

    private Long parentId;

    private String depPath;

    private Integer enabled;

    private Integer isParent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDepPath() {
        return depPath;
    }

    public void setDepPath(String depPath) {
        this.depPath = depPath == null ? null : depPath.trim();
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getIsParent() {
        return isParent;
    }

    public void setIsParent(Integer isParent) {
        this.isParent = isParent;
    }

}