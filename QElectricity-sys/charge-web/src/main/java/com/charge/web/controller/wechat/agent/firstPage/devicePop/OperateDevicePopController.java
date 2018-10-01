package com.charge.web.controller.wechat.agent.firstPage.devicePop;

import com.charge.entity.model.CommonOutputDO;
import com.charge.service.biz.wechat.agent.ChargingBoxService;
import com.charge.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 16/09/2018.
 */
@Slf4j
@RestController
@RequestMapping("wechat/agent/firstPage/devicePop/operate")
public class OperateDevicePopController extends BaseController {

    @Autowired
    private ChargingBoxService chargingBoxService;

    @RequestMapping
    public CommonOutputDO<Object> getOperatePopInfo(@RequestBody(required = true) Map<String, Object> queryData) {
        if (!validateParam(queryData)) {
            return returnFailed(null, "参属为空异常");
        }
        log.info("操作代理商设备弹出页之弹出/重启操作...");

        //获取agentId
        String agentId = (String) queryData.get("agentID");

        //获取设备编号
        String deviceNO = (String) queryData.get("deviceNO");

        //获取窗口号
        List<String> windowNO = (List<String>) queryData.get("windowNO");

        //获取操作类型  0:pop,1:reboot
        String operateType = String.valueOf(queryData.get("operateType"));
        if (operateType.equals("0")) {
            if (windowNO == null) {
                return returnFailed(null, "参属为空异常");
            }
        }

        //封装查询条件到map
        Map<String, Object> queryDataMap = new HashMap<>();
        queryDataMap.put("agentId", agentId);
        queryDataMap.put("operateType", operateType);
        queryDataMap.put("deviceNO", deviceNO);
        queryDataMap.put("windowNO", windowNO);

        //操作代理商设备弹出页选择的操作
        Boolean isOperateSuccess = chargingBoxService.operateChargingBoxDevicePopPage(queryDataMap);

        isOperateSuccess = true;

        log.info("over");
        return isOperateSuccess ? returnSuccess(null) :
                returnFailed(null, "操作失败");
    }


    private boolean validateParam(Map<String, Object> queryData) {
        if (StringUtils.isEmpty(queryData.get("agentID")) ||
                StringUtils.isEmpty(queryData.get("deviceNO")) ||
                StringUtils.isEmpty(queryData.get("operateType"))) {
            return false;
        }
        return true;
    }
}
