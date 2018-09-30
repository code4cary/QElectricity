package com.charge.web.controller.wechat.user.firstPage;

import com.charge.entity.model.CommonOutputDO;
import com.charge.entity.po.wechat.user.Account;
import com.charge.entity.po.wechat.user.Order;
import com.charge.service.biz.wechat.agent.ChargingBoxService;
import com.charge.service.biz.wechat.user.OrderService;
import com.charge.service.biz.wechat.user.UserService;
import com.charge.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by vincent on 17/09/2018.
 */
@Slf4j
@RestController
@RequestMapping("wechat/user/firstPage/scanBorrow")
public class ScanBorrowController extends BaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private ChargingBoxService chargingBoxService;

    public CommonOutputDO<Object> scanBorrow(@RequestBody(required = true) Map<String, String> queryData) {
        if (!validateParam(queryData)) {
            return returnFailed(null, "参属为空异常");
        }
        log.info("用户扫码借充电宝...");

        //获取用户账户信息
        Account account = userService.findAccountInfo(queryData.get("skey"));
        if (account == null || account.getDepositStatus() == null) {
            return returnFailed(null, "当前用户没交押金");
        }

        //判断用户是否有未完成支付的订单
        Order orderUnpaid = orderService.findOrderUnpaid(queryData.get("skey"));
        if (orderUnpaid != null && orderUnpaid.getPayStatus().equals("0")) {
            returnFailed(orderUnpaid, "用户有未完成支付的订单");

        }

        //判断用户是否有正在进行的订单
        Order orderUndone = orderService.findOrderDoing(queryData.get("skey"));
        if (orderUndone != null && orderUndone.getPowerBankStatus().equals("0")) {
            returnFailed(orderUndone, "用户有正在进行的订单");
        }

        //查询当前充电箱是否可借充电宝
        String powerBankNO = chargingBoxService.findCanBorrow(queryData);

        log.info("over");
        return powerBankNO != null ? returnSuccess(powerBankNO) : returnFailed(null, "当前充电箱无法借出充电宝");
    }


    private boolean validateParam(Map<String, String> queryData) {
        if (StringUtils.isEmpty(queryData.get("skey")) ||
                StringUtils.isEmpty(queryData.get("deviceNO"))) {
            return false;
        }
        return true;
    }
}
