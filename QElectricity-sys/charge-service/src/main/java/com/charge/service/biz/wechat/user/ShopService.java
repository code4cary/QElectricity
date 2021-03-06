package com.charge.service.biz.wechat.user;

import com.charge.entity.po.back.wechat.agent.ShopManage;
import com.charge.entity.po.back.wechat.user.ShopInfoBack;
import com.charge.entity.po.wechat.agent.Shop;
import com.charge.service.biz.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 20/09/2018.
 */
public interface ShopService extends BaseService<Shop, Integer> {


    List<ShopInfoBack> findShopBySearch(Map positionMap, String searchData);

    int updateContractPhoto(String agentId,String contractPhoto);

    List<ShopManage> findShopManageInfo(Map<String, String> queryDataMap);

    Shop findShopInfoByShopId(String shopId);
}
