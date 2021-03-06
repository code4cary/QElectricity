package com.charge.dao.mapper.wechat.agent;

import com.charge.entity.po.back.wechat.agent.ShopManage;
import com.charge.entity.po.back.wechat.user.ShopInfoBack;
import com.charge.dao.mapper.base.BaseMapper;
import com.charge.entity.po.wechat.agent.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 由MyBatis Generator工具自动生成，请不要手动修改
 */
public interface ShopMapper extends BaseMapper<Shop, Integer> {
    List<ShopInfoBack> findShopByCoordinates(@Param("map") Map<String, Double> map);

    List<ShopInfoBack> findShopBySearch(@Param("map") Map positionMap, @Param("searchData") String searchDataValue);

    int updateContractPhoto(String agentId, String contractPhoto);

    List<Shop> findShopByAgentId(@Param("id_num") String id_num);

    List<ShopManage> findShopManageInfo(@Param("queryDataMap") Map<String, String> queryDataMap);

    Shop findShopAddress(@Param("boxCustomerId") String boxCustomerId);

    Shop findShopByChargingBoxNO(@Param("boxCustomerId") String boxCustomerId);
}