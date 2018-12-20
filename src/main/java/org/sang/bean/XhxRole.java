package org.sang.bean;

import org.sang.entity.ModuleEntity;

import java.util.Date;

public class XhxRole extends ModuleEntity {

    //名称
    private String name;

    //中文
    private String nameZh;

    //排序
    private Integer sort;

    private boolean isEnable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh == null ? null : nameZh.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

}