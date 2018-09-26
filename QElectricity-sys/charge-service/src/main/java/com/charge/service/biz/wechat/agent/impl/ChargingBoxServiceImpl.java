package com.charge.service.biz.wechat.agent.impl;

import com.charge.dao.mapper.device.ChargingBoxMapper;
import com.charge.entity.po.back.wechat.agent.DeviceManage;
import com.charge.entity.po.device.ChargingBox;
import com.charge.service.biz.base.impl.BaseServiceImpl;
import com.charge.service.biz.wechat.agent.ChargingBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @param queryDataMap
     * @return
     */
    @Override
    public List<DeviceManage> findDeviceManageInfo(Map<String, String> queryDataMap) {
        List<DeviceManage> deviceManageList = chargingBoxMapper.findDeviceManageInfo(queryDataMap);

        return deviceManageList;
    }
}
