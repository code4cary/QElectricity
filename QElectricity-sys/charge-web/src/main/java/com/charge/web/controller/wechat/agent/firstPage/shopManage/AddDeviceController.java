package com.charge.web.controller.wechat.agent.firstPage.shopManage;


import com.charge.entity.model.CommonOutputDO;
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
 * Created by vincent on 17/09/2018.
 */

@Slf4j
@RestController
@RequestMapping("wechat/agent/firstPage/shopManage/addDevice")
public class AddDeviceController extends BaseController{

    @Autowired
    private ChargingBoxService chargingBoxService;

    @RequestMapping
    public CommonOutputDO<Object> xxxx(@RequestBody(required = true) Map<String, String> queryData) {
        if (!validateParam(queryData)) {
            return returnFailed(null, "参属为空异常");
        }
        log.info("代理商商户管理业之分配充电箱...");

        //分配充电箱
        Boolean isAddSuccess = chargingBoxService.AddDevice(queryData);

        log.info("over");
        return isAddSuccess?returnSuccess(null):
                returnFailed(null,"分配失败");
    }


    private boolean validateParam(Map<String, String> queryData) {
        if (StringUtils.isEmpty(queryData.get("agentID")) ||
                StringUtils.isEmpty(queryData.get("shopID")) ||
                StringUtils.isEmpty(queryData.get("deviceNO"))) {
            return false;
        }
        return true;
    }
}
