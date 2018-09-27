package com.charge.service.biz.wechat.agent;

        import com.charge.entity.model.ModifyPriceTypeCBDO;
        import com.charge.entity.po.back.wechat.agent.EarlyWarning;
        import com.charge.entity.po.back.wechat.agent.FirstPage;
        import com.charge.entity.po.back.wechat.agent.TodayIncome;
        import com.charge.entity.po.wechat.agent.Agent;
        import com.charge.entity.po.wechat.agent.MyAccount;
        import com.charge.service.biz.base.BaseService;

        import java.util.Date;
        import java.util.List;
        import java.util.Map;

/**
 * Created by vincent on 23/09/2018.
 */
public interface AgentService extends BaseService<Agent,Integer> {
    String findPwdByName(String username);

    FirstPage findAgentInfoByIdNum(String agentId ,Date dateStart,Date dateEnd);

    String findAgentIdNoByName(String username);

    List<TodayIncome> findTodayIncomeDetail(String agentId, Date dateStart,Date dateEnd);

    List<EarlyWarning> findEarlyWarning(Map<String, String> queryDataMap);

    Boolean modifyPrice(ModifyPriceTypeCBDO modifyPriceDO);

    List<MyAccount> getMyAccountInfo(Map<String, String> queryData);
}
