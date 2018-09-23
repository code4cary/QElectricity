package com.charge.dao.mapper.device;

import com.charge.dao.mapper.base.BaseMapper;
import com.charge.entity.po.device.Malfunction;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 由MyBatis Generator工具自动生成，请不要手动修改
 */
public interface MalfunctionMapper extends BaseMapper<Malfunction, Integer> {
    void insertMalRecord(@Param("malMap")Map malMap);
}