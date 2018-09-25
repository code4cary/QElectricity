package com.charge.service.biz.wechat.agent.impl;

import com.charge.dao.mapper.wechat.user.OrderMapper;
import com.charge.entity.po.back.wechat.agent.OrderDataDetail;
import com.charge.entity.po.wechat.user.Order;
import com.charge.service.biz.base.impl.BaseServiceImpl;
import com.charge.service.biz.wechat.agent.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 25/09/2018.
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, Integer> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void initBaseMapper() {

        setBaseMapper(orderMapper);
    }

    /**
     * 通过agentId,date,type查询代理商每月中的每天的的订单数量
     * 实际type不管为充电线还是充电箱,每一天的订单总数量是一样的
     *
     * @param queryDataMap
     * @return
     */
    @Override
    public OrderDataDetail findOrderDataNumPerDay(Map<String, Object> queryDataMap) {
        List<Map<Date, Integer>> orderList = orderMapper.findOrderDataNumPerDay(queryDataMap);
        //订单总数
        String totalOrder = String.valueOf(orderList.size());

        //类型
        String type = String.valueOf(queryDataMap.get("type"));

        //封装数据
        OrderDataDetail orderDataDetail = new OrderDataDetail();
        orderDataDetail.setTotalOrder(totalOrder);
        orderDataDetail.setType(type);
        orderDataDetail.setDayOrder(orderList);

        return orderDataDetail;
    }
}
