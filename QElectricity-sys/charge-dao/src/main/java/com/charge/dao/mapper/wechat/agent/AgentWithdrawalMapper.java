package com.charge.dao.mapper.wechat.agent;

import com.charge.dao.mapper.base.BaseMapper;
import com.charge.entity.po.wechat.agent.AgentWithdrawal;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 由MyBatis Generator工具自动生成，请不要手动修改
 */
public interface AgentWithdrawalMapper extends BaseMapper<AgentWithdrawal, Integer> {

    Double findAgentWithdrawalAmountDone(@Param("queryDataMap")Map<String, String> queryData);
}