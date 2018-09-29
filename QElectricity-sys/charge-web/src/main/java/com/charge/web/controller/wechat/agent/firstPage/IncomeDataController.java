package com.charge.web.controller.wechat.agent.firstPage;

import com.charge.entity.model.CommonOutputDO;
import com.charge.entity.po.back.wechat.agent.IncomeData;
import com.charge.service.biz.wechat.user.OrderService;
import com.charge.web.controller.base.BaseController;
import com.charge.web.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vincent on 15/09/2018.
 */


/**
 * 收益数据页
 */
@Slf4j
@RestController
@RequestMapping("wechat/agent/firstPage/incomeData")
public class IncomeDataController extends BaseController {

    @Autowired
    private OrderService orderService;

    @RequestMapping
    public CommonOutputDO<Object> getIncomeDataInfo(@RequestBody(required = true) Map<String, String> queryData) throws ParseException {
        if (!validateParam(queryData)) {
            return returnFailed(null, "参属为空异常");
        }
        log.info("查询代理商收益数据详情信息...");
        //获取agentId
        String agentId = queryData.get("agentID");

        //获取类型:充电箱还是充电线:1代表充电线,0代表充电宝
        String type = queryData.get("type");

        //获取date,字符串形式,请求参数为年月两个数据
        String dateStr = queryData.get("date");

        String format = "yyyy-MM";//日期格式
        //获取指定字符串日期的起始时间和结束时间
        Map<String,Date> dateMap = DateUtil.getSpecificFormatStartAndEndTime(dateStr, format);
        Date dateStart = dateMap.get("dateStart");
        Date dateEnd = dateMap.get("dateEnd");

//        Date specificDateFormat = DateUtil.getSpecificDateFormat(dateStr, format);
//        String firstDay = DateUtil.getSpecificMonthFirstDayLastday(specificDateFormat).get("firstDay");
//        String lastDay = DateUtil.getSpecificMonthFirstDayLastday(specificDateFormat).get("lastDay");
//        Date dateStart = DateUtil.getSpecificDateFormat(firstDay, "yyyy-MM-dd HH:mm:ss");
//        Date dateEnd = DateUtil.getSpecificDateFormat(lastDay, "yyyy-MM-dd HH:mm:ss");

        //封装查询条件到map
        Map<String,Object> queryDataMap = new HashMap<>();
        queryDataMap.put("agentId",agentId);
        queryDataMap.put("type",type);
        queryDataMap.put("dateStart",dateStart);
        queryDataMap.put("dateEnd",dateEnd);

        //通过agentId查询数据库当前月的收益数据详情
        IncomeData incomeData = orderService.findIncomeDataPerDay(queryDataMap);

        log.info("over");

        return returnSuccess(incomeData);
    }


    private boolean validateParam(Map<String, String> queryData) {
        if (StringUtils.isEmpty(queryData.get("agentID")) ||
                StringUtils.isEmpty(queryData.get("type")) ||
                StringUtils.isEmpty(queryData.get("date"))) {
            return false;
        }
        return true;
    }
}
