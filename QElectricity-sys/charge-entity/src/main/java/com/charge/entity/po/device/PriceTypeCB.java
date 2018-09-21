package com.charge.entity.po.device;

import com.charge.entity.po.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PriceTypeCB extends BaseEntity implements Serializable {
    private String freeTime;

    private String pricePerHour;

    private String topPricePerDay;

    private Date updateTime;

    /**
     * t_price_type关联t_charging_box  一对多关系
     */
    private List<ChargingBox> chargingBoxList;

    private static final long serialVersionUID = 1L;

    public List<ChargingBox> getChargingBoxList() {
        return chargingBoxList;
    }

    public void setChargingBoxList(List<ChargingBox> chargingBoxList) {
        this.chargingBoxList = chargingBoxList;
    }

    public String getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(String freeTime) {
        this.freeTime = freeTime == null ? null : freeTime.trim();
    }

    public String getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(String pricePerHour) {
        this.pricePerHour = pricePerHour == null ? null : pricePerHour.trim();
    }

    public String getTopPricePerDay() {
        return topPricePerDay;
    }

    public void setTopPricePerDay(String topPricePerDay) {
        this.topPricePerDay = topPricePerDay == null ? null : topPricePerDay.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "PriceTypeCB{" +
                "freeTime='" + freeTime + '\'' +
                ", pricePerHour='" + pricePerHour + '\'' +
                ", topPricePerDay='" + topPricePerDay + '\'' +
                ", updateTime=" + updateTime +
                ", chargingBoxList=" + chargingBoxList +
                '}';
    }
}