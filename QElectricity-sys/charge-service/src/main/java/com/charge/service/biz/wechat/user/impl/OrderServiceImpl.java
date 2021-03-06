package com.charge.service.biz.wechat.user.impl;

import com.charge.dao.mapper.wechat.agent.AgentMapper;
import com.charge.dao.mapper.wechat.user.OrderMapper;
import com.charge.entity.po.back.wechat.agent.IncomeData;
import com.charge.entity.po.back.wechat.agent.OrderDataDetail;
import com.charge.entity.po.back.wechat.agent.OrderRecord;
import com.charge.entity.po.wechat.user.Order;
import com.charge.service.biz.base.impl.BaseServiceImpl;
import com.charge.service.biz.wechat.user.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 25/09/2018.
 */
@Slf4j
@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, Integer> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private AgentMapper agentMapper;

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
        List<Map<String, Object>> orderList = orderMapper.findOrderDataNumPerDay(queryDataMap);
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

    /**
     * 通过agentId,date,type查询代理商每月中的每天的的收益数据
     *
     * @param queryDataMap
     * @return
     */
    @Override
    public IncomeData findIncomeDataPerDay(Map<String, Object> queryDataMap) {

        List<Map<String, Object>> incomeList = orderMapper.findIncomeDataPerDay(queryDataMap);

        //获取总收益
        Double incomeTotalTemp = 0.0;
        for (Map<String, Object> map : incomeList) {
            Double income = (Double) map.get("income");
            incomeTotalTemp += income;
        }
        //将总收益转成字符串
        String totalIncome = String.valueOf(incomeTotalTemp);

        //总分成
        //查询代理商的分成比例
        String agentId = (String) queryDataMap.get("agentId");
        Double sharging_ratio = Double.valueOf(agentMapper.findShargingRatioByAgent(agentId));
        // Double sharging_ratio = 90/100.0;
        //保留一位小数,直接截取,没有四舍五入
        Double totalSharingTemp = new BigDecimal(incomeTotalTemp * sharging_ratio).setScale(1, RoundingMode.DOWN).doubleValue();
        String totalSharing = String.valueOf(totalSharingTemp);

        //计算每天的分成
        incomeList.forEach(incomeMap -> {
            //每一天的收益
            Double sharingPerDay = (Double) incomeMap.get("income") * sharging_ratio;
            incomeMap.put("income", sharingPerDay);
        });

        //租借收益 ???
        String rentIncome = "0";

        //99收益 ???
        String _99Income = "0";

        //子代理商收益
        //先查询该代理商的id,通过该代理商的id去查询子代理商的id
        String id = agentMapper.findIDByIdNum((String) queryDataMap.get("agentId"));
        String subID = agentMapper.findSubAgentIdByParentId(id);
        if (id.equals(subID)) {
            log.info("子代理商id和父代理商id一样了,请查看后台系统哪里出了问题...");
            subID = null;
        }

        //System.out.println(subID);
        String subAgentIncome = "0";
        if (subID != null) {
            //根据子代理商id查询其该月的收益
            Map<String, Object> subQueryDataMap = new HashMap<>();
            subQueryDataMap.put("Id", subID);
            subQueryDataMap.put("type", queryDataMap.get("type"));
            subQueryDataMap.put("dateStart", queryDataMap.get("dateStart"));
            subQueryDataMap.put("dateEnd", queryDataMap.get("dateEnd"));
            subAgentIncome = String.valueOf(orderMapper.findAgentIncomeById(subQueryDataMap));
        }

        //封装数据
        IncomeData incomeData = new IncomeData();
        incomeData.setType((String) queryDataMap.get("type"));
        incomeData.setTotalSharing(totalSharing);
        incomeData.setTotalIncome(totalIncome);
        incomeData.setRentIncome(rentIncome);
        incomeData.set_99Income(_99Income);
        incomeData.setSubAgentIncome(subAgentIncome);
        incomeData.setDaySharing(incomeList);

        return incomeData;
    }

    /**
     * 通过agentId,dateRange,type,searchData查询代理商指定搜索内容,指定日期的订单记录
     *
     * @param queryDataMap
     * @return
     */
    @Override
    public List<OrderRecord> findOrderRecord(Map<String, Object> queryDataMap) {
        List<OrderRecord> orderRecordList = orderMapper.findOrderRecord(queryDataMap);

        return orderRecordList;
    }

    /**
     * 通过skey查询用户是否有未完成支付的订单
     * @param skey
     * @return
     */
    @Override
    public Order findOrderUnpaid(String skey) {
        Order order = orderMapper.findOrderUnpaid(skey);
        return order;
    }

    /**
     * 通过skey查询用户是否有正在进行的订单
     * @param skey
     * @return
     */
    @Override
    public Order findOrderDoing(String skey) {
        Order order = orderMapper.findOrderDoing(skey);
        return order;
    }
}
