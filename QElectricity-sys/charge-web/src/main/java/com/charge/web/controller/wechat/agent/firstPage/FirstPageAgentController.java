package com.charge.web.controller.wechat.agent.firstPage;


import com.charge.entity.model.CommonOutputDO;
import com.charge.entity.po.back.wechat.agent.FirstPage;
import com.charge.service.biz.wechat.agent.AgentService;
import com.charge.web.controller.base.BaseController;
import com.charge.web.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;


/**
 * Created by vincent on 23/09/2018.
 */
@Slf4j
@RestController
@RequestMapping("wechat/agent/")
public class FirstPageAgentController extends BaseController {

    @Autowired
    private AgentService agentService;

    @RequestMapping(value = "firstPage")
    @ResponseBody
    public CommonOutputDO<Object> getFirstPageAgentInfo(@RequestBody Map<String, String> agentIdMap) {
        if (!validateParam(agentIdMap)) {
            return returnFailed(null, "参属为空异常");
        }
        log.info("查询代理商首页需要显示的信息...");
        //获取agenId
        String agenId = agentIdMap.get("agentID");

        //通过agentId查询代理商相关信息
        //获得今日的开始时间
        Date dateStart = DateUtil.getTodayStartTime();
        //获得今日的结束时间
        Date dateEnd = DateUtil.getTodayEndTime();
        FirstPage agentInfo = agentService.findAgentInfoByIdNum(agenId, dateStart, dateEnd);
        log.info("over");
        return returnSuccess(agentInfo);
    }


    private boolean validateParam(Map<String, String> agentIdMap) {
        if (StringUtils.isEmpty(agentIdMap.get("agentID"))) {
            return false;
        }
        return true;
    }
}
