package com.charge.web.controller.wechat.agent.firstPage;

import com.charge.entity.model.CommonOutputDO;
import com.charge.entity.po.back.wechat.agent.DeviceManage;
import com.charge.service.biz.wechat.agent.ChargingBoxService;
import com.charge.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 15/09/2018.
 */
@Slf4j
@RestController
@RequestMapping("wechat/agent/firstPage/deviceManage")
public class DeviceManageController extends BaseController {


    @Autowired
    private ChargingBoxService chargingBoxService;

    @RequestMapping
    public CommonOutputDO<Object> getDeviceManageInfo(Map<String, String> queryData) {
        if (!validateParam(queryData)) {
            return returnFailed(null, "参属为空异常");
        }
        log.info("查询代理商设备管理页...");

        //获取agentId
        String agentId = queryData.get("agentID");

        //获取类型:充电箱还是充电线:1代表充电线,0代表充电宝
        String type = queryData.get("type");

        //状态:离线,在线,全部
        String statusRange = queryData.get("statusRange");

        //获取searchData 搜索范围：userPhone(用户手机号),shopName(商户名字),orderStatus(订单状态)
        String searchData = queryData.get("searchData");

        //封装查询条件到map
        Map<String,String> queryDataMap = new HashMap<>();
        queryDataMap.put("agentId",agentId);
        queryDataMap.put("type",type);
        queryDataMap.put("statusRange",statusRange);
        queryDataMap.put("searchData",searchData);

        //查询代理商设备管理信息
        List<DeviceManage> deviceManageList = chargingBoxService.findDeviceManageInfo(queryDataMap);

        log.info("over");
        return returnSuccess(deviceManageList);
    }


    private boolean validateParam(Map<String, String> queryData) {
        if (StringUtils.isEmpty(queryData.get("agentId")) ||
                StringUtils.isEmpty(queryData.get("type")) ||
                StringUtils.isEmpty(queryData.get("statusRange")) ||
                StringUtils.isEmpty(queryData.get("searchData"))) {
            return false;
        }
        return true;
    }
}
