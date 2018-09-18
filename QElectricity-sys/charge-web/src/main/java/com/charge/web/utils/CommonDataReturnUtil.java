package com.charge.web.utils;

import com.charge.common.enums.StatusInfo;
import com.charge.entity.model.CommonReturnDataMap;

import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 18/09/2018.
 */
public class CommonDataReturnUtil {


    /**
     * 小程序请求后台请求失败
     * @param statusInfo
     * @return
     */
    public static Map requestFail(StatusInfo statusInfo) {

       return new CommonReturnDataMap(statusInfo).getReturnData();
    }


    /**
     * 小程序请求后台成功,返回数据
     * @param statusInfo
     * @param pageKey
     * @param dataKey
     * @param dataList
     * @return
     */
    public static Map requestSuccess(StatusInfo statusInfo,String pageKey, String dataKey, List dataList) {

        return new CommonReturnDataMap(statusInfo,pageKey,dataKey,dataList).getReturnData();
    }
}
