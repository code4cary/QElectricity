package com.charge.entity.po.back.wechat.agent;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * Created by vincent on 25/09/2018.
 */
public class OrderDataDetail {

    //类型
    private String type;
    //当前月总的订单数
    private String totalOrder;
    // 当前月每天的订单数 按月初到月末排序返回
    private List<Map<Date,Integer>> dayOrder;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(String totalOrder) {
        this.totalOrder = totalOrder;
    }


    public List<Map<Date, Integer>> getDayOrder() {
        return dayOrder;
    }

    public void setDayOrder(List<Map<Date, Integer>> dayOrder) {
        this.dayOrder = dayOrder;
    }

    @Override
    public String toString() {
        return "OrderDataDetail{" +
                "type='" + type + '\'' +
                ", totalOrder='" + totalOrder + '\'' +
                ", dayOrder=" + dayOrder +
                '}';
    }

    public static void main(String... args) {
        OrderDataDetail orderDataDetail = new OrderDataDetail();
        List<Map<Date,Integer>> list = new ArrayList<>();
        Map<Date,Integer> map1 =  new HashMap<>();
        map1.put(new Date(),1);
        list.add(map1);
        Map<Date,Integer> map2 =  new HashMap<>();
        map2.put(new Date(),2);
        list.add(map2);


        orderDataDetail.setDayOrder(list);
        orderDataDetail.setTotalOrder("55");
        orderDataDetail.setType("1");
        Object json = JSON.toJSON(orderDataDetail);
        System.out.println(json);

    }
}
