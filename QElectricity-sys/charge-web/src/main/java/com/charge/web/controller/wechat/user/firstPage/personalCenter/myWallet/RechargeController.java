package com.charge.web.controller.wechat.user.firstPage.personalCenter.myWallet;

import com.alibaba.fastjson.JSON;
import com.charge.common.enums.StatusInfo;
import com.charge.service.biz.wechat.user.firstPage.UserService;
import com.charge.web.utils.CommonDataReturnUtil;
import com.charge.web.utils.WxPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by vincent on 17/09/2018.
 */

/**
 * 余额页和押金页的充值接口
 */
@RestController
@RequestMapping("wechat/user/firstPage/personalCenter/myWallet/recharge")
public class RechargeController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public Map recharge(@RequestBody Map<String, String> queryData,HttpServletRequest request) {
        //如果传入的参数不符合要求
        if (queryData == null || queryData.isEmpty()) CommonDataReturnUtil.requestFail(StatusInfo.FailInfo1);

        //skey
        String skey = queryData.get("skey");
        //充值类型:BALANCE=0:余额充值；DEPOSIT=1:押金充值
        String type = queryData.get("rechargeType");
        //金额
        String amount = queryData.get("amount");

        //查询数据库该skey对应的openid
        String openid = userService.findUserOpenIdBySkey(skey);

        //发起微信支付
        JSON wxPayData = WxPayUtil.wxPay(openid, request);

        Map wxPay =
                CommonDataReturnUtil.requestSuccess(StatusInfo.SuccessInfo1, "wxPayPage", "wxPay", wxPayData);


        return  wxPay;
    }
}
