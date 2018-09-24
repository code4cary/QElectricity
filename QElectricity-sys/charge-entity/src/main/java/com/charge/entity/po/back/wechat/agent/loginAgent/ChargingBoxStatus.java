package com.charge.entity.po.back.wechat.agent.loginAgent;

import java.io.Serializable;

/**
 * Created by vincent on 23/09/2018.
 */
public class ChargingBoxStatus implements Serializable{
    //充电箱状态
    private String toTalNum;
    private String offlineNum;

    public String getToTalNum() {
        return toTalNum;
    }

    public void setToTalNum(String toTalNum) {
        this.toTalNum = toTalNum;
    }

    public String getOfflineNum() {
        return offlineNum;
    }

    public void setOfflineNum(String offlineNum) {
        this.offlineNum = offlineNum;
    }

    @Override
    public String toString() {
        return "ChargingBoxStatus{" +
                "toTalNum='" + toTalNum + '\'' +
                ", offlineNum='" + offlineNum + '\'' +
                '}';
    }
}
