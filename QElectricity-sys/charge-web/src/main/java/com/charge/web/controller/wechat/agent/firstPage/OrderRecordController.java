package com.charge.web.controller.wechat.agent.firstPage;

import com.charge.entity.model.CommonOutputDO;
import com.charge.entity.po.back.wechat.agent.OrderRecord;
import com.charge.service.biz.wechat.agent.OrderService;
import com.charge.web.controller.base.BaseController;
import com.charge.web.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 15/09/2018.
 */
@Slf4j
@RestController
@RequestMapping("wechat/agent/firstPage/orderRecord")
public class OrderRecordController extends BaseController {

    @Autowired
    private OrderService orderService;

    @RequestMapping
    public CommonOutputDO<Object> getOrderRecordInfo(Map<String, Object> queryData) throws ParseException {
        if (!validateParam(queryData)) {
            return returnFailed(null, "参属为空异常");
        }
        log.info("查询代理商订单记录详情...");


        //获取agentId
        String agentId = (String) queryData.get("agentID");

        //获取类型:充电箱还是充电线:1代表充电线,0代表充电宝
        String type = (String) queryData.get("type");

        //获取searchData 搜索范围：userPhone(用户手机号),shopName(商户名字),orderStatus(订单状态)
        String searchData = (String) queryData.get("searchData");

        //如{"dateRange":[{"startTime":"2018-05-02","endTime":"2018-09-15"}]},需要年月日三个数据
        Map<String,String> dateRange = (Map<String, String>) queryData.get("dateRange");
        String startTime = dateRange.get("startTime");
        String endTime = dateRange.get("endTime");
        //获取字符串日期的起始时间和结束时间
        String format = "yyyy-MM-dd";//日期格式
        Date dateStart = DateUtil.getSpecificDateStartTime(DateUtil.getSpecificDateFormat(startTime,format));//获取指定日期的起始时间
        Date dateEnd = DateUtil.getSpecificDateEndTime(DateUtil.getSpecificDateFormat(endTime,format));//获取指定日期的结束时间

        //封装查询条件到map
        Map<String,Object> queryDataMap = new HashMap<>();
        queryDataMap.put("agentId",agentId);
        queryDataMap.put("type",type);
        queryDataMap.put("searchData",searchData);
        queryDataMap.put("startTime",dateStart);
        queryDataMap.put("endTime",dateEnd);


        //查询代理商订单记录详情详情页信息
        List<OrderRecord> OrderRecordList = orderService.findOrderRecord(queryDataMap);

        log.info("over");
        return returnSuccess(OrderRecordList);
    }


    private boolean validateParam(Map<String, Object> queryData) {
        if (StringUtils.isEmpty(queryData.get("agentId")) ||
                StringUtils.isEmpty(queryData.get("searchData")) ||
                StringUtils.isEmpty(queryData.get("type")) ||
                StringUtils.isEmpty(queryData.get("dateRange")) ||
                StringUtils.isEmpty(((Map<String,String>)queryData.get("dateRange")).get("dateRange")) ||
                StringUtils.isEmpty(((Map<String,String>)queryData.get("dateRange")).get("endTime"))) {
            return false;
        }
        return true;
    }
}
