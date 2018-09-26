package com.charge.web.controller.wechat.agent.firstPage;

import com.alibaba.fastjson.JSON;
import com.charge.ChargeApplication;
import com.charge.entity.model.CommonOutputDO;
import com.charge.entity.po.back.wechat.agent.EarlyWarning;
import com.charge.service.biz.wechat.agent.AgentService;
import com.charge.web.controller.base.BaseController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 26/09/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChargeApplication.class)
public class EarlyWarningControllerTest extends BaseController {
    //
    public static void main(String... args) {

    }

    @Autowired
    private AgentService agentService;

    @Test
    public void testGetEarlyWarningInfo() throws Exception {



        //封装查询条件到map
        Map<String,String> queryDataMap = new HashMap<>();
        String agentId = "123";
        queryDataMap.put("agentId",agentId);
        String searchData = "1";
        queryDataMap.put("searchData",searchData);

        //查询预警详情页信息
        List<EarlyWarning> earlyWarningList = agentService.findEarlyWarning(queryDataMap);

        CommonOutputDO commonOutputDO = returnSuccess(earlyWarningList);
        Object json = JSON.toJSON(commonOutputDO);
        System.out.println(json);
    }
}