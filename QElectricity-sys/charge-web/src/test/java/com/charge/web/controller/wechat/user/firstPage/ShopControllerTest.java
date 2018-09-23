package com.charge.web.controller.wechat.user.firstPage;

import com.charge.ChargeApplication;
import com.charge.common.back.wechat.user.ShopInfoBack;
import com.charge.service.biz.wechat.user.firstPage.ShopService;
import com.charge.web.utils.DistanceHelperUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 20/09/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChargeApplication.class)
public class ShopControllerTest {

    @Autowired
    private ShopService shopService;

    //
    public static void main(String... args) {

    }

    @Test
    public void testGetShopInfo() throws Exception {

        //将最大最小经纬度信息装进map,避免server层依赖web层的PositionModel
        Map<String, Double> positionMap = new HashMap<>();
        positionMap.put("MinLng", 113.83);
        positionMap.put("MaxLng", 113.85);
        positionMap.put("MinLat", 22.62);
        positionMap.put("MaxLat", 22.64);

        //对附近商家进行模糊匹配
        List<ShopInfoBack> shopInfoList = shopService.findShopBySearch(positionMap, "砂锅");
        shopInfoList.forEach(shopInfo -> System.out.println(shopInfo));

        System.out.println("-----------------------------------------");
        //根据用户的经纬度和商户的经纬度计算用户和商户的距离
        shopInfoList.forEach(shopInfo -> {
            Integer distanceUser2Shop = DistanceHelperUtil.GetDistance(Double.valueOf(shopInfo.getLatitude()),
                    Double.valueOf(shopInfo.getLongitude()), 113.84, 22.63);
            shopInfo.setDistance(String.valueOf(distanceUser2Shop));
        });
        shopInfoList.forEach(shopInfo -> System.out.println(shopInfo.getDistance()));
        System.out.println("-----------------------------------------");


        //将shopList中商户按离用户最近->最远的顺序排序
        Collections.sort(shopInfoList, (s1, s2) -> Double.valueOf(s1.getDistance()).compareTo(Double.valueOf(s2.getDistance())));
        shopInfoList.forEach(shopInfo -> System.out.println(shopInfo));

    }
}