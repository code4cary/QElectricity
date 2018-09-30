package com.charge.web.controller.wechat.user.firstPage;

import com.alibaba.fastjson.JSON;
import com.charge.ChargeApplication;
import com.charge.entity.model.CommonOutputDO;
import com.charge.entity.po.wechat.user.Account;
import com.charge.entity.po.wechat.user.Order;
import com.charge.service.biz.wechat.agent.ChargingBoxService;
import com.charge.service.biz.wechat.user.OrderService;
import com.charge.service.biz.wechat.user.UserService;
import com.charge.web.controller.base.BaseController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vincent on 30/09/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChargeApplication.class)
public class ScanBorrowControllerTest extends BaseController {
    //
    public static void main(String... args) {

    }

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private ChargingBoxService chargingBoxService;

    @Test
    public void testScanBorrow() throws Exception {

        Map<String, String> queryData = new HashMap<>();

        //该skey对应的用户存在,且其当前状态可以借充电宝
        String skey = "skey9876543210";
        queryData.put("skey", skey);
        queryData.put("deviceNO", "MC101710123451");//该充电宝在线且其可借可还充电宝的数量都为5

        //该skey对应的用户不存在
        String skey2 = "skey987654321";
        //该skey对应的用户有未完成支付的订单
        String skey3 = "skey9876543211";
        //该skey对应的用户有正在进行的订单
        String skey4 = "skey9876543222";


        //获取用户账户信息
        Account account = userService.findAccountInfo(skey2);
        if (account == null || account.getDepositStatus() == null) {
            CommonOutputDO commonOutputDO = returnFailed(null, "当前用户没交押金");
            Object json = JSON.toJSON(commonOutputDO);
            //skey2 = "skey987654321"  {"msg":"当前用户没交押金","code":"0","flag":"0"}
            System.out.println(json);
            System.out.println("--------------------------------------------------------");
        }

        //判断用户是否有未完成支付的订单
        Order orderUnpaid = orderService.findOrderUnpaid(skey3);
        if (orderUnpaid != null && orderUnpaid.getPayStatus().equals("0")) {
            CommonOutputDO commonOutputDO = returnFailed(orderUnpaid, "用户有未完成支付的订单");
            Object json = JSON.toJSON(commonOutputDO);
            //skey3 = "skey9876543211"
            // {"msg":"用户有未完成支付的订单","code":"0","flag":"0","data":{"orderNum":"20180929123457","payAmount":"0","createTime":1536473654000,"payStatus":"0"}}
            System.out.println(json);
            System.out.println("--------------------------------------------------------");
        }

        //判断用户是否有正在进行的订单
        Order orderUndone = orderService.findOrderDoing(skey4);
        if (orderUndone != null && orderUndone.getPowerBankStatus().equals("0")) {
            CommonOutputDO commonOutputDO = returnFailed(orderUndone, "用户有正在进行的订单");
            Object json = JSON.toJSON(commonOutputDO);
            //该skey对应的用户有正在进行的订单
            //{"msg":"用户有正在进行的订单","code":"0","flag":"0","data":{"orderNum":"20190930255648","createTime":1538236800000,"powerBankStatus":"0"}}
            System.out.println(json);
            System.out.println("--------------------------------------------------------");
        }

        //查询当前充电箱是否可借充电宝
        //queryData.get("skey)对应的用户存在,且其当前状态可以借充电宝
        //{"msg":"请求成功","code":"1","flag":"1","data":"pb20180930"}
        String powerBankNO = chargingBoxService.findCanBorrow(queryData);
        System.out.println(powerBankNO);

        CommonOutputDO commonOutputDO = null;
        commonOutputDO = (powerBankNO != null ? returnSuccess(powerBankNO) : returnFailed(null, "当前充电箱无法借出充电宝"));
        Object json = JSON.toJSON(commonOutputDO);
        System.out.println(json);

    }
}