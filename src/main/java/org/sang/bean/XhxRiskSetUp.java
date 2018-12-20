package org.sang.bean;

import org.sang.entity.ModuleEntity;

public class XhxRiskSetUp extends ModuleEntity {
    private Long userId;
    private String receive_mode; //风险接收方式 1 邮件 2 APP消息 3 微信
    private String mails;  //接受邮件
    private Integer send_cycle;   //发送周期 1 每周 2 每月 3 季度 4 不需要

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getReceive_mode() {
        return receive_mode;
    }

    public void setReceive_mode(String receive_mode) {
        this.receive_mode = receive_mode;
    }

    public String getMails() {
        return mails;
    }

    public void setMails(String mails) {
        this.mails = mails;
    }

    public Integer getSend_cycle() {
        return send_cycle;
    }

    public void setSend_cycle(Integer send_cycle) {
        this.send_cycle = send_cycle;
    }
}