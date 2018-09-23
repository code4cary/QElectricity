package com.charge.service.biz.wechat.user.impl;

import com.charge.common.back.wechat.user.ShopInfoBack;
import com.charge.common.pojo.ShopInfo;
import com.charge.dao.mapper.wechat.agent.ShopMapper;
import com.charge.entity.po.wechat.agent.Shop;
import com.charge.entity.po.wechat.user.User;
import com.charge.service.biz.base.impl.BaseServiceImpl;
import com.charge.service.biz.wechat.user.FirstPageService;
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
    public List<ShopInfoBack> findShopByCoordinates(Map<String, Double> map) {
        //符合经纬度范围的商户,只能查到SHOP_PHOTO,NAME,BUSINESS_TIME,ADDRESS,SHOP_STATUS,CONTRACT_PERSON_NAM等信息
        List<ShopInfoBack> shopInfoList = shopMapper.findShopByCoordinates(map);

        //向device查询符合当前经纬度范围的商户名下的充电箱可借和可还的充电宝数量
        //首次向数据库查询当前商户名下的设备的编号

        //再想device查询其当前可借和可还的充电宝数量




        return shopInfoList;
    }




}
