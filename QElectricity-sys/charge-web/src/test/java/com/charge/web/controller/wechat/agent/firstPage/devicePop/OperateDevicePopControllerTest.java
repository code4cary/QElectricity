package com.charge.web.controller.wechat.agent.firstPage.devicePop;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 27/09/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChargeApplication.class)
public class OperateDevicePopControllerTest extends BaseController{
    //
    public static void main(String... args) {

    }

    @Autowired
    private ChargingBoxService chargingBoxService;

    @Test
    public void testGetOperatePopInfo() throws Exception {

        //封装查询条件到map
        Map<String,Object> queryDataMap = new HashMap<>();
        queryDataMap.put("agentId","123");
        queryDataMap.put("operateType","1");
        queryDataMap.put("deviceNO","123");
        List<String> windowList = new ArrayList<>();
        windowList.add("1");
        windowList.add("2");
        queryDataMap.put("windowNO",windowList);

        //查询代理商设备管理信息
        Boolean isOperateSuccess = chargingBoxService.operateChargingBoxDevicePopPage(queryDataMap);

        CommonOutputDO commonOutputDO = isOperateSuccess?returnSuccess(null):
                returnFailed(null,"操作失败");
        Object json = JSON.toJSON(commonOutputDO);
        System.out.println(json);
    }
}