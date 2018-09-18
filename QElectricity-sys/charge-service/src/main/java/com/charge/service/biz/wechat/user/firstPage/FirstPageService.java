package com.charge.service.biz.wechat.user.firstPage;

import com.charge.common.pojo.ShopInfo;
import com.charge.entity.po.wechat.agent.Shop;
import com.charge.entity.po.wechat.user.User;
import com.charge.service.biz.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 18/09/2018.
 */
public interface FirstPageService extends BaseService<Shop,Integer> {

    public User findByShopNo(String shopNo);

    public List<ShopInfo> findShopByCoordinates(Map<String, Double> map);
}
