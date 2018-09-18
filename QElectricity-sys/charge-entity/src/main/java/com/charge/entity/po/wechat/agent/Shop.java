package com.charge.entity.po.wechat.agent;

import com.charge.entity.po.base.BaseEntity;
import com.charge.entity.po.device.ChargingBox;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Shop extends BaseEntity implements Serializable {
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

    private String locationTag;

    private String business;

    private String contractPersonName;

    private String contractPersonPhone;

    private String idNumber;

    private String shopPhoto;

    private String contractPhoto;

    private String bankAccount;

    private String startBusinessTime;

    private String endBusinessTime;

    private Date leaveTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;


    /**
     * @des:商户关联多个充电箱 one2many
     * @associate:
     * @see:

     */
    private List<ChargingBox> chargingBoxList;

    public List<ChargingBox> getChargingBoxList() {
        return chargingBoxList;
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

    public String getLocationTag() {
        return locationTag;
    }

    public void setLocationTag(String locationTag) {
        this.locationTag = locationTag == null ? null : locationTag.trim();
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business == null ? null : business.trim();
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

    public String getStartBusinessTime() {
        return startBusinessTime;
    }

    public void setStartBusinessTime(String startBusinessTime) {
        this.startBusinessTime = startBusinessTime == null ? null : startBusinessTime.trim();
    }

    public String getEndBusinessTime() {
        return endBusinessTime;
    }

    public void setEndBusinessTime(String endBusinessTime) {
        this.endBusinessTime = endBusinessTime == null ? null : endBusinessTime.trim();
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

    public void setChargingBoxList(List<ChargingBox> chargingBoxList) {
        this.chargingBoxList = chargingBoxList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", shopNo=").append(shopNo);
        sb.append(", logo=").append(logo);
        sb.append(", name=").append(name);
        sb.append(", address=").append(address);
        sb.append(", city=").append(city);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", starLevel=").append(starLevel);
        sb.append(", areaLabel=").append(areaLabel);
        sb.append(", categoryLabel=").append(categoryLabel);
        sb.append(", locationTag=").append(locationTag);
        sb.append(", business=").append(business);
        sb.append(", contractPersonName=").append(contractPersonName);
        sb.append(", contractPersonPhone=").append(contractPersonPhone);
        sb.append(", idNumber=").append(idNumber);
        sb.append(", shopPhoto=").append(shopPhoto);
        sb.append(", contractPhoto=").append(contractPhoto);
        sb.append(", bankAccount=").append(bankAccount);
        sb.append(", startBusinessTime=").append(startBusinessTime);
        sb.append(", endBusinessTime=").append(endBusinessTime);
        sb.append(", leaveTime=").append(leaveTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}