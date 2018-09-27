package com.charge.web.controller.wechat.agent.firstPage.shopManage;

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
 * Created by vincent on 27/09/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChargeApplication.class)
public class AddDeviceControllerTest extends BaseController{
    //
    public static void main(String... args) {

    }

    @Autowired
    private ChargingBoxService chargingBoxService;

    @Test
    public void testXxxx() throws Exception {

        Map<String, String> queryData = new HashMap<>();

        queryData.put("shopID", "8");
        queryData.put("deviceNO","MC101710123458");

        //分配充电箱
        Boolean isAddSuccess = chargingBoxService.AddDevice(queryData);


        CommonOutputDO commonOutputDO = isAddSuccess ? returnSuccess(null) :
                returnFailed(null, "分配失败");

        Object json = JSON.toJSON(commonOutputDO);
        System.out.println(json);
    }

}
