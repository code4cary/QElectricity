package com.charge.web.controller.wechat.agent.firstPage;

import com.alibaba.fastjson.JSON;
import com.charge.ChargeApplication;
import com.charge.entity.model.CommonOutputDO;
import com.charge.entity.po.back.wechat.agent.OrderRecord;
import com.charge.service.biz.wechat.agent.OrderService;
import com.charge.web.controller.base.BaseController;
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
 * Created by vincent on 26/09/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChargeApplication.class)
public class OrderRecordControllerTest extends BaseController {
    //
    public static void main(String... args) {

    }
    @Autowired
    private OrderService orderService;
    @Test
    public void testGetOrderRecordInfo() throws Exception {


        //获取字符串日期的起始时间和结束时间
        String format = "yyyy-MM-dd";//日期格式
        String startTime = "2018-09-19 ";
        String endTime = "2018-09-25";
        Date dateStart = DateUtil.getSpecificDateStartTime(DateUtil.getSpecificDateFormat(startTime,format));//获取指定日期的起始时间
        Date dateEnd = DateUtil.getSpecificDateEndTime(DateUtil.getSpecificDateFormat(endTime,format));//获取指定日期的结束时间

        //封装查询条件到map
        Map<String,Object> queryDataMap = new HashMap<>();
        queryDataMap.put("agentId","123");
        queryDataMap.put("type","1");
        queryDataMap.put("startTime",dateStart);
        queryDataMap.put("endTime",dateEnd);


        //查询代理商订单记录详情详情页信息
        List<OrderRecord> OrderRecordList = orderService.findOrderRecord(queryDataMap);


        CommonOutputDO commonOutputDO = returnSuccess(OrderRecordList);
        Object json = JSON.toJSON(commonOutputDO);
        System.out.println(json);
    }
}