package com.charge.entity.po.wechat.agent;

import com.charge.entity.po.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class AgentWithdrawal extends BaseEntity implements Serializable {
    private Integer agId;

    private String withdrawalNo;

    private String amount;

    private String status;

    private Date createTime;

    private Date endTime;

    private String withdrawalType;

    private static final long serialVersionUID = 1L;

    /**
     * t_agent_withdrawal关联t_agent 多对一
     */
    private Agent agent;

    public String getWithdrawalType() {
        return withdrawalType;
    }

    public void setWithdrawalType(String withdrawalType) {
        this.withdrawalType = withdrawalType;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Integer getAgId() {
        return agId;
    }

    public void setAgId(Integer agId) {
        this.agId = agId;
    }

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
        return "AgentWithdrawal{" +
                "agId=" + agId +
                ", withdrawalNo='" + withdrawalNo + '\'' +
                ", amount='" + amount + '\'' +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", endTime=" + endTime +
                ", withdrawalType='" + withdrawalType + '\'' +
                ", agent=" + agent +
                '}';
    }
}