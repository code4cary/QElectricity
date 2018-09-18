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
    private static final String dataKey = "data";

    //封装返回的数据
    private Map returnData;

    public CommonReturnDataMap() {
    }


    public CommonReturnDataMap(StatusInfo statusInfo) {

        this.returnData.put(codeKey, statusInfo.getCode());
        this.returnData.put(msgKey, statusInfo.getMsg());
        //请求失败,数据为空.注意和请求成功,但是返回数据为空的区别
        this.returnData.put(dataKey, null);

    }

    public CommonReturnDataMap(StatusInfo statusInfo, String pageKey, String dataKey, List dataList) {//dataKey为最里层的数据key
        this.returnData.put(codeKey, statusInfo.getCode());
        this.returnData.put(msgKey, statusInfo.getMsg());

        //数据库查询到的数据封装成的map
        HashMap infoMap = new HashMap();
        infoMap.put(dataKey, dataList);
        //上面的数据显示在你哪个页面的map
        HashMap pageMap = new HashMap();
        pageMap.put(pageKey, infoMap);

        //求情成功
        this.returnData.put(dataKey, pageMap);
    }

    public static String getCodeKey() {
        return codeKey;
    }

    public static String getMsgKey() {
        return msgKey;
    }

    public static String getDataKey() {
        return dataKey;
    }

    public Map getReturnData() {
        return returnData;
    }

    public void setReturnData(Map returnData) {
        this.returnData = returnData;
    }
}
