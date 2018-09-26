package com.charge.service.biz.wechat.agent.impl;

import com.charge.dao.mapper.device.ChargingBoxMapper;
import com.charge.entity.po.back.wechat.agent.DeviceManage;
import com.charge.entity.po.back.wechat.agent.DevicePop;
import com.charge.entity.po.device.ChargingBox;
import com.charge.service.biz.base.impl.BaseServiceImpl;
import com.charge.service.biz.wechat.agent.ChargingBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 26/09/2018.
 */
@Service
public class ChargingBoxServiceImpl extends BaseServiceImpl<ChargingBox, Integer> implements ChargingBoxService {

    @Autowired
    private ChargingBoxMapper chargingBoxMapper;

    @Override
    public void initBaseMapper() {
        setBaseMapper(chargingBoxMapper);
    }

    /**
     * 通过agentId,statusRange,type,searchData查询代理商指定搜索内容,指定日期的订单记录
     *
     * @param queryDataMap
     * @return
     */
    @Override
    public List<DeviceManage> findDeviceManageInfo(Map<String, String> queryDataMap) {
        List<DeviceManage> deviceManageList = chargingBoxMapper.findDeviceManageInfo(queryDataMap);

        return deviceManageList;
    }

    /**
     * 通过agentId,type,operateType,deviceNO进行设备管理页操作:解绑或重启
     *
     * @param queryDataMap
     * @return
     */
    @Override
    public Boolean operateChargingBoxDeviceManagePage(Map<String, String> queryDataMap) {
        //获取操作类型
        String operateType = queryDataMap.get("operateType");
        //获取设备编号
        String deviceNO = queryDataMap.get("deviceNO");

        boolean isOperateSuccess = false;
        if (operateType.equals("0")) {//解绑操作
            int row = chargingBoxMapper.updateAgentInfo(queryDataMap);
            System.out.println(row);
            isOperateSuccess = row > 0 ? true : false;
        } else if (operateType.equals("1")) {//重启操作
            //向曾斌那边发起请求 对设备进行重启
            //如果收到曾斌那边返回成功的消息
            //充电箱是否有相关信息进行更新??? 有变化曾斌那边会自动请求接口告诉后台

        }

        return isOperateSuccess;

    }

    /**
     * 根据agentId,deviceNO查询代理商设备弹出页信息
     *
     * @param queryDataMap
     * @return
     */
    @Override
    public DevicePop findDevicePopInfo(Map<String, String> queryDataMap) {

        DevicePop devicePopInfo = chargingBoxMapper.findDevicePopInfo(queryDataMap);

        //请求设备中介(曾斌)该充电箱可弹出的窗口号为多少

        List<String> windowList = new ArrayList<>();
        //测试数据
        windowList.add("1");
        windowList.add("3");
        windowList.add("5");
        windowList.add("7");
        windowList.add("9");
        devicePopInfo.setWindowNo(windowList);

        return devicePopInfo;
    }

    /**
     * 根据agentId,operateType,deviceNO,windowNO操作设备弹出页操作:弹出或重启
     *
     * @param queryDataMap
     * @return
     */
    @Override
    public Boolean operateChargingBoxDevicePopPage(Map<String, Object> queryDataMap) {
        //获取操作类型 0:pop,1:reboot
        String operateType = (String) queryDataMap.get("operateType");

        Boolean isOperateSuccess = false;
        if (operateType.equals("0")){//弹出充电宝
            //请求设备中介弹出设备
            //获取需要弹出的充电宝的窗口号
            List<String> windowNo = (List<String>) queryDataMap.get("windowNo");
            //获取设备号
            String deviceNO = (String) queryDataMap.get("deviceNO");
            //将deviceNO,windowNo传入相应请求接口



        } else if (operateType.equals("1")){//重启设备
            //向曾斌那边发起请求 对设备进行重启

            //测试数据
            isOperateSuccess = true;
        }


        return isOperateSuccess;
    }
}
