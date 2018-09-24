package com.charge.entity.po.wechat.agent;

import com.charge.entity.po.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class Shop extends BaseEntity implements Serializable {
    private Integer agId;

    private String shopNo;

    private String logo;

    private String name;

    private String address;

    private String city;

    private String longitude;

    private String latitude;

    private String starLevel;

    private String areaLabel;

    private String categoryLabel;

    private String locationLabel;

    private String business;

    private String shopStatus;

    private String contractPersonName;

    private String contractPersonPhone;

    private String idNumber;

    private String shopPhoto;

    private String contractPhoto;

    private String bankAccount;

    private String businessTime;

    private Date leaveTime;

    private Date updateTime;

    /**
     * t_shop关联t_agent 多对一关系
     */
    //private Agent agent;

    private static final long serialVersionUID = 1L;

//    public Agent getAgent() {
//        return agent;
//    }
//
//    public void setAgent(Agent agent) {
//        this.agent = agent;
//    }

    public Integer getAgId() {
        return agId;
    }

    public void setAgId(Integer agId) {
        this.agId = agId;
    }

    public String getShopNo() {
        return shopNo;
    }

    public void setShopNo(String shopNo) {
        this.shopNo = shopNo == null ? null : shopNo.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(String starLevel) {
        this.starLevel = starLevel == null ? null : starLevel.trim();
    }

    public String getAreaLabel() {
        return areaLabel;
    }

    public void setAreaLabel(String areaLabel) {
        this.areaLabel = areaLabel == null ? null : areaLabel.trim();
    }

    public String getCategoryLabel() {
        return categoryLabel;
    }

    public void setCategoryLabel(String categoryLabel) {
        this.categoryLabel = categoryLabel == null ? null : categoryLabel.trim();
    }

    public String getLocationLabel() {
        return locationLabel;
    }

    public void setLocationLabel(String locationLabel) {
        this.locationLabel = locationLabel == null ? null : locationLabel.trim();
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business == null ? null : business.trim();
    }

    public String getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus == null ? null : shopStatus.trim();
    }

    public String getContractPersonName() {
        return contractPersonName;
    }

    public void setContractPersonName(String contractPersonName) {
        this.contractPersonName = contractPersonName == null ? null : contractPersonName.trim();
    }

    public String getContractPersonPhone() {
        return contractPersonPhone;
    }

    public void setContractPersonPhone(String contractPersonPhone) {
        this.contractPersonPhone = contractPersonPhone == null ? null : contractPersonPhone.trim();
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
    }

    public String getShopPhoto() {
        return shopPhoto;
    }

    public void setShopPhoto(String shopPhoto) {
        this.shopPhoto = shopPhoto == null ? null : shopPhoto.trim();
    }

    public String getContractPhoto() {
        return contractPhoto;
    }

    public void setContractPhoto(String contractPhoto) {
        this.contractPhoto = contractPhoto == null ? null : contractPhoto.trim();
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
    }

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime == null ? null : businessTime.trim();
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "agId=" + agId +
                ", shopNo='" + shopNo + '\'' +
                ", logo='" + logo + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", starLevel='" + starLevel + '\'' +
                ", areaLabel='" + areaLabel + '\'' +
                ", categoryLabel='" + categoryLabel + '\'' +
                ", locationLabel='" + locationLabel + '\'' +
                ", business='" + business + '\'' +
                ", shopStatus='" + shopStatus + '\'' +
                ", contractPersonName='" + contractPersonName + '\'' +
                ", contractPersonPhone='" + contractPersonPhone + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", shopPhoto='" + shopPhoto + '\'' +
                ", contractPhoto='" + contractPhoto + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", businessTime='" + businessTime + '\'' +
                ", leaveTime=" + leaveTime +
                ", updateTime=" + updateTime +
                ", agent=" + "agent" +
                '}';
    }
}