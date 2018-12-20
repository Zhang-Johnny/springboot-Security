package org.sang.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.sang.entity.ModuleEntity;

import java.util.Date;
import java.util.List;

public class XhxMenu extends ModuleEntity {

    private String url;

    private String path;

    private String component;

    private String name;

    private String iconCls;

    private Long parentId;

    private List<XhxRole> roles;
    private List<XhxMenu> children;
    private MenuMeta meta;
    private Boolean checked=false;//回显用户权限数据时，勾选框的选中状态

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }


    @JsonIgnore
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component == null ? null : component.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls == null ? null : iconCls.trim();
    }

    @JsonIgnore
    public Long getParentId() {
        return parentId;
    }

    @JsonIgnore
    public List<XhxRole> getRoles() {
        return roles;
    }

    public void setRoles(List<XhxRole> roles) {
        this.roles = roles;
    }

    public List<XhxMenu> getChildren() {
        return children;
    }

    public void setChildren(List<XhxMenu> children) {
        this.children = children;
    }

    public MenuMeta getMeta() {
        return meta;
    }

    public void setMeta(MenuMeta meta) {
        this.meta = meta;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

}