package com.charge.dao.mapper.device;

import com.charge.dao.mapper.base.BaseMapper;
import com.charge.entity.po.device.ChargingBox;
import org.apache.ibatis.annotations.Param;

/**
 * 由MyBatis Generator工具自动生成，请不要手动修改
 */
public interface ChargingBoxMapper extends BaseMapper<ChargingBox, Integer> {


    String findPtIdById(@Param("id") String chargingBoxId);
}