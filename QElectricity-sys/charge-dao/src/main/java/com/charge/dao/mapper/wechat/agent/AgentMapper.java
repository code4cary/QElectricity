package com.charge.dao.mapper.wechat.agent;

import com.charge.dao.mapper.base.BaseMapper;
import com.charge.entity.po.wechat.agent.Agent;
import org.apache.ibatis.annotations.Param;

/**
 * 由MyBatis Generator工具自动生成，请不要手动修改
 */
public interface AgentMapper extends BaseMapper<Agent, Integer> {
    String findAgentIdNoByName(@Param("name") String agentName);

    String findPwdByName(@Param("name") String agentName);

    Agent findTotalOrderAndShare(@Param("id_num") String id_num);

    String findShargingRatioByAgent(@Param("id_num") String id_num);

    String findSubAgentIdByParentId(@Param("p_id_num") String p_id_num);


}