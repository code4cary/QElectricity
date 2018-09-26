package com.charge.web.controller.wechat.agent.firstPage.deviceManage;

import com.charge.entity.model.CommonOutputDO;
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
 * Created by vincent on 15/09/2018.
 */
@Slf4j
@RestController
@RequestMapping("wechat/agent/firstPage/deviceManage/operate")
public class OperateController extends BaseController {

    @Autowired
    private ChargingBoxService chargingBoxService;

    @RequestMapping
    public CommonOutputDO<Object> getOperateDeviceManageInfo(Map<String, String> queryData) {
        if (!validateParam(queryData)) {
            return returnFailed(null, "参属为空异常");
        }
        log.info("进行代理商设备管理页之解绑/重启操作....");
        //获取agentId
        String agentId = queryData.get("agentID");

        //获取类型:充电箱还是充电线:1代表充电线,0代表充电宝
        String type = queryData.get("type");

        //操作类型:operateType:   0:unbind,1:reboot
        String operateType = queryData.get("operateType");

        //获取设备编号
        String deviceNO = queryData.get("deviceNO");

        //封装查询条件到map
        Map<String,String> queryDataMap = new HashMap<>();
        queryDataMap.put("agentId",agentId);
        queryDataMap.put("type",type);
        queryDataMap.put("operateType",operateType);
        queryDataMap.put("deviceNO",deviceNO);

        //进行代理商设备管理页之解绑/重启操作
        Boolean operateDeviceManage = chargingBoxService.opereteChargingBox(queryDataMap);

        log.info("over");
        return operateDeviceManage?returnSuccess(null):
                returnFailed(null,"操作失败");
    }


    private boolean validateParam(Map<String, String> queryData) {
        if (StringUtils.isEmpty(queryData.get("agentID")) ||
                StringUtils.isEmpty(queryData.get("type")) ||
                StringUtils.isEmpty(queryData.get("operateType")) ||
                StringUtils.isEmpty(queryData.get("deviceNO"))) {
            return false;
        }
        return true;
    }
}
