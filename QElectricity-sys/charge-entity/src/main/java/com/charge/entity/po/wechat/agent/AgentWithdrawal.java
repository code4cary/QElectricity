package com.charge.entity.po.wechat.agent;

import com.charge.entity.po.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class AgentWithdrawal extends BaseEntity implements Serializable {
    private String withdrawalNo;

    private String amount;

    private String status;

    private Date endTime;

    private static final long serialVersionUID = 1L;

    public String getWithdrawalNo() {
        return withdrawalNo;
    }

    public void setWithdrawalNo(String withdrawalNo) {
        this.withdrawalNo = withdrawalNo == null ? null : withdrawalNo.trim();
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", withdrawalNo=").append(withdrawalNo);
        sb.append(", amount=").append(amount);
        sb.append(", status=").append(status);
        sb.append(", endTime=").append(endTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}