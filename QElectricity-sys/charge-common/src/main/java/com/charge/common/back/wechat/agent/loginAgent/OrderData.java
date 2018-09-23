package com.charge.common.back.wechat.agent.loginAgent;

import java.io.Serializable;

/**
 * Created by vincent on 23/09/2018.
 */
public class OrderData implements Serializable {

    private String todayOrder;
    private String todayIncome;
    private String totalOrder;
    private String totalSharing;

    public String getTodayOrder() {
        return todayOrder;
    }

    public void setTodayOrder(String todayOrder) {
        this.todayOrder = todayOrder;
    }

    public String getTodayIncome() {
        return todayIncome;
    }

    public void setTodayIncome(String todayIncome) {
        this.todayIncome = todayIncome;
    }

    public String getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(String totalOrder) {
        this.totalOrder = totalOrder;
    }

    public String getTotalSharing() {
        return totalSharing;
    }

    public void setTotalSharing(String totalSharing) {
        this.totalSharing = totalSharing;
    }

    @Override
    public String toString() {
        return "OrderData{" +
                "todayOrder='" + todayOrder + '\'' +
                ", todayIncome='" + todayIncome + '\'' +
                ", totalOrder='" + totalOrder + '\'' +
                ", totalSharing='" + totalSharing + '\'' +
                '}';
    }
}
