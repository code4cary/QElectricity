package com.charge.service.biz.wechat.agent;

import com.charge.entity.po.back.wechat.agent.IncomeData;
import com.charge.entity.po.back.wechat.agent.OrderDataDetail;
import com.charge.entity.po.wechat.user.Order;
import com.charge.service.biz.base.BaseService;

import java.util.Map;

/**
 * Created by vincent on 24/09/2018.
 */
public interface OrderService extends BaseService<Order,Integer> {

    OrderDataDetail findOrderDataNumPerDay(Map<String, Object> queryDataMap);

    IncomeData findIncomeDataPerDay(Map<String, Object> queryDataMap);
}
