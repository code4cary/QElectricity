package com.charge.dao.mapper.device;

import com.charge.dao.mapper.base.BaseMapper;
import com.charge.entity.po.device.PriceTypeCB;
import org.apache.ibatis.annotations.Param;

/**
 * 由MyBatis Generator工具自动生成，请不要手动修改
 */
public interface PriceTypeCBMapper extends BaseMapper<PriceTypeCB, Integer> {


    PriceTypeCB findPriceTypeCBByCbId(@Param("cbId") String cb_id);
}