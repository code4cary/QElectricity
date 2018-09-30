package com.charge.entity.po.wechat.user;

import com.charge.entity.po.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class Order extends BaseEntity implements Serializable {
    private Integer aid;

    //订单编号
    private String orderNum;

    private Date endTime;

    private String payAmount;

    private String payStatus;

    private String orderType;

    private String powerBankBorrowPlace;

    private String powerBankBackPlace;

    private String powerBankStatus;

    private String powerBankId;

    private String boxChargingId;

    private Date payTime;

    private String haveShowed;

    /**
     * 订单表关联流水表  一对一
     */
    private WaterBills waterBills;

    /**
     * 订单表关联账户表  多对一
     */
    private Account account;

    public String getHaveShowed() {
        return haveShowed;
    }

    public void setHaveShowed(String haveShowed) {
        this.haveShowed = haveShowed;
    }

    private static final long serialVersionUID = 1L;

    public WaterBills getWaterBills() {
        return waterBills;
    }

    public void setWaterBills(WaterBills waterBills) {
        this.waterBills = waterBills;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


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

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
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
        return "Order{" +
                "aid=" + aid +
                ", orderNum='" + orderNum + '\'' +
                ", endTime=" + endTime +
                ", payAmount='" + payAmount + '\'' +
                ", payStatus='" + payStatus + '\'' +
                ", orderType='" + orderType + '\'' +
                ", powerBankBorrowPlace='" + powerBankBorrowPlace + '\'' +
                ", powerBankBackPlace='" + powerBankBackPlace + '\'' +
                ", powerBankStatus='" + powerBankStatus + '\'' +
                ", powerBankId='" + powerBankId + '\'' +
                ", boxChargingId='" + boxChargingId + '\'' +
                ", payTime=" + payTime +
                ", haveShowed='" + haveShowed + '\'' +
                ", waterBills=" + waterBills +
                ", account=" + account +
                '}';
    }
}