package com.charge.web.controller.wechat.agent.firstPage.shopManage;

import com.charge.entity.model.CommonOutputDO;
import com.charge.entity.po.device.PriceTypeCB;
import com.charge.service.biz.wechat.agent.ChargingBoxService;
import com.charge.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by vincent on 01/10/2018.
 */
@Slf4j
@RestController
@RequestMapping("wechat/agent/firstPage/shopManage/modifyPrice")
public class ModifyPriceController extends BaseController {

    @Autowired
    private ChargingBoxService chargingBoxService;

    @RequestMapping
    public CommonOutputDO<Object> getPriceInfo(@RequestBody(required = true) Map<String, String> queryData) {
        if (!validateParam(queryData)) {
            return returnFailed(null, "参属中存在空参异常");
        }
        log.info("代理商修改充电箱大页面接口...");

        PriceTypeCB priceTypeCB = chargingBoxService.findPriceInfo(queryData);

        log.info("over...");
        return priceTypeCB == null?returnFailed(null,"没用对应的价格策略表"):returnSuccess(priceTypeCB);
    }

    private boolean validateParam(Map<String, String> queryData) {
        if (StringUtils.isEmpty(queryData.get("agentID")) ||
                StringUtils.isEmpty(queryData.get("shopID"))) {
            return false;
        }
        return true;
    }

}
