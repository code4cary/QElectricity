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

    private String rechargeBalance;

    private String balanceGived;

    private Date updateTime;

    /**
     * t_account关联t_user 一对一关系
     */
    private User user;

    /**
     * t_account关联t_order 一对多关系
     */
    private List<Order> orderList;

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

    public String getRechargBalance() {
        return rechargeBalance;
    }

    public void setRechargBalance(String rechargeBalance) {
        this.rechargeBalance = rechargeBalance == null ? null : rechargeBalance.trim();
    }

    public String getBalanceGived() {
        return balanceGived;
    }

    public void setBalanceGived(String balanceGived) {
        this.balanceGived = balanceGived == null ? null : balanceGived.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uid=").append(uid);
        sb.append(", accountDeposit=").append(accountDeposit);
        sb.append(", depositStatus=").append(depositStatus);
        sb.append(", accountBalance=").append(accountBalance);
        sb.append(", rechargeBalance=").append(rechargeBalance);
        sb.append(", balanceGived=").append(balanceGived);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}