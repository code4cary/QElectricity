package com.charge.service.biz.wechat.agent;

import com.charge.entity.po.back.wechat.agent.loginAgent.FirstPage;
import com.charge.entity.po.back.wechat.agent.todayIncomeDetail.TodayIncome;
import com.charge.entity.po.wechat.agent.Agent;
import com.charge.service.biz.base.BaseService;

import java.util.Date;
import java.util.List;

/**
 * Created by vincent on 23/09/2018.
 */
public interface AgentService extends BaseService<Agent,Integer> {
    String findPwdByName(String username);

    FirstPage findAgentInfoByIdNum(String agentId ,Date dateStart,Date dateEnd);

    String findAgentIdNoByName(String username);

    List<TodayIncome> findTodayIncomeDetail(String agentId, Date dateStart,Date dateEnd);
}
