package com.charge.service.biz.device.impl;

import com.charge.service.biz.device.SysToSocketService;
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
public class SysToSocketImpl implements SysToSocketService {

    @Value("baseUrl")
    private String BaseUrl;

    /**
     * 向指定设备请求打开某个充电宝锁位
     *
     * @param chargingBoxNo:充电箱的编号
     * @return
     */
    @Override
    public Map<Object, Object> borrowChargingBox(String chargingBoxNo) {

        String url = BaseUrl + "borrowChargingBox";
        Map<String, String> chargingBoxNoMap = new HashMap<>();
        chargingBoxNoMap.put("chargingBoxNo", chargingBoxNo);
        //发送请求,并接受返回数据
        String backData = HttpUtil.sendPost(url, chargingBoxNoMap);

        //将返回的数据封装成map
        Map<Object, Object> backDataMap = HttpUtil.strToMap(backData);

        Object code = backDataMap.get("code");//设备返回状态码
        if (!code.equals("1")) {//请求失败
            return null;
        }
        return backDataMap;
    }


    /**
     * 远程重启设备
     *
     * @param reqPara chargingBoxNo:充电箱的编号
     *                delay:延时时间,0表示立刻重启
     * @return
     */
    @Override
    public Map<Object, Object> rebootDevice(Map<String, String> reqPara) {
        String url = BaseUrl + "rebootDevice";
        //发送请求,并接受返回数据
        String backData = HttpUtil.sendPost(url, reqPara);
        //将返回的数据封装成map
        Map<Object, Object> backDataMap = HttpUtil.strToMap(backData);

        Object code = backDataMap.get("code");//设备返回状态码
        if (!code.equals("1")) {//请求失败
            return null;
        }
        return backDataMap;
    }

    /**
     * 查询充电宝库存总数
     *
     * @param reqPara chargingBoxNo:充电箱的编号
     * @return
     */
    @Override
    public Map<Object, Object> queryPowerBankAmount(Map<String, String> reqPara) {
        String url = BaseUrl + "queryPowerBankAmount";

        //发送请求,并接受返回数据
        String backData = HttpUtil.sendPost(url, reqPara);

        //将返回的数据封装成map
        Map<Object, Object> backDataMap = HttpUtil.strToMap(backData);

        Object code = backDataMap.get("code");//设备返回状态码
        if (!code.equals("1")) {//请求失败
            return null;
        }
        return backDataMap;
    }


    /**
     * 查询充电宝库存明细
     *
     * @param reqPara chargingBoxNo:充电箱的编号
     * @return
     */
    @Override
    public Map<Object, Object> queryPowerBankDetail(Map<String, String> reqPara) {
        String url = BaseUrl + "queryPowerBankDetail";

        //发送请求,并接受返回数据
        String backData = HttpUtil.sendPost(url, reqPara);

        //将返回的数据封装成map
        Map<Object, Object> backDataMap = HttpUtil.strToMap(backData);

        Object code = backDataMap.get("code");//设备返回状态码
        if (!code.equals("1")) {//请求失败
            return null;
        }
        return backDataMap;
    }

    /**
     * 根据锁ID查询充电宝ID
     *
     * @param reqPara chargingBoxNo:充电箱的编号
     *                lockID:锁ID
     * @return
     */
    @Override
    public Map<Object, Object> queryPowerBankIDByLockID(Map<String, String> reqPara) {
        String url = BaseUrl + "queryPowerBankIDByLockID";

        //发送请求,并接受返回数据
        String backData = HttpUtil.sendPost(url, reqPara);

        //将返回的数据封装成map
        Map<Object, Object> backDataMap = HttpUtil.strToMap(backData);

        Object code = backDataMap.get("code");//设备返回状态码
        if (!code.equals("1")) {//请求失败
            return null;
        }
        return backDataMap;

    }


