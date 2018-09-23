package com.charge.service.biz.wechat.user;

import com.charge.common.back.wechat.user.ShopInfoBack;
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

    public List<ShopInfoBack> findShopByCoordinates(Map<String, Double> map);
}
