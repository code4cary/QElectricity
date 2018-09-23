package com.charge.common.back.wechat.user;

import java.io.Serializable;

/**
 * Created by vincent on 18/09/2018.
 */

/**
 * 附近商家信息
 */
public class ShopInfoBack implements Serializable{

    private String shopPhoto;
    private String shopName;
    private String businessTime;
    private String address;
    private String longitude;
    private String latitude;
    private String shopStatus;
    private String canBorrowNum;
    private String canBackNum;
    private String distance;
    private String shopTel;


    public String getCanBackwNum() {return canBackNum;}

    public String getShopPhoto() {
        return shopPhoto;
    }

    public String getShopName() {
        return shopName;
    }

    public String getBusinessTime() {
        return businessTime;
    }

    public String getAddress() {
        return address;
    }

    public String getShopStatus() {
        return shopStatus;
    }

    public String getCanBorrowNum() {
        return canBorrowNum;
    }

    public String getDistance() {
        return distance;
    }

    public String getShopTel() {
        return shopTel;
    }

    public void setShopPhoto(String shopPhoto) {
        this.shopPhoto = shopPhoto;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
    }

    public void setCanBorrowNum(String canBorrowNum) {
        this.canBorrowNum = canBorrowNum;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setShopTel(String shopTel) {
        this.shopTel = shopTel;
    }

    public void setCanBackwNum(String canBackNum) {this.canBackNum = canBackNum;}


    public String getCanBackNum() {
        return canBackNum;
    }

    public void setCanBackNum(String canBackNum) {
        this.canBackNum = canBackNum;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "ShopInfoBack{" +
                "shopPhoto='" + shopPhoto + '\'' +
                ", shopName='" + shopName + '\'' +
                ", businessTime='" + businessTime + '\'' +
                ", address='" + address + '\'' +
                ", shopStatus='" + shopStatus + '\'' +
                ", canBorrowNum='" + canBorrowNum + '\'' +
                ", canBackNum='" + canBackNum + '\'' +
                ", distance='" + distance + '\'' +
                ", shopTel='" + shopTel + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }
}
