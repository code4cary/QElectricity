package com.charge.entity.po.wechat.agent;

import java.io.Serializable;

public class CreateShopDO implements Serializable {

    private String id;
    private String agentID;
    private String city;
    private String areaLabel;
    private String shopAddress;

    /**
     * 添加经纬度字段  2018-09-27 vincent
     */
    private String longitude;

    private String latitude;

    private String shopName;
    private String contractPerson;
    private String contractPhone;
    private String shopPhoto;
    private String contractPhoto;
    private String bankAccount;
    private String startBusinessTime;
    private String categoryLabel;
    private String locationLabel;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAgentID() {
        return agentID;
    }

    public void setAgentID(String agentID) {
        this.agentID = agentID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAreaLabel() {
        return areaLabel;
    }

    public void setAreaLabel(String areaLabel) {
        this.areaLabel = areaLabel;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getContractPerson() {
        return contractPerson;
    }

    public void setContractPerson(String contractPerson) {
        this.contractPerson = contractPerson;
    }

    public String getContractPhone() {
        return contractPhone;
    }

    public void setContractPhone(String contractPhone) {
        this.contractPhone = contractPhone;
    }

    public String getShopPhoto() {
        return shopPhoto;
    }

    public void setShopPhoto(String shopPhoto) {
        this.shopPhoto = shopPhoto;
    }

    public String getContractPhoto() {
        return contractPhoto;
    }

    public void setContractPhoto(String contractPhoto) {
        this.contractPhoto = contractPhoto;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getStartBusinessTime() {
        return startBusinessTime;
    }

    public void setStartBusinessTime(String startBusinessTime) {
        this.startBusinessTime = startBusinessTime;
    }

    public String getCategoryLabel() {
        return categoryLabel;
    }

    public void setCategoryLabel(String categoryLabel) {
        this.categoryLabel = categoryLabel;
    }

    public String getLocationLabel() {
        return locationLabel;
    }

    public void setLocationLabel(String locationLabel) {
        this.locationLabel = locationLabel;
    }

}
