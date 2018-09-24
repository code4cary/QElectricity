package com.charge.service.biz.wechat.agent.impl;

import com.charge.dao.mapper.wechat.agent.AgentMapper;
import com.charge.dao.mapper.wechat.user.OrderMapper;
import com.charge.entity.po.back.wechat.loginAgent.LoginAgentBack;
import com.charge.entity.po.wechat.agent.Agent;
import com.charge.entity.po.wechat.user.Order;
import com.charge.service.biz.base.impl.BaseServiceImpl;
import com.charge.service.biz.wechat.agent.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 23/09/2018.
 */
@Service
public class AgentServiceImpl extends BaseServiceImpl<Agent, Integer> implements AgentService {
    @Autowired
    private AgentMapper agentMapper;

    @Autowired
    private OrderMapper orderMapper;



    @Override
    public void initBaseMapper() {
        setBaseMapper(agentMapper);
    }


    /**
     * 根据代理商id(此id为代理商id_num)查询代理商首页相关信息
     *
     * @param agentId
     * @return
     */
    @Override
    public LoginAgentBack findAgentInfoByIdNum(String agentId) {

        List<Order> todayOrdersCountAndIncome = orderMapper.findTodayOrdersByAgentId(agentId);

        int todayIncome = 0;
        int todayOrdersNum = 0;
        for (Order  order: todayOrdersCountAndIncome) {
            //计算今日订单收益,以及今日订单数
            //如果订单支付完成,才能算给代理商
            if (order.getPayStatus().equals("1")) {
                todayIncome += Integer.valueOf(order.getPayAmount());
                todayOrdersNum++;
            }
        }

        //总分成,查询t_agent表获取订单总数和总分成
        List<String> totalOrderAndIncome = agentMapper.findTotalOrderAndIncome(agentId);


        //2:查询用代理商充电箱状态
        Map<String, String> chargingBoxStatus = new HashMap<>();
        //充电箱总数量

        //充电箱离线数量

        //3:查询代理商充电箱预警数量
        String earlyWarningNum;

        return null;
    }

    /**
     * 根据用户名查询用户密码
     *
     * @param agentName
     * @return
     */
    @Override
    public String findPwdByName(String agentName) {
        String tmpPwd = agentMapper.findPwdByName(agentName);
        return tmpPwd;
    }


    /**
     * 根据用户名查询该用户的ID_NUMBER
     *
     * @param username
     * @return
     */
    @Override
    public String findAgentIdNoByName(String username) {

        String agentId = agentMapper.findAgentIdNoByName(username);

        return agentId;
    }

}
