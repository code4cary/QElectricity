package com.charge.web.controller.wechat.agent.firstPage;

import com.alibaba.fastjson.JSON;
import com.charge.ChargeApplication;
import com.charge.entity.model.CommonOutputDO;
import com.charge.entity.po.back.wechat.agent.IncomeData;
import com.charge.service.biz.wechat.user.OrderService;
import com.charge.web.controller.base.BaseController;
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
 * Created by vincent on 26/09/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChargeApplication.class)
public class IncomeDataControllerTest extends BaseController {
    @Autowired
    private OrderService orderService;

    //
    public static void main(String... args) {

    }

    @Test
    public void testGetIncomeDataInfo() throws Exception {

        //获取agentId
        String agentId = "456";

        //获取类型:充电箱还是充电线:1代表充电线,0代表充电宝
        String type = "1";

        //获取date,字符串形式,请求参数为年月两个数据
        String format = "yyyy-MM";//日期格式
        Date dateStart = DateUtil.getSpecificDateStartTime(DateUtil.getSpecificDateFormat("2018-09", format));//获取指定日期的起始时间
        Date dateEnd = DateUtil.getSpecificDateEndTime(DateUtil.getSpecificDateFormat("2018-10", format));//获取指定日期的结束时间

        System.out.println(dateStart);

        Map<String, Object> queryDataMap = new HashMap<>();
        queryDataMap.put("agentId", agentId);
        queryDataMap.put("type", type);
        queryDataMap.put("dateStart", dateStart);
        queryDataMap.put("dateEnd", dateEnd);

        //通过agentId查询数据库当前月的收益数据详情
        IncomeData incomeData = orderService.findIncomeDataPerDay(queryDataMap);

        CommonOutputDO commonOutputDO = returnSuccess(incomeData);
        Object json = JSON.toJSON(commonOutputDO);
        System.out.println(json);
        System.out.println("--------------------------------------------");
        Object json2 = JSON.toJSON(incomeData);
        System.out.println(json2);
    }
}