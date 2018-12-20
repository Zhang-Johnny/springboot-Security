package org.sang.bean;

import org.sang.entity.ModuleEntity;

import java.util.Date;

public class XhxInvoiceAptitude extends ModuleEntity {

    private Integer userId;

    //开户名称
    private String accountName;

    //税务号
    private Integer taxNumber;

    //发票内容
    private String context;

    //开户银行
    private String bank;

    //银行账号
    private String bankNumber;

    //注册地址
    private String retinfoAdd;

    //邮寄地址
    private String mailingAdd;

    //收件人
    private String addressee;

    //联系电话
    private String telephone;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public Integer getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(Integer taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber == null ? null : bankNumber.trim();
    }

    public String getRetinfoAdd() {
        return retinfoAdd;
    }

    public void setRetinfoAdd(String retinfoAdd) {
        this.retinfoAdd = retinfoAdd == null ? null : retinfoAdd.trim();
    }

    public String getMailingAdd() {
        return mailingAdd;
    }

    public void setMailingAdd(String mailingAdd) {
        this.mailingAdd = mailingAdd == null ? null : mailingAdd.trim();
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee == null ? null : addressee.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

}