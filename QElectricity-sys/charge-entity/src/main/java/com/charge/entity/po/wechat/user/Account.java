package com.charge.entity.po.wechat.user;

import com.charge.entity.po.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Account extends BaseEntity implements Serializable {
    private String accountDeposit;

    private String isRefundDeposit;

    private String accountBalance;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     * @des:账户关联多个订单 one2many
     * @associate:
     * @see:
     */
    private List<Order> orderList;

    public List<Order> getOrderList() { return orderList; }

    public String getAccountDeposit() {
        return accountDeposit;
    }

    public void setAccountDeposit(String accountDeposit) {
        this.accountDeposit = accountDeposit == null ? null : accountDeposit.trim();
    }

    public String getIsRefundDeposit() {
        return isRefundDeposit;
    }

    public void setIsRefundDeposit(String isRefundDeposit) {
        this.isRefundDeposit = isRefundDeposit == null ? null : isRefundDeposit.trim();
    }

    public String getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance == null ? null : accountBalance.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setOrderList(List<Order> orderList) { this.orderList = orderList; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", accountDeposit=").append(accountDeposit);
        sb.append(", isRefundDeposit=").append(isRefundDeposit);
        sb.append(", accountBalance=").append(accountBalance);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}