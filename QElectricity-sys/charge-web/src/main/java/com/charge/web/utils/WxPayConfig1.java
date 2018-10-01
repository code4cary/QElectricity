package com.charge.web.utils;

/**
 * Created by vincent on 22/09/2018.
 */
public class WxPayConfig1 {

    //小程序appid
    public static final String appid = "wx7d498f7ffd96b40e";
    //微信支付的商户id
    public static final String mch_id = "1514872221";
    //微信支付的商户密钥
    public static final String key = "wx7d498f7ffd96b40eqwer4321qdxiaochengxu20182018xcx";
    //支付成功后的服务器回调url
   // public static final String notify_url = "https://??/??/weixin/api/wxNotify";
   // public static final String notify_url = "https://qdtechwx.com/wechat/wxPay/wxNotify";
    public static final String notify_url = "https://qdtechwx.com/wechat/wxPay";
    //签名方式，固定值
    public static final String SIGNTYPE = "MD5";
    //交易类型，小程序支付的固定值为JSAPI
    public static final String TRADETYPE = "JSAPI";
    //微信统一下单接口地址
    public static final String pay_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
}




