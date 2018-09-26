package com.charge.entity.po.back.wechat.agent;

import java.io.Serializable;

/**
 * Created by vincent on 26/09/2018.
 */
public class EarlyWarning implements Serializable {

    //商户名字
    private String shopName;

    //商户联系方式
    private String shopContract;

    //商户地址
    private String shopAddress;

    //充电宝数量
    private String powerBankNum;

    //设备编号
    private String deviceNO;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopContract() {
        return shopContract;
    }

    public void setShopContract(String shopContract) {
        this.shopContract = shopContract;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getPowerBankNum() {
        return powerBankNum;
    }

    public void setPowerBankNum(String powerBankNum) {
        this.powerBankNum = powerBankNum;
    }

    public String getDeviceNO() {
        return deviceNO;
    }

    public void setDeviceNO(String deviceNO) {
        this.deviceNO = deviceNO;
    }


    @Override
    public String toString() {
        return "EarlyWarning{" +
                "shopName='" + shopName + '\'' +
                ", shopContract='" + shopContract + '\'' +
                ", shopAddress='" + shopAddress + '\'' +
                ", powerBankNum='" + powerBankNum + '\'' +
                ", deviceNO='" + deviceNO + '\'' +
                '}';
    }
}
