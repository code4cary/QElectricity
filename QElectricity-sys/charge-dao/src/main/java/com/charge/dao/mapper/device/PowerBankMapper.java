package com.charge.dao.mapper.device;

import com.charge.dao.mapper.base.BaseMapper;
import com.charge.entity.po.device.PowerBank;
import org.apache.ibatis.annotations.Param;

/**
 * 由MyBatis Generator工具自动生成，请不要手动修改
 */
public interface PowerBankMapper extends BaseMapper<PowerBank, Integer> {

    String findSNById(@Param("id") String id);

    PowerBank findPowerBank(@Param("powerBankNO") String powerBankNO);

    int updateLockStatusByLockID(@Param("lockID") String lockID);
}