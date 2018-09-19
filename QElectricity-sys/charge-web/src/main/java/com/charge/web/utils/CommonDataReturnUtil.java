package com.charge.web.utils;

import com.charge.common.enums.StatusInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 18/09/2018.
 */
public class CommonDataReturnUtil {


    //状态码key
    private static final String codeKey = "code";
    //状态信息key
    private static final String msgKey = "msg";

    /**
     * 小程序请求后台请求失败
     *
     * @param statusInfo
     * @return
     */
    public static Map requestFail(StatusInfo statusInfo) {
        Map dataMap = new HashMap<>();
        dataMap.put(codeKey, statusInfo.getCode());
        dataMap.put(msgKey, statusInfo.getMsg());
        return dataMap;
    }


    /**
     * 小程序请求后台成功,返回数据
     *
     * @param statusInfo
     * @param pageKey
     * @param dataKey
     * @param data
     * @return
     */
    public static Map requestSuccess(StatusInfo statusInfo, String pageKey, String dataKey, Object data) {

        Map dataMap = new HashMap<>();
        dataMap.put(codeKey, statusInfo.getCode());
        dataMap.put(msgKey, statusInfo.getMsg());
        if (data instanceof List) data = (List) data;
        dataMap.put(pageKey, new HashMap<>().put(dataKey, data));

        return dataMap;
    }


}
