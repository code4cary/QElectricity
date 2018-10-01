package com.charge.service.biz.wechat.wxpay;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

//微信支付service
public interface WxPayService {

    //统一下单方式
    Map<String,String> wxUnifyOrder(String openId, String amount, HttpServletRequest request);

}
