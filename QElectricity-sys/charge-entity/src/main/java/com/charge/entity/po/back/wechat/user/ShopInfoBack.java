package com.charge.entity.po.back.wechat.user;

import java.io.Serializable;

/**
 * Created by vincent on 18/09/2018.
 */

/**
 * 附近商家信息
 */
public class ShopInfoBack implements Serializable{

    private String shopId;
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

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopPhoto() {
        return shopPhoto;
    }

    public void setShopPhoto(String shopPhoto) {
        this.shopPhoto = shopPhoto;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
    }

    public String getCanBorrowNum() {
        return canBorrowNum;
    }

    public void setCanBorrowNum(String canBorrowNum) {
        this.canBorrowNum = canBorrowNum;
    }

    public String getCanBackNum() {
        return canBackNum;
    }

    public void setCanBackNum(String canBackNum) {
        this.canBackNum = canBackNum;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getShopTel() {
        return shopTel;
    }

    public void setShopTel(String shopTel) {
        this.shopTel = shopTel;
    }

    @Override
    public String toString() {
        return "ShopInfoBack{" +
                "shopId='" + shopId + '\'' +
                ", shopPhoto='" + shopPhoto + '\'' +
                ", shopName='" + shopName + '\'' +
                ", businessTime='" + businessTime + '\'' +
                ", address='" + address + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", shopStatus='" + shopStatus + '\'' +
                ", canBorrowNum='" + canBorrowNum + '\'' +
                ", canBackNum='" + canBackNum + '\'' +
                ", distance='" + distance + '\'' +
                ", shopTel='" + shopTel + '\'' +
                '}';
    }
}
