package com.charge.service.biz.wechat.agent.impl;

import com.charge.dao.mapper.device.PowerBankMapper;
import com.charge.entity.po.device.PowerBank;
import com.charge.service.biz.base.impl.BaseServiceImpl;
import com.charge.service.biz.wechat.agent.PowerBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by vincent on 01/10/2018.
 */
@Service
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
