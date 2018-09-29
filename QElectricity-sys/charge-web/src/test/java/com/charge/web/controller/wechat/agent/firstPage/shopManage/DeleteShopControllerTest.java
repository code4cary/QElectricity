package com.charge.web.controller.wechat.agent.firstPage.shopManage;

import com.charge.ChargeApplication;
import com.charge.service.biz.wechat.user.ShopService;
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
public class DeleteShopControllerTest {
    //
    public static void main(String... args) {

    }

    @Autowired
    private ShopService shopService;

    @Test
    public void testDeleteShop() throws Exception {

        DeleteShopController deleteShopController = new DeleteShopController();
        Map<String,String> map = new HashMap<>();
        map.put("agentID","1");
        map.put("shopID","8");
        System.out.println(map);

        int i = shopService.deleteByPrimaryKey(1);
        System.out.println(i);

    }
}