package com.charge.web.controller.wechat.agent.myAccount;

import com.alibaba.fastjson.JSON;
import com.charge.ChargeApplication;
import com.charge.entity.po.wechat.agent.MyAccount;
import com.charge.service.biz.wechat.agent.AgentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vincent on 28/09/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChargeApplication.class)
public class MyAccountControllerTest {
    //
    public static void main(String... args) {

    }

    @Autowired
    private AgentService agentService;


    @Test
    public void testGetMyAccountInfo() throws Exception {


        Map<String, String> queryData = new HashMap<>();
        queryData.put("agentID","123");

        MyAccount myAccountList = agentService.getMyAccountInfo(queryData);

        Object json = JSON.toJSON(myAccountList);
        System.out.println(json);

    }
}