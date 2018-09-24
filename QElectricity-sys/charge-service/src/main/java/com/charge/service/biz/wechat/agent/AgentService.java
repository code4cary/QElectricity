package com.charge.service.biz.wechat.agent;

import com.charge.entity.po.back.wechat.loginAgent.LoginAgentBack;
import com.charge.entity.po.wechat.agent.Agent;
import com.charge.service.biz.base.BaseService;

/**
 * Created by vincent on 23/09/2018.
 */
public interface AgentService extends BaseService<Agent,Integer> {
    String findPwdByName(String username);

    LoginAgentBack findAgentInfoByIdNum(String agentId);

    String findAgentIdNoByName(String username);
}
