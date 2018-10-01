package com.charge.web.controller.wechat.user.firstPage.personalCenter.myWallet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.charge.common.enums.StatusInfo;
import com.charge.entity.model.CommonOutputDO;
import com.charge.service.biz.wechat.user.UserService;
import com.charge.service.biz.wechat.wxpay.WxPayService;
import com.charge.web.controller.base.BaseController;
import com.charge.web.utils.CommonDataReturnUtil;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("wechat/user/firstPage/personalCenter/myWallet/")
public class RechargeController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    WxPayService wxPayService;

    @RequestMapping("recharge")
    public CommonOutputDO recharge(@RequestBody Map<String, String> queryData, HttpServletRequest request) {
        //如果传入的参数不符合要求
        if (queryData == null || queryData.isEmpty()) CommonDataReturnUtil.requestFail(StatusInfo.FailInfo1);

        //skey
        String skey = queryData.get("skey");
        //充值类型:BALANCE=0:余额充值；DEPOSIT=1:押金充值
        String type = queryData.get("rechargeType");
        //金额
        String amount = queryData.get("amount");

//        String amount1 = Integer.parseInt(amount)+"";

        Double value = Double.valueOf(amount) * 100;
        amount = value.intValue()+"";
        //查询数据库该skey对应的openid
        String openid = userService.findUserOpenIdBySkey(skey);

        if (StringUtils.isEmpty(openid)) {
            return returnFailed(null, "不存在该用户openId");
        }
        //发起微信支付
        Map<String, String> resultMap = wxPayService.wxUnifyOrder(openid, amount, request);
        if (null == resultMap) {
            return returnFailed(null, "发起微信支付下单失败！！系统异常！！");
        }
        return returnSuccess(resultMap);
    }
}
