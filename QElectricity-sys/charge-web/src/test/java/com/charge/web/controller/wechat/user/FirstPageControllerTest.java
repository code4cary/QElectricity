package com.charge.web.controller.wechat.user;

import com.charge.ChargeApplication;
import com.charge.common.pojo.ShopInfo;
import com.charge.service.biz.wechat.user.FirstPageService;
import com.charge.web.utils.DistanceHelperUtil;
import com.charge.web.utils.PositionModel;
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
 * Created by vincent on 19/09/2018.
 */

/**
 * 接口测试方法
 * 测试FirstPageController
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChargeApplication.class)
public class FirstPageControllerTest {
    @Autowired
    private FirstPageService firstPageService;

    //
    public static void main(String... args) {

    }

    @Test
    public void testGetFirstPageInfo() throws Exception {

        Double userLongitude = 113.836378;

        Double userLatitude = 22.631872;

        Double distance =  50.0;

        //获取离当前用户50km范围的最大最小经纬度
        PositionModel positionModel = DistanceHelperUtil.FindNeighPosition(userLongitude, userLatitude, distance);

        //将最大最小经纬度信息装进map,避免server层依赖web层的PositionModel
        Map<String, Double> positionMap = new HashMap<>();
        positionMap.put("MinLng", positionModel.getMinLng());
        positionMap.put("MaxLng", positionModel.getMaxLng());
        positionMap.put("MinLat", positionModel.getMinLat());
        positionMap.put("MaxLat", positionModel.getMaxLat());

        System.out.println(positionMap);
        System.out.println("-----------------------------------------------");

        //根据经纬度关系查询用户附近的商户
        List<ShopInfo> shopList = firstPageService.findShopByCoordinates(positionMap);
        shopList.forEach(shopInfo -> System.out.println(shopInfo));


        //根据用户的经纬度和商户的经纬度计算用户和商户的距离,并按最近->最远的顺序排序
        shopList.forEach(shopInfo -> {
            Integer distanceUser2Shop = DistanceHelperUtil.GetDistance(Double.valueOf(shopInfo.getLatitude()),
                    Double.valueOf(shopInfo.getLongitude()), userLatitude,userLongitude);

            shopInfo.setDistance(String.valueOf(distanceUser2Shop));

        });

        //将shopList中商户按离用户最近->最远的顺序排序
        Collections.sort(shopList, (s1, s2) -> Double.valueOf(s1.getDistance()).compareTo(Double.valueOf(s2.getDistance())));


        System.out.println("-----------------------------------------------");
        shopList.forEach(shopInfo -> System.out.println(shopInfo.getDistance()));

    }
}