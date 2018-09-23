package com.charge.web.utils;

import com.charge.common.enums.StatusInfo;

import java.util.HashMap;
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


//    public static Map requestSuccess(StatusInfo statusInfo, String pageKey, String dataKey, List dataList) {
//
//        Map dataMap = new HashMap<>();
//        dataMap.put(codeKey, statusInfo.getCode());
//        dataMap.put(msgKey, statusInfo.getMsg());
//        Map map = new HashMap<>();
//        map.put(dataKey, dataList);
//        //dataMap.put(pageKey, new HashMap<String,List<ShopInfo>>().put(dataKey, dataList));
//        dataMap.put(pageKey, map);
//
//        return dataMap;
//    }

//    public static Map requestSuccess(StatusInfo statusInfo, String pageKey, String dataKey, String dataString) {
//
//        List dataList = new ArrayList<>();
//        dataList.add(dataString);
//        Map dataMap = requestSuccess(statusInfo, pageKey, dataKey, dataList);
//
//        return dataMap;
//    }

//    public static Map requestSuccess(StatusInfo statusInfo, String pageKey, String dataKey, Map dataMap) {
//
//        List dataList = new ArrayList<>();
//        dataList.add(dataMap);
//        Map data = requestSuccess(statusInfo, pageKey, dataKey, dataList);
//
//        return data;
//    }




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
        Map map = new HashMap<>();
        map.put(dataKey, data);
        dataMap.put(pageKey, map);
        return dataMap;
    }

    public static Map returnSuccess(StatusInfo statusInfo) {
        Map dataMap = new HashMap<>();
        dataMap.put("code", statusInfo.getCode());
        dataMap.put("msg", statusInfo.getMsg());
        return dataMap;
    }

    public static Map returnFailed(StatusInfo statusInfo) {
        Map dataMap = new HashMap<>();
        dataMap.put("code", statusInfo.getCode());
        dataMap.put("msg", statusInfo.getMsg());
        return dataMap;
    }
}
