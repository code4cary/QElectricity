package com.charge.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * JSON转对象
 * @author mikeeyao
 *
 */
@SuppressWarnings("unchecked")
public class JsonToBeanUtil {
    private static Gson gson     = new Gson();
    private static Gson gsonNull = new GsonBuilder().serializeNulls().create();

    public static <T> T jsonToBean(String jsonString, Type type) {
        return (T) gson.fromJson(jsonString, type);
    }
    
    public static <T> T jsonToMap(String jsonString, Type type) {
        return (T) gson.fromJson(jsonString, type);
    }

    /**
     * 此方法会过滤为null的值
     * 
     * @param t
     * @return
     */
    public static String beanToString(Object t) {
        return gson.toJson(t);
    }
    
    public static String beanToMap(Object t) {
        return gson.toJson(t);
    }

    /**
     * 此方法会把为null的值以null返回
     * 
     * @param t
     * @return
     */
    public static String beanToStringNulls(Object t) {
        return gsonNull.toJson(t);
    }

    /**
     * map转换json. <br>
     * 详细说明
     * 
     * @param params 集合
     * @return
     * @return String json字符串
     * @throws @author v_mtlong
     */
    public static String mapToJson(Map<String, Object> params) {
        JSONObject jsonMap = new JSONObject(params);
        return jsonMap.toString();
    }

    
}
