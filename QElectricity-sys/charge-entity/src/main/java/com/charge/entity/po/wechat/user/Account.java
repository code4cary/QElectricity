package com.charge.entity.po.wechat.user;

import com.charge.entity.po.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Account extends BaseEntity implements Serializable {
    private Integer uid;

    private String accountDeposit;

    private String depositStatus;

    private String accountBalance;

    private Date updateTime;

    /**
     * t_account关联t_user  一对一关系
     */
    private User user;

    public User getUser() {
        return user;
    }

    /**
     * t_account关联t_order 一对多关系
     */
    private List<Order> orderList;

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private static final long serialVersionUID = 1L;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAccountDeposit() {
        return accountDeposit;
    }

    public void setAccountDeposit(String accountDeposit) {
        this.accountDeposit = accountDeposit == null ? null : accountDeposit.trim();
    }

    public String getDepositStatus() {
        return depositStatus;
    }

    public void setDepositStatus(String depositStatus) {
        this.depositStatus = depositStatus == null ? null : depositStatus.trim();
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

    @Override
    public String toString() {
        return "Account{" +
                "uid=" + uid +
                ", accountDeposit='" + accountDeposit + '\'' +
                ", depositStatus='" + depositStatus + '\'' +
                ", accountBalance='" + accountBalance + '\'' +
                ", updateTime=" + updateTime +
                ", user=" + user +
                ", orderList=" + orderList +
                '}';
    }
}