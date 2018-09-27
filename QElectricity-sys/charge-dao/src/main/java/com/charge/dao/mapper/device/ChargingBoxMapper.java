package com.charge.dao.mapper.device;

import com.charge.dao.mapper.base.BaseMapper;
import com.charge.entity.po.back.wechat.agent.DeviceManage;
import com.charge.entity.po.back.wechat.agent.DevicePop;
import com.charge.entity.po.back.wechat.agent.EarlyWarning;
import com.charge.entity.po.device.ChargingBox;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 由MyBatis Generator工具自动生成，请不要手动修改
 */
public interface ChargingBoxMapper extends BaseMapper<ChargingBox, Integer> {


    List<ChargingBox> findChargingBoxByAgentId(@Param("id_num") String id_num);

    List<EarlyWarning> findEarlyWarning(@Param("queryDataMap") Map<String, String> queryDataMap);

    List<DeviceManage> findDeviceManageInfo(@Param("queryDataMap") Map<String, String> queryDataMap);

    int updateAgentInfo(@Param("queryDataMap") Map<String, String> queryDataMap);

    DevicePop findDevicePopInfo(@Param("queryDataMap") Map<String, String> queryDataMap);


    int updateDeviceSID(@Param("queryDataMap") Map<String, String> queryDataMap);
}