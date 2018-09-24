package com.charge.entity.po.back.wechat.user;

import java.io.Serializable;

/**
 * Created by vincent on 21/09/2018.
 */
public class ChargingRecordBack implements Serializable {

    private String chargingStartTime;
    private String powerBankNO;
    private String chargingTimeAmount;
    private String chargingCost;
    private String chargingStatus;


    public String getChargingStartTime() {
        return chargingStartTime;
    }

    public void setChargingStartTime(String chargingStartTime) {
        this.chargingStartTime = chargingStartTime;
    }

    public String getPowerBankNO() {
        return powerBankNO;
    }

    public void setPowerBankNO(String powerBankNO) {
        this.powerBankNO = powerBankNO;
    }

    public String getChargingTimeAmount() {
        return chargingTimeAmount;
    }

    public void setChargingTimeAmount(String chargingTimeAmount) {
        this.chargingTimeAmount = chargingTimeAmount;
    }

    public String getChargingCost() {
        return chargingCost;
    }

    public void setChargingCost(String chargingCost) {
        this.chargingCost = chargingCost;
    }

    public String getChargingStatus() {
        return chargingStatus;
    }

    public void setChargingStatus(String chargingStatus) {
        this.chargingStatus = chargingStatus;
    }

    @Override
    public String toString() {
        return "ChargingRecordBack{" +
                "chargingStartTime='" + chargingStartTime + '\'' +
                ", powerBankNO='" + powerBankNO + '\'' +
                ", chargingTimeAmount='" + chargingTimeAmount + '\'' +
                ", chargingCost='" + chargingCost + '\'' +
                ", chargingStatus='" + chargingStatus + '\'' +
                '}';
    }
}
