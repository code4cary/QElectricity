package com.charge.web.controller.wechat.agent.firstPage;

import com.charge.entity.model.CommonOutputDO;
import com.charge.entity.po.back.wechat.agent.EarlyWarning;
import com.charge.service.biz.wechat.agent.AgentService;
import com.charge.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 15/09/2018.
 */
@Slf4j
@RestController
@RequestMapping("wechat/agent/firstPage/earlyWarning")
public class EarlyWarningController extends BaseController {

    @Autowired
    private AgentService agentService;

    @RequestMapping
    public CommonOutputDO<Object> getEarlyWarningInfo(Map<String, String> queryData) {
        if (!validateParam(queryData)) {
            return returnFailed(null, "参属为空异常");
        }
        log.info("查询代理商预警详情页信息...");

        //获取agentId
        String agentId = queryData.get("agentID");

        //获取searchData
        String searchData = queryData.get("searchData");

        //封装查询信息
        //封装查询条件到map
        Map<String, String> queryDataMap = new HashMap<>();
        queryDataMap.put("agentId", agentId);
        queryDataMap.put("searchData", searchData);

        //查询预警详情页信息
        List<EarlyWarning> earlyWarningList = agentService.findEarlyWarning(queryDataMap);

        log.info("over");
        return returnSuccess(earlyWarningList);
    }


    private boolean validateParam(Map<String, String> queryData) {
        if (StringUtils.isEmpty(queryData.get("agentId"))) {
            return false;
        }
        return true;
    }
}
