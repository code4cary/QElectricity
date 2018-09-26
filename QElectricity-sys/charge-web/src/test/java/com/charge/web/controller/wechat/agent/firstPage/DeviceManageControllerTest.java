package com.charge.web.controller.wechat.agent.firstPage;

import com.alibaba.fastjson.JSON;
import com.charge.ChargeApplication;
import com.charge.entity.model.CommonOutputDO;
import com.charge.entity.po.back.wechat.agent.DeviceManage;
import com.charge.service.biz.wechat.agent.ChargingBoxService;
import com.charge.web.controller.base.BaseController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 26/09/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChargeApplication.class)
public class DeviceManageControllerTest extends BaseController {

    @Autowired
    private ChargingBoxService chargingBoxService;
    //
    public static void main(String... args) {

    }

    @Test
    public void testGetDeviceManageInfo() throws Exception {


        //封装查询条件到map
        Map<String,String> queryDataMap = new HashMap<>();
        queryDataMap.put("agentId","123");
        queryDataMap.put("type","1");
       // queryDataMap.put("statusRange","");
       // queryDataMap.put("searchData","");

        //查询代理商设备管理信息
        List<DeviceManage> deviceManageList = chargingBoxService.findDeviceManageInfo(queryDataMap);

        CommonOutputDO commonOutputDO = returnSuccess(deviceManageList);
        Object json = JSON.toJSON(commonOutputDO);
        System.out.println(json);
    }
}