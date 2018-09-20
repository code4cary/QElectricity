package com.charge.service.biz.wechat.user.firstPage;

import com.charge.common.pojo.ShopInfo;
import com.charge.entity.po.wechat.agent.Shop;
import com.charge.service.biz.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 20/09/2018.
 */
public interface ShopService extends BaseService<Shop, Integer> {


    List<ShopInfo> findShopBySearch(Map positionMap, String searchData);
}
