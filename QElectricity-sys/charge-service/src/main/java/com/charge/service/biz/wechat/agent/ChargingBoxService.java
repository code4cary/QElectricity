package com.charge.service.biz.wechat.agent;

import com.charge.entity.po.back.wechat.agent.DeviceManage;
import com.charge.entity.po.back.wechat.agent.DevicePop;
import com.charge.entity.po.device.ChargingBox;
import com.charge.service.biz.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 26/09/2018.
 */
public interface ChargingBoxService extends BaseService<ChargingBox,Integer> {
    List<DeviceManage> findDeviceManageInfo(Map<String, String> queryDataMap);

    Boolean operateChargingBoxDeviceManagePage(Map<String, String> queryDataMap);

    Boolean operateChargingBoxDevicePopPage(Map<String, Object> queryDataMap);

    DevicePop findDevicePopInfo(Map<String, String> queryDataMap);

    Boolean AddDevice(Map<String, String> queryData);
}
