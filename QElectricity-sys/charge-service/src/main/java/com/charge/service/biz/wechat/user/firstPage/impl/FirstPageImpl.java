package com.charge.service.biz.wechat.user.firstPage.impl;

import com.charge.common.pojo.ShopInfo;
import com.charge.dao.mapper.wechat.agent.ShopMapper;
import com.charge.entity.po.wechat.agent.Shop;
import com.charge.entity.po.wechat.user.User;
import com.charge.service.biz.base.impl.BaseServiceImpl;
import com.charge.service.biz.wechat.user.firstPage.FirstPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 18/09/2018.
 */
@Service
public class FirstPageImpl extends BaseServiceImpl<Shop, Integer> implements FirstPageService {

    @Autowired
    ShopMapper shopMapper;


    @PostConstruct
    @Override
    public void initBaseMapper() {
        setBaseMapper(shopMapper);
    }

    /**
     *
     * @param shopNo
     * @return
     */
    @Override
    public User findByShopNo(String shopNo) {

        return null;
    }

    /**
     * 查询经纬度符合小于最大经纬度,大于最小经纬度的商家
     */
    @Override
    public List<ShopInfo> findShopByCoordinates(Map<String, Double> map) {
        //符合经纬度范围的商户,只能查到SHOP_PHOTO,NAME,BUSINESS_TIME,ADDRESS,SHOP_STATUS,CONTRACT_PERSON_NAME信息
        List<ShopInfo> shopInfoList = shopMapper.findShopByCoordinates(map);

        //向device查询符合当前经纬度范围的商户充电箱可借和可还的充电宝数量



        //查询该商户名下充电箱的可借充电宝,可还充电宝数量
        return shopInfoList;
    }


}
