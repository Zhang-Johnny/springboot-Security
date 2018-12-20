package org.sang.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.sang.entity.ModuleEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class XhxGroupUser extends ModuleEntity implements UserDetails{

    //用户名
    private String username;

    //密码
    private String password;

    //单位公司
    private String company;

    //联系电话
    private String mobilePhone;

    //账户绑定手机
    private String bindMobile;

    //最后登录IP
    private String lastLoginIp;

    //最后登录时间
    private Date lastLoginTime;

    //登录总次数
    private String loginCount;

    //头像
    private String profilePicture;

    //是否启用
    private boolean enable;

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    //客户购买vip可创建用户数量
    private int userCount;

    private XhxUserinfo xhxUserinfo;

    public Double getRechMoney() {
        return rechMoney;
    }

    public void setRechMoney(Double rechMoney) {
        this.rechMoney = rechMoney;
    }

    public Double getGiveMoney() {
        return giveMoney;
    }

    public void setGiveMoney(Double giveMoney) {
        this.giveMoney = giveMoney;
    }

    private Double rechMoney;

    private Double giveMoney;
    private List<XhxRole> roles;

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (XhxRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    public String getBindMobile() {
        return bindMobile;
    }

    public void setBindMobile(String bindMobile) {
        this.bindMobile = bindMobile == null ? null : bindMobile.trim();
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(String loginCount) {
        this.loginCount = loginCount == null ? null : loginCount.trim();
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture == null ? null : profilePicture.trim();
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public XhxUserinfo getXhxUserinfo() {
        return xhxUserinfo;
    }

    public void setXhxUserinfo(XhxUserinfo xhxUserinfo) {
        this.xhxUserinfo = xhxUserinfo;
    }

    public List<XhxRole> getRoles() {
        return roles;
    }

    public void setRoles(List<XhxRole> roles) {
        this.roles = roles;
    }
}