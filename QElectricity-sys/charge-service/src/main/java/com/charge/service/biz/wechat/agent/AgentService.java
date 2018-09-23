package com.charge.service.biz.wechat.agent;

import com.charge.common.back.wechat.agent.loginAgent.LoginAgentBack;
import com.charge.entity.po.wechat.agent.Agent;
import com.charge.service.biz.base.BaseService;

import java.util.List;

/**
 * Created by vincent on 23/09/2018.
 */
public interface AgentService extends BaseService<Agent,Integer> {
    String findPwdByName(String username);

    List<LoginAgentBack> findAgentInfoByName(String username);

    String findAgentIdNoByName(String username);
}
