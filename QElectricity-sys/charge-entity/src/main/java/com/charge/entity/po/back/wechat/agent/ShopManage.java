package com.charge.entity.po.back.wechat.agent;

import java.io.Serializable;

/**
 * Created by vincent on 27/09/2018.
 */
public class ShopManage implements Serializable {

    private String shopName;
    private String shopContract;
    private String shopAddress;
    private String shopToTalIncome;
    private String chargingBoxNum;

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

    public String getShopToTalIncome() {
        return shopToTalIncome;
    }

    public void setShopToTalIncome(String shopToTalIncome) {
        this.shopToTalIncome = shopToTalIncome;
    }

    public String getChargingBoxNum() {
        return chargingBoxNum;
    }

    public void setChargingBoxNum(String chargingBoxNum) {
        this.chargingBoxNum = chargingBoxNum;
    }


    @Override
    public String toString() {
        return "ShopManage{" +
                "shopName='" + shopName + '\'' +
                ", shopContract='" + shopContract + '\'' +
                ", shopAddress='" + shopAddress + '\'' +
                ", shopToTalIncome='" + shopToTalIncome + '\'' +
                ", chargingBoxNum='" + chargingBoxNum + '\'' +
                '}';
    }
}
