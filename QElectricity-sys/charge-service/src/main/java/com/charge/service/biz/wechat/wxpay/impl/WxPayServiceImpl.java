package com.charge.service.biz.wechat.wxpay.impl;

import com.charge.common.utils.IPUtil;
import com.charge.common.utils.wxpay.MyWXPayConfig;
import com.charge.common.utils.wxpay.WXPay;
import com.charge.common.utils.wxpay.WXPayUtil;
import com.charge.service.biz.wechat.wxpay.WxPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class WxPayServiceImpl implements WxPayService {

    @Autowired
    MyWXPayConfig myWXPayConfig;

    @Override
    public Map<String, String> wxUnifyOrder(String openId, String amount, HttpServletRequest request) {
        try {
            //商品名称
            String body = "借用充电宝";
            //获取客户端的ip地址,
            String spbill_create_ip = IPUtil.getIpAddr(request);
            WXPay wxpay = new WXPay(myWXPayConfig);
            Map<String, String> data = new HashMap<String, String>();
            data.put("body", body);
            data.put("out_trade_no", System.currentTimeMillis() + "");
            //data.put("device_info", "sb");
            data.put("fee_type", "CNY");
            data.put("total_fee", amount);
            data.put("spbill_create_ip", spbill_create_ip);
            data.put("notify_url", "http://www.weixin.qq.com/wxpay/pay.php");
            data.put("trade_type", "JSAPI");  // 此处指定为扫码支付
            //data.put("product_id", "12");
            data.put("openid", openId);
            Map<String, String> resp = wxpay.unifiedOrder(data);
            if ("SUCCESS".equals(resp.get("return_code"))) {
                return combineData(resp);
            } else {
                log.error("调用微信支付下单接口失败，原因=[ {} ]", resp.get("return_msg"));
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("微信支付统一下单失败，原因=[ {} ]", e.getMessage());
            return null;
        }
    }

    private Map<String, String> combineData(Map<String, String> map) throws Exception {
        HashMap<String, String> dataMap = new HashMap<String, String>();
        String s = map.get("prepay_id");
        dataMap.put("appId", myWXPayConfig.getAppID());
        dataMap.put("nonceStr", WXPayUtil.generateNonceStr());
        dataMap.put("timeStamp", System.currentTimeMillis()/1000 + "");
        dataMap.put("signType", "MD5");
        dataMap.put("package", "prepay_id=" + s);
        String sign = WXPayUtil.generateSignature(dataMap, myWXPayConfig.getKey());
        dataMap.put("paySign", sign);
        return dataMap;
    }
}
