package com.charge.entity.po.device;

import com.charge.entity.po.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ChargingBox extends BaseEntity implements Serializable {
    private String boxCustomerId;

    private String boxDate;

    private String boxSn;

    private String location;

    private String status;

    private String model;

    private String softwareVersion;

    private String business;

    private String borrowNum;

    private String powerBankNum;

    private String canBorrowNum;

    private String canBackNum;

    private String malfunctionNum;

    private String speakerStatus;

    private String simCcid;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     * @des:充电箱关联多个充电宝 one2many
     * @associate:
     * @see:

     */
    private List<PowerBank> powerBankList;

    public List<PowerBank> getPowerBankList() {
        return powerBankList;
    }

    public String getBoxCustomerId() {
        return boxCustomerId;
    }

    public void setBoxCustomerId(String boxCustomerId) {
        this.boxCustomerId = boxCustomerId == null ? null : boxCustomerId.trim();
    }

    public String getBoxDate() {
        return boxDate;
    }

    public void setBoxDate(String boxDate) {
        this.boxDate = boxDate == null ? null : boxDate.trim();
    }

    public String getBoxSn() {
        return boxSn;
    }

    public void setBoxSn(String boxSn) {
        this.boxSn = boxSn == null ? null : boxSn.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion == null ? null : softwareVersion.trim();
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business == null ? null : business.trim();
    }

    public String getBorrowNum() {
        return borrowNum;
    }

    public void setBorrowNum(String borrowNum) {
        this.borrowNum = borrowNum == null ? null : borrowNum.trim();
    }

    public String getPowerBankNum() {
        return powerBankNum;
    }

    public void setPowerBankNum(String powerBankNum) {
        this.powerBankNum = powerBankNum == null ? null : powerBankNum.trim();
    }

    public String getCanBorrowNum() {
        return canBorrowNum;
    }

    public void setCanBorrowNum(String canBorrowNum) {
        this.canBorrowNum = canBorrowNum == null ? null : canBorrowNum.trim();
    }

    public String getCanBackNum() {
        return canBackNum;
    }

    public void setCanBackNum(String canBackNum) {
        this.canBackNum = canBackNum == null ? null : canBackNum.trim();
    }

    public String getMalfunctionNum() {
        return malfunctionNum;
    }

    public void setMalfunctionNum(String malfunctionNum) {
        this.malfunctionNum = malfunctionNum == null ? null : malfunctionNum.trim();
    }

    public String getSpeakerStatus() {
        return speakerStatus;
    }

    public void setSpeakerStatus(String speakerStatus) {
        this.speakerStatus = speakerStatus == null ? null : speakerStatus.trim();
    }

    public String getSimCcid() {
        return simCcid;
    }

    public void setSimCcid(String simCcid) {
        this.simCcid = simCcid == null ? null : simCcid.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setPowerBankList(List<PowerBank> powerBankList) {
        this.powerBankList = powerBankList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", boxCustomerId=").append(boxCustomerId);
        sb.append(", boxDate=").append(boxDate);
        sb.append(", boxSn=").append(boxSn);
        sb.append(", location=").append(location);
        sb.append(", status=").append(status);
        sb.append(", model=").append(model);
        sb.append(", softwareVersion=").append(softwareVersion);
        sb.append(", business=").append(business);
        sb.append(", borrowNum=").append(borrowNum);
        sb.append(", powerBankNum=").append(powerBankNum);
        sb.append(", canBorrowNum=").append(canBorrowNum);
        sb.append(", canBackNum=").append(canBackNum);
        sb.append(", malfunctionNum=").append(malfunctionNum);
        sb.append(", speakerStatus=").append(speakerStatus);
        sb.append(", simCcid=").append(simCcid);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}