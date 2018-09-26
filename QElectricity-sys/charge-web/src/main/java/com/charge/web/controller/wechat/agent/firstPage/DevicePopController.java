package com.charge.web.controller.wechat.agent.firstPage;

import com.charge.entity.model.CommonOutputDO;
import com.charge.entity.po.back.wechat.agent.DevicePop;
import com.charge.service.biz.wechat.agent.ChargingBoxService;
import com.charge.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vincent on 16/09/2018.
 */
@Slf4j
@RestController
@RequestMapping("wechat/agent/firstPage/devicePop")
public class DevicePopController extends BaseController {

    @Autowired
    private ChargingBoxService chargingBoxService;

    @RequestMapping
    public CommonOutputDO<Object> getDevicePopInfo(Map<String, String> queryData) {
        if (!validateParam(queryData)) {
            return returnFailed(null, "参属为空异常");
        }
        log.info("查询代理商设备弹出页信息...");

        //获取agentId
        String agentId = queryData.get("agentID");

        //获取设备编号
        String deviceNO = queryData.get("deviceNO");

        //封装查询条件到map
        Map<String,String> queryDataMap = new HashMap<>();
        queryDataMap.put("agentId",agentId);
        queryDataMap.put("deviceNO",deviceNO);

        //查询代理商设备管理信息
        DevicePop devicePopInfo = chargingBoxService.findDevicePopInfo(queryDataMap);

        log.info("over");
        return returnSuccess(devicePopInfo);
    }


    private boolean validateParam(Map<String, String> queryData) {
        if (StringUtils.isEmpty(queryData.get("agentID")) ||
                StringUtils.isEmpty(queryData.get("deviceNO"))) {
            return false;
        }
        return true;
    }
}
