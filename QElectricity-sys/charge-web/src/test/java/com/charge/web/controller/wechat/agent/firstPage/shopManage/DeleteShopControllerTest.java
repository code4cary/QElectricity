package com.charge.web.controller.wechat.agent.firstPage.shopManage;

import com.charge.ChargeApplication;
import com.charge.service.biz.wechat.user.ShopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vincent on 27/09/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChargeApplication.class)
public class DeleteShopControllerTest {
    //
    public static void main(String... args) {

    }

    @Test
    @Rollback
    @Transactional//通常我们单元测试为了保证每个测试之间的数据独立，会使用@Rollback注解让每个单元测试都能在结束时回滚
    public void testDeleteShop() throws Exception {

        int i = shopService.deleteByPrimaryKey(1);
        System.out.println(i);

    }


    @Autowired
    private ShopService shopService;
}