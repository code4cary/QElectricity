package com.charge.common.back.wechat.agent.loginAgent;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by vincent on 23/09/2018.
 */
public class LoginAgentBack implements Serializable{


    //订单数据
    private Map<String,String> orderData;

    //充电宝状态
    private Map<String,String> chargingBoxStatus;


    //预警数量
    private String earlyWarningNum;


    public Map<String, String> getOrderData() {
        return orderData;
    }

    public void setOrderData(Map<String, String> orderData) {
        this.orderData = orderData;
    }

    public String getEarlyWarningNum() {
        return earlyWarningNum;
    }

    public void setEarlyWarningNum(String earlyWarningNum) {
        this.earlyWarningNum = earlyWarningNum;
    }

    public Map<String, String> getChargingBoxStatus() {
        return chargingBoxStatus;
    }

    public void setChargingBoxStatus(Map<String, String> chargingBoxStatus) {
        this.chargingBoxStatus = chargingBoxStatus;
    }

    @Override
    public String toString() {
        return "LoginAgentBack{" +
                "orderData=" + orderData +
                ", chargingBoxStatus=" + chargingBoxStatus +
                ", earlyWarningNum='" + earlyWarningNum + '\'' +
                '}';
    }
}
