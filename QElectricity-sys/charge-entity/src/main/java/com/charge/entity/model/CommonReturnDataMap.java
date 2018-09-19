package com.charge.entity.model;

import com.charge.common.enums.StatusInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 18/09/2018.
 */
public class CommonReturnDataMap {

    //状态码key
    private static final String codeKey = "code";
    //状态信息key
    private static final String msgKey = "msg";
    //最外层数据key
    private static final String dataKeyOut = "data";

    private StatusInfo statusInfo;

    private String pageKey;

    private String dataKey;

    private String dataList;

    //封装返回的数据
    private Map returnData;

    public CommonReturnDataMap() {
    }


    public CommonReturnDataMap(StatusInfo statusInfo) {

        this.getReturnData().put(codeKey, statusInfo.getCode());
        this.getReturnData().put(msgKey, statusInfo.getMsg());
        //请求失败,数据为空.注意和请求成功,但是返回数据为空的区别
        this.getReturnData().put(dataKeyOut, null);

    }

    public CommonReturnDataMap(StatusInfo statusInfo, String pageKey, String dataKey, String data) {//dataKey为最里层的数据key
        this.setReturnData(new HashMap<>());
        this.getReturnData().put(codeKey, statusInfo.getCode());
        this.getReturnData().put(msgKey, statusInfo.getMsg());

        //上面的数据显示在你哪个页面的map
        HashMap pageMap = new HashMap();
        pageMap.put(dataKey, data);

        //请求成功
        this.getReturnData().put(pageKey, pageMap);
    }

    public CommonReturnDataMap(StatusInfo statusInfo, String pageKey, String dataKey, List dataList) {//dataKey为最里层的数据key
        this.setReturnData(new HashMap<>());
        this.getReturnData().put(codeKey, statusInfo.getCode());
        this.getReturnData().put(msgKey, statusInfo.getMsg());

        //上面的数据显示在你哪个页面的map
        HashMap pageMap = new HashMap();
        pageMap.put(dataKey, dataList);

        //请求成功
        this.getReturnData().put(pageKey, pageMap);
    }

    public static String getCodeKey() {
        return codeKey;
    }

    public static String getMsgKey() {
        return msgKey;
    }

    public static String getDataKey() {
        return dataKeyOut;
    }

    public Map getReturnData() {
        return returnData;
    }

    public void setReturnData(Map returnData) {
        this.returnData = returnData;
    }
}
