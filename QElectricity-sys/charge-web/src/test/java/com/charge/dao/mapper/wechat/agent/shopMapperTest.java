package com.charge.dao.mapper.wechat.agent;

import com.charge.ChargeApplication;
import com.charge.common.pojo.ShopInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 18/09/2018.
 */

/**
 * 接口测试方法
 * 测试shopMapper
 * List<ShopInfo> findShopByCoordinates(Map<String, Double> map);
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChargeApplication.class)
public class shopMapperTest {

    @Autowired
    private ShopMapper shopMapper;

    @Test
    public void findShopByCoordinatesTest() {

        Map<String, Double> map = new HashMap<>();
        map.put("MinLng", 113.00);
        map.put("MaxLng", 114.00);
        map.put("MinLat", 22.00);
        map.put("MaxLat", 24.00);
        System.out.println(map);

        List<ShopInfo> shopInfoList = shopMapper.findShopByCoordinates(map);

        System.out.println("---------------");
        shopInfoList.forEach(shopInfo -> System.out.println(shopInfo));

    }

}


