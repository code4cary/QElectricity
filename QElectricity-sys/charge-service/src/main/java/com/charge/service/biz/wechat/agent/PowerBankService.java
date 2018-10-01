package com.charge.service.biz.wechat.agent;

import com.charge.entity.po.device.PowerBank;
import com.charge.service.biz.base.BaseService;

/**
 * Created by vincent on 01/10/2018.
 */
public interface PowerBankService extends BaseService<PowerBank,Integer> {
    int updateLockStatusByLockID(String lockID);
}
