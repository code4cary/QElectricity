package com.charge.service.biz.device.impl;

import com.charge.dao.mapper.device.PowerBankMapper;
import com.charge.entity.po.device.PowerBank;
import com.charge.service.biz.base.impl.BaseServiceImpl;
import com.charge.service.biz.device.PowerBankService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by vincent on 01/10/2018.
 */
public class PowerBankServiceImpl extends BaseServiceImpl<PowerBank, Integer> implements PowerBankService {

    @Autowired
    private PowerBankMapper powerBankMapper;

    @Override
    public void initBaseMapper() {
        setBaseMapper(powerBankMapper);
    }

    /**
     * 通过lockID更新充电宝锁位状态
     * @param lockID
     * @return
     */
    @Override
    public int updateLockStatusByLockID(String lockID) {
        int updateRow = powerBankMapper.updateLockStatusByLockID(lockID);
        return updateRow;
    }
}
