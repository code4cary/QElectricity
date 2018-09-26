package com.charge.entity.po.back.wechat.agent;

import java.io.Serializable;

/**
 * Created by vincent on 26/09/2018.
 */
public class DeviceManage implements Serializable {

    //充电箱编号
    private String chargingBoxNO;
    //充电宝个数
    private String powerBankNum;
    //合作点名称,即商户名
    private String shopName;
    //充电箱状态 "离线","在线"
    private String status;

    public String getChargingBoxNum() {
        return chargingBoxNO;
    }

    public void setChargingBoxNum(String chargingBoxNum) {
        this.chargingBoxNO = chargingBoxNum;
    }

    public String getPowerBankNum() {
        return powerBankNum;
    }

    public void setPowerBankNum(String powerBankNum) {
        this.powerBankNum = powerBankNum;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DeviceManage{" +
                "chargingBoxNum='" + chargingBoxNO + '\'' +
                ", powerBankNum='" + powerBankNum + '\'' +
                ", shopName='" + shopName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
