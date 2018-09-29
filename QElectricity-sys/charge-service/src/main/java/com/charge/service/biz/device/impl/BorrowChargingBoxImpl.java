package com.charge.service.biz.device.impl;

import com.charge.service.biz.device.BorrowChargingBox;
import com.charge.service.biz.util.HttpUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vincent on 29/09/2018.
 */
@Service
@PropertySource(value = "classpath:device.properties")
public class BorrowChargingBoxImpl implements BorrowChargingBox {

    @Value("baseUrl")
    private String BaseUrl;

    /**
     * 向指定设备请求打开某个充电宝锁位
     *
     * @param chargingBoxNo
     * @return
     */
    @Override
    public Map<Object, Object> borrowChargingBox(String chargingBoxNo) {

        String url = BaseUrl + "borrowChargingBox";
        Map<String, String> chargingBoxNoMap = new HashMap<>();
        chargingBoxNoMap.put("chargingBoxNo", chargingBoxNo);
        String backData = HttpUtil.sendPost(url, chargingBoxNoMap);

        //将返回的数据封装成map
        Map<Object, Object> backDataMap = HttpUtil.strToMap(backData);

        Object code = backDataMap.get("code");
        if (!code.equals("1")) {//请求失败
            return null;
        }
        return backDataMap;
    }


    public static void main(String... args) {
        //Map<Object, Object> backDataMapTemp = new HashMap<>();
        Map<String, String> backDataMapTemp = new HashMap<>();
        backDataMapTemp.put("test1", "1");
        backDataMapTemp.put("test2", "2");
        System.out.println(backDataMapTemp);

        Map<String, String> backDataMap = new HashMap<>();
        BeanUtils.copyProperties(backDataMapTemp, backDataMap);
        System.out.println(backDataMap);

    }
}
