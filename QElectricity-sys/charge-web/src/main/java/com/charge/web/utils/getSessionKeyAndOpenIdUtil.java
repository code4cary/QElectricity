package com.charge.web.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vincent on 10/09/2018.
 */
public class getSessionKeyAndOpenIdUtil {
    public static JSONObject getSessionKeyOrOpenId(String code) {
        //微信端登录code
        String wxCode = code;

        String appid = "wx7d498f7ffd96b40e";

        String secret = "097bf881a856edbdda8438f80339ffa7";

        String grant_type = "authorization_code";

        String requestUrl =
                "https://api.weixin.qq.com/sns/jscode2session?" +
                        "appid=" + appid +
                        "&secret=" + secret +
                        "&js_code=" + wxCode +
                        "&grant_type" + grant_type;
        Map<String, String> requestUrlParam = new HashMap<String, String>();

        //发送post请求读取调用微信接口获取openid用户唯一标识
        JSONObject jsonObject = JSON.parseObject(UrlUtil.sendPost(requestUrl, requestUrlParam));
        return jsonObject;
    }


}


