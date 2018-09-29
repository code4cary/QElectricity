package com.charge.service.biz.wechat.user.impl;

import com.charge.entity.po.back.wechat.user.TransactionDetailBack;
import com.charge.dao.mapper.wechat.user.WaterBillsMapper;
import com.charge.entity.po.wechat.user.WaterBills;
import com.charge.service.biz.base.impl.BaseServiceImpl;
import com.charge.service.biz.wechat.user.WaterBillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by vincent on 22/09/2018.
 */
@Service
public class WaterBillsServiceImpl extends BaseServiceImpl<WaterBills, Integer> implements WaterBillsService {
    @Autowired
    private WaterBillsMapper waterBillsMapper;

    @PostConstruct
    @Override
    public void initBaseMapper() {
        setBaseMapper(waterBillsMapper);
    }


    /**
     * 根据skey查询交易明细
     * 需要查询的数据:
     *      1:recordNo: 记录编号,即交易号.跟订单号挂钩
     *      2:type:0:表示消费;1:表示充值;2:提现  即流水类型
     *      3:transactionSource: 交易源
     *      4:transactionTime: 交易时间
     *      5:transactionAmount: 交易金额
     *
     * @param skey
     * @return
     */
    @Override
    public List<TransactionDetailBack> findTransactionDetail(String skey) {

        List<TransactionDetailBack>  transactionDetailList =  waterBillsMapper.findTransactionDetailBySkey(skey);
        return transactionDetailList;
    }
}
