package com.charge.entity.po.wechat.user;

import com.charge.entity.po.base.BaseEntity;

import java.io.Serializable;

public class WaterBills extends BaseEntity implements Serializable {
    private Integer oid;

    private String amount;

    private String type;

    /**
     * t_water_bills关联t_order 一对一
     */
    private Order order;

    private static final long serialVersionUID = 1L;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    @Override
    public String toString() {
        return "WaterBills{" +
                "oid=" + oid +
                ", amount='" + amount + '\'' +
                ", type='" + type + '\'' +
                ", order=" + order +
                '}';
    }
}