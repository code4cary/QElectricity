package com.charge.entity.po.back.wechat.agent;


import java.io.Serializable;

/**
 * Created by vincent on 26/09/2018.
 */
public class OrderRecord implements Serializable {

    //用户手机号,注意是借充电宝的用户手机号
    private String userPhone;
    //商户名,即用户从哪个商户借的充电宝
    private String shopName;
    //消费金额
    private String cost;
    //开始时间
    private String startTime;
    //结束时间
    private  String endTime;
    //订单状态
    private String orderStatus;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }


    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }


    @Override
    public String toString() {
        return "OrderRecord{" +
                "userPhone='" + userPhone + '\'' +
                ", shopName='" + shopName + '\'' +
                ", cost='" + cost + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
