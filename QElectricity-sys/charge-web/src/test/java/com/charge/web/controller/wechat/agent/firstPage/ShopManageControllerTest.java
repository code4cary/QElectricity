package com.charge.web.controller.wechat.agent.firstPage;

import com.alibaba.fastjson.JSON;
import com.charge.ChargeApplication;
import com.charge.entity.model.CommonOutputDO;
import com.charge.entity.po.back.wechat.agent.ShopManage;
import com.charge.service.biz.wechat.user.firstPage.ShopService;
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
 * Created by vincent on 27/09/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChargeApplication.class)
public class ShopManageControllerTest extends BaseController {
    //
    public static void main(String... args) {

    }

    @Autowired
    private ShopService shopService;

    @Test
    public void testGetShopManageInfo() throws Exception {
        //封装查询条件到map
        Map<String, String> queryDataMap = new HashMap<>();
        queryDataMap.put("agentId", "123");
        queryDataMap.put("searchData", "");
        queryDataMap.put("type", "1");

        //查询代理商商户管理页信息
        List<ShopManage> shopManageList = shopService.findShopManageInfo(queryDataMap);

        CommonOutputDO commonOutputDO = returnSuccess(shopManageList);
        Object json = JSON.toJSON(commonOutputDO);
        System.out.println(json);
    }
}