package com.charge.web.controller.wechat.agent.firstPage;

import com.alibaba.fastjson.JSON;
import com.charge.ChargeApplication;
import com.charge.entity.po.back.wechat.agent.FirstPage;
import com.charge.service.biz.wechat.agent.AgentService;
import com.charge.web.utils.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by vincent on 24/09/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChargeApplication.class)
public class FirstPageAgentControllerTest {
    @Autowired
    private AgentService agentService;

    //
    public static void main(String... args) {

    }

    @Test
    public void testGetFirstPageAgentInfo() throws Exception {
        String agentId = "123";

        //获得该日的开始时间
        Date dateStart = DateUtil.getTodayStartTime();
        //获得该日的结束时间
        Date dateEnd = DateUtil.getTodayEndTime();
        FirstPage agentInfo = agentService.findAgentInfoByIdNum(agentId, dateStart, dateEnd);
        Object json = JSON.toJSON(agentInfo);
        System.out.println(json);

    }
}