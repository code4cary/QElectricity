package com.charge.entity.po.back.wechat.agent;

/**
 * Created by vincent on 24/09/2018.
 */
public class TodayIncome {


    private String shopName;
    private String totalIncome;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(String totalIncome) {
        this.totalIncome = totalIncome;
    }

    @Override
    public String toString() {
        return "TodayIncome{" +
                "shopName='" + shopName + '\'' +
                ", totalIncome='" + totalIncome + '\'' +
                '}';
    }
}
