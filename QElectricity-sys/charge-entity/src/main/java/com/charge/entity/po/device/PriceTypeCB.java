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

    private static final long serialVersionUID = 1L;

    /**
     * @des:定价策略关联多个充电箱 one2many
     * @associate:
     * @see:
     */
    private List<ChargingBox> chargingBoxList;

    public List<ChargingBox> getChargingBoxList() {
        return chargingBoxList;
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

    public void setChargingBoxList(List<ChargingBox> chargingBoxList) {
        this.chargingBoxList = chargingBoxList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", freeTime=").append(freeTime);
        sb.append(", pricePerHour=").append(pricePerHour);
        sb.append(", topPricePerDay=").append(topPricePerDay);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}