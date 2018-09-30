package com.charge.web.controller.wechat.user;

import com.charge.entity.model.CommonOutputDO;
import com.charge.entity.po.wechat.user.Order;
import com.charge.service.biz.wechat.user.UserService;
import com.charge.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by vincent on 30/09/2018.
 */

@Slf4j
@RestController
@RequestMapping("wechat/user/backPowerBank")
public class BackPowerBank extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 前端查询用户是否归还充电宝
     * 前端频繁访问的接口考虑使用redis
     *
     * @param queryData
     * @return
     */
    @RequestMapping
    public CommonOutputDO<Object> getBackPowerBankInfo(@RequestBody Map<String, String> queryData) {

        if (!validateParam(queryData)) {
            return returnFailed(null, "参属为空异常");
        }
        log.info("查询用户是否归还充电宝...");

        Order order = userService.findBackPowerBankInfo(queryData.get("skey"));

        if (order == null) {
            return returnFailed(null, "该用户所有订单的相关状态都已完成");
        } else {
            if (order.getPowerBankStatus().equals("1") &&
                    order.getPayStatus().equals("0") &&
                    order.getHaveShowed().equals("0")) {//归还成功但未支付且未被该接口访问过
                //将订单的HAVE_SHOWED字段改为1,因为已经访问过了
                Order OrderUpdate = new Order();
                BeanUtils.copyProperties(order, OrderUpdate);
                OrderUpdate.setHaveShowed("1");
                userService.updateOrderHaveShowed(OrderUpdate);
                return returnSuccess(order);

            } else if (order.getPowerBankStatus().equals("0")) {//未归还.订单正在进行中
                return returnFailed(order, "该用户有正在进行的订单");
            }
        }

        log.info("over");
        return returnFailed(null, null);

    }

    private boolean validateParam(Map<String, String> queryData) {
        if (StringUtils.isEmpty(queryData.get("skey"))) {
            return false;
        }
        return true;
    }

    public static void main(String... args) {
        Order order = new Order();
        order.setId(1);
        order.setHaveShowed("0");
        order.setPayAmount("6");
        Order OrderUpdate = new Order();
        BeanUtils.copyProperties(order, OrderUpdate);
        OrderUpdate.setHaveShowed("1");
        System.out.println(OrderUpdate.getId());
        System.out.println(OrderUpdate);
    }
}
