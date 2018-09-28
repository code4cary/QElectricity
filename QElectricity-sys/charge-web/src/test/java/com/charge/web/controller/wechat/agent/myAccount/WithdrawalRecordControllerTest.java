package com.charge.web.controller.wechat.agent.myAccount;

import com.alibaba.fastjson.JSON;
import com.charge.ChargeApplication;
import com.charge.entity.po.back.wechat.agent.WithdrawalRecord;
import com.charge.service.biz.wechat.agent.AgentService;
import com.charge.web.utils.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 28/09/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChargeApplication.class)
public class WithdrawalRecordControllerTest {

    @Autowired
    private AgentService agentService;
    //
    public static void main(String... args) {

    }

    @Test
    public void testGetWithdrawalRecord() throws Exception {


        //获取指定字符串日期的起始时间和结束时间
        String format = "yyyy-MM";//日期格式
        Map<String, Date> dateMap = DateUtil.getSpecificFormatStartAndEndTime("2018-09", format);
        Date dateStart = dateMap.get("dateStart");
        Date dateEnd = dateMap.get("dateEnd");



        //封装请求参数
        Map<String,Object> queryDataMap = new HashMap<>();
        queryDataMap.put("agentId","123");
        queryDataMap.put("dateStart",dateStart);
        queryDataMap.put("dateEnd",dateEnd);

        //查询提现记录
        List<WithdrawalRecord> withdrawalRecordList = agentService.findAgentWithdrawalRecord(queryDataMap);

        Object json = JSON.toJSON(withdrawalRecordList);
        System.out.println(json);

    }
}