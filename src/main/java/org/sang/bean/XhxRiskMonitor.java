package org.sang.bean;

import org.sang.entity.ModuleEntity;

import java.util.Date;

public class XhxRiskMonitor extends ModuleEntity {

    private String menuName;

    private Double monitorPrice;

    private Double discount;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public Double getMonitorPrice() {
        return monitorPrice;
    }

    public void setMonitorPrice(Double monitorPrice) {
        this.monitorPrice = monitorPrice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

}