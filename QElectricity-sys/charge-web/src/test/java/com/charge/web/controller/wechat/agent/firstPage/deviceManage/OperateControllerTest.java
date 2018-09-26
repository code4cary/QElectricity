package com.charge.web.controller.wechat.agent.firstPage.deviceManage;

import com.alibaba.fastjson.JSON;
import com.charge.ChargeApplication;
import com.charge.entity.model.CommonOutputDO;
import com.charge.service.biz.wechat.agent.ChargingBoxService;
import com.charge.web.controller.base.BaseController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vincent on 26/09/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChargeApplication.class)
public class OperateControllerTest extends BaseController {

    @Autowired
    private ChargingBoxService chargingBoxService;

    //
    public static void main(String... args) {

    }

    @Test
    public void testGetOperateDeviceManageInfo() throws Exception {


        //封装查询条件到map
        Map<String, String> queryDataMap = new HashMap<>();
        queryDataMap.put("agentId", "123");
        queryDataMap.put("type", "1");
        queryDataMap.put("operateType", "0");//0:unbind,1:reboot
        queryDataMap.put("deviceNO", "MC101710123458");

        //进行代理商设备管理页之解绑/重启操作
        Boolean operateDeviceManage = chargingBoxService.opereteChargingBox(queryDataMap);


        CommonOutputDO commonOutputDO = operateDeviceManage?returnSuccess(null):
                returnFailed(null,"操作失败");

        Object json = JSON.toJSON(commonOutputDO);
        System.out.println(json);
    }
}