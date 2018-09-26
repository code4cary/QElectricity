package com.charge.dao.mapper.wechat.user;

import com.charge.dao.mapper.base.BaseMapper;
import com.charge.entity.po.wechat.user.Order;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 由MyBatis Generator工具自动生成，请不要手动修改
 */
public interface OrderMapper extends BaseMapper<Order, Integer> {

    List<Order> findUserOrderRecordBySkey(@Param("skey") String skey);

    List<Order> findTodayOrdersByAgentId(@Param("id_num") String id_num,
                                         @Param("dateTodayStart") Date dateTodayStart,
                                         @Param("dateTodayEnd") Date dateTodayEnd);

    List<Order> findOrdersByIdAndDate(@Param("shopId") Integer shopId,
                                      @Param("dateStart") Date dateStart,
                                      @Param("dateEnd") Date dateEnd);

    List<Map<String,Object>> findOrderDataNumPerDay(@Param("queryDataMap") Map<String, Object> queryDataMap);

    List<Map<String,Object>> findIncomeDataPerDay(@Param("queryDataMap") Map<String, Object> queryDataMap);

    Double findSubAgentIncomeBySubId(@Param("subQueryDataMap") Map<String, Object> subQueryDataMap);


}