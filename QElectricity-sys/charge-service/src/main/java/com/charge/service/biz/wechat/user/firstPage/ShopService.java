package com.charge.service.biz.wechat.user.firstPage;

import com.charge.common.back.wechat.user.ShopInfoBack;
import com.charge.entity.po.wechat.agent.Shop;
import com.charge.service.biz.base.BaseService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 20/09/2018.
 */
public interface ShopService extends BaseService<Shop, Integer> {


    List<ShopInfoBack> findShopBySearch(Map positionMap, String searchData);

    int updateContractPhoto(String agentId,String contractPhoto);

}
