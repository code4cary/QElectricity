package com.charge.service.biz.wechat.user.firstPage.personalCenter.myWallet;

import com.charge.entity.po.back.wechat.user.TransactionDetailBack;
import com.charge.entity.po.wechat.user.WaterBills;
import com.charge.service.biz.base.BaseService;

import java.util.List;

/**
 * Created by vincent on 22/09/2018.
 */
public interface WaterBillsService extends BaseService<WaterBills, Integer> {
    List<TransactionDetailBack> findTransactionDetail(String skey);
}
