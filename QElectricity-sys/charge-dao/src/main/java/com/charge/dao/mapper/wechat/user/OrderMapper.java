package com.charge.dao.mapper.wechat.user;

import com.charge.dao.mapper.base.BaseMapper;
import com.charge.entity.po.wechat.user.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 由MyBatis Generator工具自动生成，请不要手动修改
 */
public interface OrderMapper extends BaseMapper<Order, Integer> {
    String findPbIdById(@Param("id") int id);

    List<Order> findUserOrderRecordBySkey(@Param("skey") String skey);
}