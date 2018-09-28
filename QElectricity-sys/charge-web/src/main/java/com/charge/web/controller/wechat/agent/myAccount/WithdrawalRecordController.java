package com.charge.web.controller.wechat.agent.myAccount;

import com.charge.entity.model.CommonOutputDO;
import com.charge.entity.po.back.wechat.agent.WithdrawalRecord;
import com.charge.service.biz.wechat.agent.AgentService;
import com.charge.web.controller.base.BaseController;
import com.charge.web.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 17/09/2018.
 */
@Slf4j
@RestController
@RequestMapping("wechat/agent/withdrawalRecord")
public class WithdrawalRecordController extends BaseController {

    @Autowired
    private AgentService agentService;

    @RequestMapping
    public CommonOutputDO<Object> getWithdrawalRecord(@RequestBody(required = true) Map<String, String> queryData) throws ParseException {
        if (!validateParam(queryData)) {
            return returnFailed(null, "参属为空异常");
        }
        log.info("查询代理商提现页信息...");

        String dateStr = queryData.get("date");

        //获取指定字符串日期的起始时间和结束时间
        String format = "yyyy-MM";//日期格式
        Map<String, Date> dateMap = DateUtil.getSpecificFormatStartAndEndTime(dateStr, format);
        Date dateStart = dateMap.get("dateStart");
        Date dateEnd = dateMap.get("dateEnd");

        //agentId
        String agentId = queryData.get("agentID");

        //封装请求参数
        Map<String,Object> queryDataMap = new HashMap<>();
        queryDataMap.put("agentId",agentId);
        queryDataMap.put("dateStart",dateStart);
        queryDataMap.put("dateEnd",dateEnd);

        //查询提现记录
        List<WithdrawalRecord> withdrawalRecordList = agentService.findAgentWithdrawalRecord(queryDataMap);

        log.info("over");
        return returnSuccess(withdrawalRecordList);
    }


    private boolean validateParam(Map<String, String> queryData) {
        if (StringUtils.isEmpty(queryData.get("agentID")) ||
                StringUtils.isEmpty(queryData.get("date"))) {
            return false;
        }
        return true;
    }
}
