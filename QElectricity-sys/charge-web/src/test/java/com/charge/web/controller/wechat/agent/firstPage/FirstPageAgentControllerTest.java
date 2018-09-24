package com.charge.web.controller.wechat.agent.firstPage;

import com.charge.ChargeApplication;
import com.charge.entity.po.back.wechat.agent.loginAgent.FirstPage;
import com.charge.service.biz.wechat.agent.AgentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        String agenId = "123";
        //通过agentId查询代理商相关信息
        FirstPage agentInfo = agentService.findAgentInfoByIdNum(agenId);

    }
}