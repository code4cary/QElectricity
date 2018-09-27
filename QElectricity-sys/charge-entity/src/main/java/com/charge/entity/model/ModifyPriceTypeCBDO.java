package com.charge.entity.model;

import com.charge.entity.po.device.PriceTypeCB;

import java.io.Serializable;

/**
 * Created by vincent on 27/09/2018.
 */
public class ModifyPriceTypeCBDO implements Serializable {

    private PriceTypeCB priceTypeCB;

    private String AgentId;

    private String shopId;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public PriceTypeCB getPriceTypeCB() {
        return priceTypeCB;
    }

    public void setPriceTypeCB(PriceTypeCB priceTypeCB) {
        this.priceTypeCB = priceTypeCB;
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
                "priceTypeCB=" + priceTypeCB +
                ", AgentId='" + AgentId + '\'' +
                ", shopId='" + shopId + '\'' +
                '}';
    }
}
