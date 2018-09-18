package com.charge.web.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vincent on 10/09/2018.
 */
public class WXUtil {
    public static JSONObject getSessionKeyOrOpenId(String code){
        //微信端登录code
        String wxCode = code;

        String appid = null;

        String secret = null;

        String grant_type = "authorization_code";

        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String,String> requestUrlParam = new HashMap<String, String>(  );
        requestUrlParam.put( "appid", appid );//小程序appId
        requestUrlParam.put( "secret",secret );
        requestUrlParam.put( "js_code",wxCode );//小程序端返回的code
        requestUrlParam.put( "grant_type",grant_type );//默认参数

        //发送post请求读取调用微信接口获取openid用户唯一标识
        JSONObject jsonObject = JSON.parseObject( UrlUtil.sendPost( requestUrl,requestUrlParam ));
        return jsonObject;
    }

}


