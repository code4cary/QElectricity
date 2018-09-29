package com.charge.web.controller.wechat.agent.firstPage;

import com.alibaba.fastjson.JSON;
import com.charge.ChargeApplication;
import com.charge.entity.po.back.wechat.agent.OrderDataDetail;
import com.charge.service.biz.wechat.user.OrderService;
import com.charge.web.utils.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vincent on 25/09/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChargeApplication.class)
public class OrderDataDetailControllerTest {
    @Autowired
    private OrderService orderService;

    //
    public static void main(String... args) {

    }

    @Test
    public void testGetOrderDataInfo() throws Exception {

        //获取agentId
        String agentId = "123";

        //获取类型:充电箱还是充电线:1代表充电线,0代表充电宝
        String type = "1";

        //获取date,字符串形式,请求参数为年月两个数据
        String format = "yyyy-MM";//日期格式
        Date specificDateFormat = DateUtil.getSpecificDateFormat("2018-09", format);
        String firstDay = DateUtil.getSpecificMonthFirstDayLastday(specificDateFormat).get("firstDay");
        String lastDay = DateUtil.getSpecificMonthFirstDayLastday(specificDateFormat).get("lastDay");
        Date dateStart = DateUtil.getSpecificDateFormat(firstDay, "yyyy-MM-dd HH:mm:ss");
        Date dateEnd = DateUtil.getSpecificDateFormat(lastDay, "yyyy-MM-dd HH:mm:ss");

        Map<String,Object> queryDataMap = new HashMap<>();
        queryDataMap.put("agentId",agentId);
        queryDataMap.put("type",type);
        queryDataMap.put("dateStart",dateStart);
        queryDataMap.put("dateEnd",dateEnd);

        //通过agentId查询数据库当前月的订单数据详情
        OrderDataDetail orderDataDetail= orderService.findOrderDataNumPerDay(queryDataMap);

        Object json = JSON.toJSON(orderDataDetail);
        System.out.println(json);
    }
}