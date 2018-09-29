package com.charge.service.biz.wechat.user.impl;

import com.charge.dao.mapper.device.ChargingBoxMapper;
import com.charge.dao.mapper.wechat.agent.ShopMapper;
import com.charge.entity.po.back.wechat.user.ShopInfoBack;
import com.charge.entity.po.device.ChargingBox;
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
public class FirstPageServiceImpl extends BaseServiceImpl<Shop, Integer> implements FirstPageService {

    @Autowired
    ShopMapper shopMapper;

    @Autowired
    private ChargingBoxMapper chargingBoxMapper;

    @PostConstruct
    @Override
    public void initBaseMapper() {
        setBaseMapper(shopMapper);
    }

    /**
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

        //查询数据库充电宝的可借可还数量,并将可借数量最大的充电宝的可借可还数据封装进shopInfo
        shopInfoList.forEach(shopInfo -> {

            //查询商户名下的充电宝编号
            String shopId = shopInfo.getShopId();

            //查询商户名下的充电宝编号,一个商户名下可能存在多个充电宝
            List<String> chargingBoxNoList = chargingBoxMapper.findChargingBoxNO(shopId);

            //查询充电宝的可借可还数量
            int canBorrowNumMax = 0;
            int canBackNum = 0;
            for (String chargingBoxNo : chargingBoxNoList) {
                ChargingBox chargingBox = chargingBoxMapper.findBorrowBackNum(chargingBoxNo);
                System.out.println(chargingBox);
                Integer canBorrowNum = Integer.valueOf(chargingBox.getCanBorrowNum());
                if (canBorrowNum >= canBorrowNumMax) {
                    canBorrowNumMax = canBorrowNum;
                    canBackNum = Integer.valueOf(chargingBox.getCanBackNum());
                }
            }
            shopInfo.setCanBorrowNum(String.valueOf(canBorrowNumMax));
            shopInfo.setCanBackNum(String.valueOf(canBackNum));

            //这个数据不需要返回前端
            shopInfo.setShopId(null);
        });


        return shopInfoList;
    }

}
