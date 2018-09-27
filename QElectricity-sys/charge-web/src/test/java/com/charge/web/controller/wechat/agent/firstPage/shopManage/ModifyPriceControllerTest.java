package com.charge.web.controller.wechat.agent.firstPage.shopManage;

import com.alibaba.fastjson.JSON;
import com.charge.ChargeApplication;
import com.charge.entity.model.CommonOutputDO;
import com.charge.entity.model.ModifyPriceTypeCBDO;
import com.charge.entity.po.device.PriceTypeCB;
import com.charge.service.biz.wechat.agent.AgentService;
import com.charge.web.controller.base.BaseController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by vincent on 27/09/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChargeApplication.class)
public class ModifyPriceControllerTest extends BaseController{

    @Autowired
    private AgentService agentService;
    //
    public static void main(String... args) {

    }

    @Test
    public void testModifyPriceInfo() throws Exception {

        //修改价格策略

        ModifyPriceTypeCBDO modifyPriceDO = new ModifyPriceTypeCBDO();
        modifyPriceDO.setAgentId("123");
        modifyPriceDO.setShopId("1");
        PriceTypeCB priceTypeCB = new PriceTypeCB();
        priceTypeCB.setFreeTime("50");
        priceTypeCB.setPricePerHour("6.6");
        priceTypeCB.setTopPricePerDay("66.6");
        modifyPriceDO.setPriceTypeCB(priceTypeCB);

        Boolean isModifySuccess =  agentService.modifyPrice(modifyPriceDO);

        CommonOutputDO commonOutputDO = isModifySuccess ? returnSuccess(null) :
                returnFailed(null, "价格修改失败");
        Object json = JSON.toJSON(commonOutputDO);
        System.out.println(json);
    }
}