    /**
     * 查询充电宝配置文件
     *
     * @param reqPara chargingBoxNo:充电箱的编号
     * @return
     */
    @Override
    public Map<Object, Object> queryChargingBoxConfigFile(Map<String, String> reqPara) {

        String url = BaseUrl + "queryChargingBoxConfigFile";

        //发送请求,并接受返回数据
        String backData = HttpUtil.sendPost(url, reqPara);

        //将返回的数据封装成map
        Map<Object, Object> backDataMap = HttpUtil.strToMap(backData);

        Object code = backDataMap.get("code");//设备返回状态码
        if (!code.equals("1")) {//请求失败
            return null;
        }
        return backDataMap;
    }


    /**
     * 更新充电宝配置文件
     *
     * @param reqPara chargingBoxNo:充电箱的编号
     *                serverIp:服务器IP
     *                serverPort:服务器端口号
     *                heartbeatInterval:心跳间隔时间
     * @return
     */
    @Override
    public Map<Object, Object> updateChargingBoxConfigFile(Map<String, String> reqPara) {
        String url = BaseUrl + "updateChargingBoxConfigFile";

        //发送请求,并接受返回数据
        String backData = HttpUtil.sendPost(url, reqPara);

        //将返回的数据封装成map
        Map<Object, Object> backDataMap = HttpUtil.strToMap(backData);

        Object code = backDataMap.get("code");//设备返回状态码
        if (!code.equals("1")) {//请求失败
            return null;
        }
        return backDataMap;
    }


    /**
     * 操作喇叭
     *
     * @param reqPara chargingBoxNo:充电箱的编号
     *                type:1表示打开，0表示关闭
     * @return
     */
    @Override
    public Map<Object, Object> Loudspeaker(Map<String, String> reqPara) {
        String url = BaseUrl + "Loudspeaker";

        //发送请求,并接受返回数据
        String backData = HttpUtil.sendPost(url, reqPara);

        //将返回的数据封装成map
        Map<Object, Object> backDataMap = HttpUtil.strToMap(backData);

        Object code = backDataMap.get("code");//设备返回状态码
        if (!code.equals("1")) {//请求失败
            return null;
        }
        return backDataMap;
    }

    /**
     * 设备OTP升级
     *
     * @param reqPara chargingBoxNo:充电箱的编号
     *                config:配置信息{
     *                serverIp:服务器IP
     *                serverPort:服务器端口
     *                fileName:文件名
     *                username:用户名
     *                password:密码
     *                }
     * @return
     */
    @Override
    public Map<Object, Object> upgradeDevice(Map<String, String> reqPara) {
        String url = BaseUrl + "upgradeDevice";

        //发送请求,并接受返回数据
        String backData = HttpUtil.sendPost(url, reqPara);

        //将返回的数据封装成map
        Map<Object, Object> backDataMap = HttpUtil.strToMap(backData);

        Object code = backDataMap.get("code");//设备返回状态码
        if (!code.equals("1")) {//请求失败
            return null;
        }
        return backDataMap;
    }

    /**
     * 根据锁ID开锁
     *
     * @param reqPara chargingBox:{
     *                chargingBoxNo:充电箱的编号
     *                lockID":锁ID
     *                }
     * @return
     */
    @Override
    public Map<Object, Object> openLockByLockID(Map<String, String> reqPara) {
        String url = BaseUrl + "openLockByLockID";

        //发送请求,并接受返回数据
        String backData = HttpUtil.sendPost(url, reqPara);

        //将返回的数据封装成map
        Map<Object, Object> backDataMap = HttpUtil.strToMap(backData);

        Object code = backDataMap.get("code");//设备返回状态码
        if (!code.equals("1")) {//请求失败
            return null;
        }
        return backDataMap;
    }

    /**
     * 查询SIM卡信息
     *
     * @param reqPara chargingBoxNo:充电箱的编号
     * @return
     */
    @Override
    public Map<Object, Object> querySIM(Map<String, String> reqPara) {
        String url = BaseUrl + "querySIM";

        //发送请求,并接受返回数据
        String backData = HttpUtil.sendPost(url, reqPara);

        //将返回的数据封装成map
        Map<Object, Object> backDataMap = HttpUtil.strToMap(backData);

        Object code = backDataMap.get("code");//设备返回状态码
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
