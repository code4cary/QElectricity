package com.charge.web.controller.wechat.user;

import com.alibaba.fastjson.JSON;
import com.charge.ChargeApplication;
import com.charge.entity.model.CommonOutputDO;
import com.charge.entity.po.wechat.user.Order;
import com.charge.service.biz.util.SysoUtil;
import com.charge.service.biz.wechat.user.UserService;
import com.charge.web.controller.base.BaseController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by vincent on 30/09/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChargeApplication.class)
public class BackPowerBankTest extends BaseController {
    //
    public static void main(String... args) {

    }

    @Autowired
    private UserService userService;

    @Test
    public void testGetBackPowerBankInfo() throws Exception {

        String skey = "skey9876543210";

        Order order = userService.findBackPowerBankInfo(skey);
        SysoUtil.outPutToConsole(order);

        CommonOutputDO commonOutputDO = null;

        if (order == null) {
            commonOutputDO = returnFailed(null, "该用户所有订单的相关状态都已完成");
        } else {
            if (order.getPowerBankStatus().equals("1") &&
                    order.getPayStatus().equals("0") &&
                    order.getHaveShowed().equals("0")) {//归还成功但未支付且未被该接口访问过
                //将订单的HAVE_SHOWED字段改为1,因为已经访问过了
                Order OrderUpdate = new Order();
                BeanUtils.copyProperties(order, OrderUpdate);
                OrderUpdate.setHaveShowed("1");
                userService.updateOrderHaveShowed(OrderUpdate);
                commonOutputDO = returnSuccess(order);

            } else if (order.getPowerBankStatus().equals("0")) {//未归还.订单正在进行中
                commonOutputDO = returnFailed(order, "该用户有正在进行的订单");
            }
        }
        Object json = JSON.toJSON(commonOutputDO);
        System.out.println(json);

    }
}