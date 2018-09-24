package com.charge.dao.mapper.device;

import com.charge.dao.mapper.base.BaseMapper;
import com.charge.entity.po.device.ChargingBox;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 由MyBatis Generator工具自动生成，请不要手动修改
 */
public interface ChargingBoxMapper extends BaseMapper<ChargingBox, Integer> {


    List<ChargingBox> findChargingBoxByAgentId(@Param("id_num") String id_num);
}