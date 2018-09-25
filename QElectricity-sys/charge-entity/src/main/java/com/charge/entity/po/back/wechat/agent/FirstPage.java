package com.charge.entity.po.back.wechat.agent;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by vincent on 23/09/2018.
 */
public class FirstPage implements Serializable{


    //订单数据
    private OrderData orderData;

    //充电宝状态
    private ChargingBoxStatus chargingBoxStatus;

    //预警数量
    private Map<String,String> earlyWarningNum;

    //代理商id
    private Map<String,String> agentId;

    public OrderData getOrderData() {
        return orderData;
    }

    public void setOrderData(OrderData orderData) {
        this.orderData = orderData;
    }

    public ChargingBoxStatus getChargingBoxStatus() {
        return chargingBoxStatus;
    }

    public void setChargingBoxStatus(ChargingBoxStatus chargingBoxStatus) {
        this.chargingBoxStatus = chargingBoxStatus;
    }

    public Map<String, String> getEarlyWarningNum() {
        return earlyWarningNum;
    }

    public void setEarlyWarningNum(Map<String, String> earlyWarningNum) {
        this.earlyWarningNum = earlyWarningNum;
    }

    public Map<String, String> getAgentId() {
        return agentId;
    }

    public void setAgentId(Map<String, String> agentId) {
        this.agentId = agentId;
    }

    @Override
    public String toString() {
        return "FirstPage{" +
                "orderData=" + orderData +
                ", chargingBoxStatus=" + chargingBoxStatus +
                ", earlyWarningNum=" + earlyWarningNum +
                ", agentId=" + agentId +
                '}';
    }
}
