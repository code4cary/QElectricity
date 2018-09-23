package com.charge.service.biz.wechat.agent.impl;

import com.charge.common.back.wechat.agent.loginAgent.LoginAgentBack;
import com.charge.dao.mapper.wechat.agent.AgentMapper;
import com.charge.entity.po.wechat.agent.Agent;
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

    @Override
    public void initBaseMapper() {
        setBaseMapper(agentMapper);
    }


    /**
     * 根据用户名查询代理商首页相关信息
     * @param username
     * @return
     */
    @Override
    public List<LoginAgentBack> findAgentInfoByName(String username) {

        LoginAgentBack loginAgentBack = new LoginAgentBack();

        //1:查询代理商订单数据
        Map<String,String> orderData = new HashMap<>();
        //今日订单


        //今日收益

        //订单总数

        //总分成


        //2:查询用代理商充电箱状态
        Map<String,String> chargingBoxStatus = new HashMap<>();
        //充电箱总数量

        //充电箱离线数量

        //3:查询代理商充电箱预警数量
        String earlyWarningNum;

        return null;
    }

    /**
     * 根据用户名查询用户密码
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
     * @param username
     * @return
     */
    @Override
    public String findAgentIdNoByName(String username) {

        String agentId = agentMapper.findAgentIdNoByName(username);

        return agentId;
    }

}
