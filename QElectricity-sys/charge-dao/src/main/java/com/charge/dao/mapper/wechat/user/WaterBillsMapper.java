package com.charge.dao.mapper.wechat.user;

import com.charge.entity.po.back.wechat.user.TransactionDetailBack;
import com.charge.dao.mapper.base.BaseMapper;
import com.charge.entity.po.wechat.user.WaterBills;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 由MyBatis Generator工具自动生成，请不要手动修改
 */
public interface WaterBillsMapper extends BaseMapper<WaterBills, Integer> {
    List<TransactionDetailBack> findTransactionDetailBySkey(@Param("skey")String skey);
}