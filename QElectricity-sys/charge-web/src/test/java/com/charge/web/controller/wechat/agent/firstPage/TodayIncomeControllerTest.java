package com.charge.web.controller.wechat.agent.firstPage;

import com.alibaba.fastjson.JSON;
import com.charge.ChargeApplication;
import com.charge.entity.po.back.wechat.agent.TodayIncome;
import com.charge.service.biz.wechat.agent.AgentService;
import com.charge.web.utils.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by vincent on 25/09/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChargeApplication.class)
public class TodayIncomeControllerTest {

    @Autowired
    private AgentService agentService;

    //
    public static void main(String... args) {

    }

    @Test
    public void testGetTodayIncomeDetail() throws Exception {

        String agentId = "123";

        String dateStr = "2018-09-24";

        //将字符串日期变为Date类型
        String format = "yyyy-MM-dd";//日期格式
        Date date = DateUtil.getSpecificDateFormat(dateStr,format);

        //获得该日的开始时间
        Date dateStart = DateUtil.getSpecificDateStartTime(date);
        //获得该日的结束时间
        Date dateEnd = DateUtil.getSpecificDateEndTime(date);

        //去数据库查询
        List<TodayIncome> todayIncomeDetail = agentService.findTodayIncomeDetail(agentId, dateStart, dateEnd);

        Object json = JSON.toJSON(todayIncomeDetail);
        System.out.println(json);
    }
}