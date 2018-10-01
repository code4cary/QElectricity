package com.charge.entity.model;

import java.io.Serializable;

/**
 * Created by vincent on 27/09/2018.
 */
public class ModifyPriceTypeCBDO implements Serializable {

    private String freeTime;

    private String pricePerHour;

    private String topPricePerDay;

    private String AgentId;

    private String shopId;

    public String getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(String freeTime) {
        this.freeTime = freeTime;
    }

    public String getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(String pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public String getTopPricePerDay() {
        return topPricePerDay;
    }

    public void setTopPricePerDay(String topPricePerDay) {
        this.topPricePerDay = topPricePerDay;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }


    public String getAgentId() {
        return AgentId;
    }

    public void setAgentId(String agentId) {
        AgentId = agentId;
    }

    @Override
    public String toString() {
        return "ModifyPriceTypeCBDO{" +
                "freeTime='" + freeTime + '\'' +
                ", pricePerHour='" + pricePerHour + '\'' +
                ", topPricePerDay='" + topPricePerDay + '\'' +
                ", AgentId='" + AgentId + '\'' +
                ", shopId='" + shopId + '\'' +
                '}';
    }
}
