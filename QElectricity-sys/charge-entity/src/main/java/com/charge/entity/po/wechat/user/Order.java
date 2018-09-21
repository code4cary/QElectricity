package com.charge.entity.po.wechat.user;

import com.charge.entity.po.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class Order extends BaseEntity implements Serializable {
    private Integer aid;

    private String orderNum;

    private Date endTime;

    private String payAmount;

    private String payStatus;

    private String powerBankBorrowPlace;

    private String powerBankBackPlace;

    private String powerBankStatus;

    private String powerBankId;

    private String boxChargingId;

    private Date payTime;

    private static final long serialVersionUID = 1L;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount == null ? null : payAmount.trim();
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus == null ? null : payStatus.trim();
    }

    public String getPowerBankBorrowPlace() {
        return powerBankBorrowPlace;
    }

    public void setPowerBankBorrowPlace(String powerBankBorrowPlace) {
        this.powerBankBorrowPlace = powerBankBorrowPlace == null ? null : powerBankBorrowPlace.trim();
    }

    public String getPowerBankBackPlace() {
        return powerBankBackPlace;
    }

    public void setPowerBankBackPlace(String powerBankBackPlace) {
        this.powerBankBackPlace = powerBankBackPlace == null ? null : powerBankBackPlace.trim();
    }

    public String getPowerBankStatus() {
        return powerBankStatus;
    }

    public void setPowerBankStatus(String powerBankStatus) {
        this.powerBankStatus = powerBankStatus == null ? null : powerBankStatus.trim();
    }

    public String getPowerBankId() {
        return powerBankId;
    }

    public void setPowerBankId(String powerBankId) {
        this.powerBankId = powerBankId == null ? null : powerBankId.trim();
    }

    public String getBoxChargingId() {
        return boxChargingId;
    }

    public void setBoxChargingId(String boxChargingId) {
        this.boxChargingId = boxChargingId == null ? null : boxChargingId.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", aid=").append(aid);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", endTime=").append(endTime);
        sb.append(", payAmount=").append(payAmount);
        sb.append(", payStatus=").append(payStatus);
        sb.append(", powerBankBorrowPlace=").append(powerBankBorrowPlace);
        sb.append(", powerBankBackPlace=").append(powerBankBackPlace);
        sb.append(", powerBankStatus=").append(powerBankStatus);
        sb.append(", powerBankId=").append(powerBankId);
        sb.append(", boxChargingId=").append(boxChargingId);
        sb.append(", payTime=").append(payTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}