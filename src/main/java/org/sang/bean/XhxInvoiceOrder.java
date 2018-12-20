package org.sang.bean;

import org.sang.entity.ModuleEntity;

import java.util.Date;
import java.util.Map;

public class XhxInvoiceOrder extends ModuleEntity {

    private Long userId;

    //发票号码
    private String invoiceno;

    //发票抬头
    private String invoiceHeader;

    //税务号
    private String taxNumber;

    //发票类型:
    //0 普通发票
    //1 增值税发票
    private Integer invoiceType;

    //发票性值
    //0 纸质
    //1 电子
    private Integer invoiceNature;

    //发票金额
    private Double orderAmount;

    //发票状态
    //0、未开发票 1、已开发票
    private Integer orderStatus;


    //发票内容
    private String context;

    //配送方式：0 快递1 下载
    private Integer deliveryType;

    //邮寄地址
    private String mailingAdd;

    //收件人
    private String addressee;

    //联系电话
    private String telephone;


    private String remark;

    private String expressRemark;

    //申请时间
    private Date orderTime;


    private Map<String,Object> map;

    private XhxUser XhxUser;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getInvoiceno() {
        return invoiceno;
    }

    public void setInvoiceno(String invoiceno) {
        this.invoiceno = invoiceno == null ? null : invoiceno.trim();
    }

    public String getInvoiceHeader() {
        return invoiceHeader;
    }

    public void setInvoiceHeader(String invoiceHeader) {
        this.invoiceHeader = invoiceHeader == null ? null : invoiceHeader.trim();
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public Integer getInvoiceNature() {
        return invoiceNature;
    }

    public void setInvoiceNature(Integer invoiceNature) {
        this.invoiceNature = invoiceNature;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getExpressRemark() {
        return expressRemark;
    }

    public void setExpressRemark(String expressRemark) {
        this.expressRemark = expressRemark;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }

    public Integer getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Integer deliveryType) {
        this.deliveryType = deliveryType;
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

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }


    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public org.sang.bean.XhxUser getXhxUser() {
        return XhxUser;
    }

    public void setXhxUser(org.sang.bean.XhxUser xhxUser) {
        XhxUser = xhxUser;
    }
}