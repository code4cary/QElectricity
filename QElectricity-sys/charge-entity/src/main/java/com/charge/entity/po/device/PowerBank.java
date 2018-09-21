package com.charge.entity.po.device;

import com.charge.entity.po.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PowerBank extends BaseEntity implements Serializable {
    private String lockId;

    private String lockStatus;

    private String lockOperationStatus;

    private String pbCustomerId;

    private String pbModel;

    private String pbDate;

    private String pbSn;

    private String pbCapacity;

    private String pbStatus;

    private String pbPriority;

    private String borrowTimes;

    private String totalUseTime;

    private Date updateTime;

    /**
     * t_power_bank关联t_charging_box  多对多关系
     */
    private List<ChargingBox> chargingBoxList;

    private static final long serialVersionUID = 1L;

    public List<ChargingBox> getChargingBoxList() {
        return chargingBoxList;
    }

    public void setChargingBoxList(List<ChargingBox> chargingBoxList) {
        this.chargingBoxList = chargingBoxList;
    }

    public String getLockId() {
        return lockId;
    }

    public void setLockId(String lockId) {
        this.lockId = lockId == null ? null : lockId.trim();
    }

    public String getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(String lockStatus) {
        this.lockStatus = lockStatus == null ? null : lockStatus.trim();
    }

    public String getLockOperationStatus() {
        return lockOperationStatus;
    }

    public void setLockOperationStatus(String lockOperationStatus) {
        this.lockOperationStatus = lockOperationStatus == null ? null : lockOperationStatus.trim();
    }

    public String getPbCustomerId() {
        return pbCustomerId;
    }

    public void setPbCustomerId(String pbCustomerId) {
        this.pbCustomerId = pbCustomerId == null ? null : pbCustomerId.trim();
    }

    public String getPbModel() {
        return pbModel;
    }

    public void setPbModel(String pbModel) {
        this.pbModel = pbModel == null ? null : pbModel.trim();
    }

    public String getPbDate() {
        return pbDate;
    }

    public void setPbDate(String pbDate) {
        this.pbDate = pbDate == null ? null : pbDate.trim();
    }

    public String getPbSn() {
        return pbSn;
    }

    public void setPbSn(String pbSn) {
        this.pbSn = pbSn == null ? null : pbSn.trim();
    }

    public String getPbCapacity() {
        return pbCapacity;
    }

    public void setPbCapacity(String pbCapacity) {
        this.pbCapacity = pbCapacity == null ? null : pbCapacity.trim();
    }

    public String getPbStatus() {
        return pbStatus;
    }

    public void setPbStatus(String pbStatus) {
        this.pbStatus = pbStatus == null ? null : pbStatus.trim();
    }

    public String getPbPriority() {
        return pbPriority;
    }

    public void setPbPriority(String pbPriority) {
        this.pbPriority = pbPriority == null ? null : pbPriority.trim();
    }

    public String getBorrowTimes() {
        return borrowTimes;
    }

    public void setBorrowTimes(String borrowTimes) {
        this.borrowTimes = borrowTimes == null ? null : borrowTimes.trim();
    }

    public String getTotalUseTime() {
        return totalUseTime;
    }

    public void setTotalUseTime(String totalUseTime) {
        this.totalUseTime = totalUseTime == null ? null : totalUseTime.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    @Override
    public String toString() {
        return "PowerBank{" +
                "lockId='" + lockId + '\'' +
                ", lockStatus='" + lockStatus + '\'' +
                ", lockOperationStatus='" + lockOperationStatus + '\'' +
                ", pbCustomerId='" + pbCustomerId + '\'' +
                ", pbModel='" + pbModel + '\'' +
                ", pbDate='" + pbDate + '\'' +
                ", pbSn='" + pbSn + '\'' +
                ", pbCapacity='" + pbCapacity + '\'' +
                ", pbStatus='" + pbStatus + '\'' +
                ", pbPriority='" + pbPriority + '\'' +
                ", borrowTimes='" + borrowTimes + '\'' +
                ", totalUseTime='" + totalUseTime + '\'' +
                ", updateTime=" + updateTime +
                ", chargingBoxList=" + chargingBoxList +
                '}';
    }
}