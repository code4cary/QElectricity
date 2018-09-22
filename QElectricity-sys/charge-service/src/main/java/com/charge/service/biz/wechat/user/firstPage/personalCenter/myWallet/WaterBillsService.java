package com.charge.service.biz.wechat.user.firstPage.personalCenter.myWallet;

import com.charge.common.pojo.TransactionDetail;
import com.charge.entity.po.wechat.user.WaterBills;
import com.charge.service.biz.base.BaseService;

import java.util.List;

/**
 * Created by vincent on 22/09/2018.
 */
public interface WaterBillsService extends BaseService<WaterBills, Integer> {
    List<TransactionDetail> findTransactionDetail(String skey);
}
