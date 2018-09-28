package com.charge.web.controller.wechat.agent.myAccount;

/**
 * Created by vincent on 17/09/2018.
 */

import com.charge.entity.model.CommonOutputDO;
import com.charge.entity.po.wechat.agent.MyAccount;
import com.charge.service.biz.wechat.agent.AgentService;
import com.charge.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("wechat/agent/myAccount")
public class MyAccountController extends BaseController {

    @Autowired
    private AgentService agentService;

    @RequestMapping
    public CommonOutputDO<Object> getMyAccountInfo(@RequestBody(required = true) Map<String, String> queryData) {
        if (!validateParam(queryData)) {
            return returnFailed(null, "参属为空异常");
        }
        log.info("查询代理商我的账户信息...");

        MyAccount myAccountList = agentService.getMyAccountInfo(queryData);

        log.info("over");
        return returnSuccess(myAccountList);
    }


    private boolean validateParam(Map<String, String> queryData) {
        if (StringUtils.isEmpty(queryData.get("agentID"))) {
            return false;
        }
        return true;
    }
}
