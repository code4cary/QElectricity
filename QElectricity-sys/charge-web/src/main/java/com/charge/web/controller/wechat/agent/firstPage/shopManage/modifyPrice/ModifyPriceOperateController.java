package com.charge.web.controller.wechat.agent.firstPage.shopManage.modifyPrice;

import com.charge.entity.model.CommonOutputDO;
import com.charge.entity.model.ModifyPriceTypeCBDO;
import com.charge.service.biz.wechat.agent.AgentService;
import com.charge.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by vincent on 17/09/2018.
 */
@Slf4j
@RestController
@PropertySource(value = "classpath:price.properties")
@RequestMapping("wechat/agent/firstPage/shopManage/modifyPrice/operate")
public class ModifyPriceOperateController extends BaseController {

    @Autowired
    private AgentService agentService;

    @Value("${freeTimeThreshold}")
    private String freeTimeThreshold;

    @Value("${pricePerHourThreshold}")
    private String pricePerHourThreshold;

    @Value("${topPricePerDayThreshold}")
    private String topPricePerDayThreshold;

    @RequestMapping
    public CommonOutputDO<Object> modifyPriceOperateInfo(@RequestBody(required = true) ModifyPriceTypeCBDO modifyPriceDO) {
        if (!validateParam(modifyPriceDO)) {
            return returnFailed(null, "参属中存在空参异常");
        }
        log.info("代理商修改充电箱价格策略...");

        //价格策略逻辑判断
        String freeTime = modifyPriceDO.getFreeTime();
        String pricePerHour = modifyPriceDO.getPricePerHour();
        String topPricePerDay = modifyPriceDO.getTopPricePerDay();

        if (Float.valueOf(freeTime) < 0 || Float.valueOf(freeTime) > Float.valueOf(freeTimeThreshold)) {
            return returnFailed(null, "免费时长设置的不合理");
        } else if (Float.valueOf(pricePerHour)<0 || Float.valueOf(pricePerHour)>Float.valueOf(pricePerHourThreshold)) {
            return returnFailed(null, "每小时价格设置的不合理");
        } else if(Float.valueOf(topPricePerDay)<0 || Float.valueOf(topPricePerDay)>Float.valueOf(topPricePerDayThreshold)) {
            return returnFailed(null, "每日封顶设置的不合理");
        }

        //修改价格策略
        Boolean isModifySuccess = agentService.modifyPrice(modifyPriceDO);

        log.info("over");
        return isModifySuccess ? returnSuccess(null) :
                returnFailed(null, "价格修改失败");
    }


    private boolean validateParam(ModifyPriceTypeCBDO modifyPriceDO) {
        if (StringUtils.isEmpty(modifyPriceDO.getAgentId()) ||
                StringUtils.isEmpty(modifyPriceDO.getShopId()) ||
                StringUtils.isEmpty(modifyPriceDO.getFreeTime()) ||
                StringUtils.isEmpty(modifyPriceDO.getPricePerHour()) ||
                StringUtils.isEmpty(modifyPriceDO.getTopPricePerDay())) {
            return false;
        }
        return true;
    }
}
