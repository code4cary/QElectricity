package com.charge.dao.mapper.wechat.agent;

import com.charge.common.pojo.ShopInfo;
import com.charge.dao.mapper.base.BaseMapper;
import com.charge.entity.po.wechat.agent.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 由MyBatis Generator工具自动生成，请不要手动修改
 */
public interface ShopMapper extends BaseMapper<Shop, Integer> {

    List<ShopInfo> findShopByCoordinates(@Param("map") Map<String, Double> map);

    List<ShopInfo> findShopBySearch(@Param("map") Map positionMap,@Param("searchData") String searchDataValue);
}