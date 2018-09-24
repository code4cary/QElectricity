package com.charge.web.controller.wechat.agent.firstPage;

import com.charge.entity.model.CommonOutputDO;
import com.charge.entity.po.back.wechat.agent.todayIncomeDetail.TodayIncome;
import com.charge.service.biz.wechat.agent.AgentService;
import com.charge.web.controller.base.BaseController;
import com.charge.web.utils.DateUtil;
import com.charge.web.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 15/09/2018.
 */
@Slf4j
@RestController
@RequestMapping("wechat/agent/firstPage/todayIncome")
public class TodayIncomeController extends BaseController{


    @Autowired
    private AgentService agentService;

    @RequestMapping
    public CommonOutputDO<Object> getTodayIncomeDetail(@RequestBody Map<String,String> queryData)
            throws ParseException {
        if (!validateParam(queryData)) {
            return returnFailed(null, "参属为空异常");
        }
        log.info("查询代理商今日订单详情...");

        //获取agenId
        String agentId = queryData.get("agentID");

        //获取date,字符串形式
        String dateStr = queryData.get("date");

        //将字符串日期变为Date类型
        Date date = TimeUtil.getSpecificDateFormat(dateStr);

        //获得该日的开始时间
        Date dateStart = DateUtil.getSpecificDateStartTime(date);
        //获得该日的结束时间
        Date dateEnd = DateUtil.getSpecificDateEndTime(date);

        //去数据库查询
        List<TodayIncome> todayIncomeDetail = agentService.findTodayIncomeDetail(agentId,dateStart,dateEnd);

        log.info("over");;
        return returnSuccess(todayIncomeDetail);
    }


    private boolean validateParam(Map<String, String> queryData) {
        if (StringUtils.isEmpty(queryData.get("agentID")) || StringUtils.isEmpty(queryData.get("date"))) {
            return false;
        }
        return true;
    }
}
