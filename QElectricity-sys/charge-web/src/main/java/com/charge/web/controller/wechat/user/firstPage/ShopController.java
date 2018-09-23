package com.charge.web.controller.wechat.user.firstPage;

import com.charge.common.enums.StatusInfo;
import com.charge.common.back.wechat.user.ShopInfoBack;
import com.charge.service.biz.wechat.user.firstPage.ShopService;
import com.charge.web.utils.CommonDataReturnUtil;
import com.charge.web.utils.DistanceHelperUtil;
import com.charge.web.utils.PositionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 17/09/2018.
 */
@RestController
@RequestMapping("wechat/user/firstPage/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    //目前查询距离用户50km范围内的商户
    @Value("${distance}")
    private Double distance;

    //每次查询增加的距离
    @Value("${addDistance}")
    private Double addDistance;

    //查询距离范围的的上限
    @Value("${thresholdDistance}")
    private Double thresholdDistance;

    //@ResponseBody
    @RequestMapping
    public Map getShopInfo(@RequestBody Map queryData) {
        //如果传入的参数不符合要求
        if (queryData == null || queryData.isEmpty()) CommonDataReturnUtil.requestFail(StatusInfo.FailInfo1);

        //解析前端的userPosition请求参数
        List userPositionList = (List) queryData.get("userPosition");

        //获取用户当前所在位置的的经度
        Double userLongitude = Double.parseDouble((String) userPositionList.get(0));
        //获取用户当前所在位置的维度
        Double userLatitude = Double.parseDouble((String) userPositionList.get(1));

        //解析前端的searchData请求参数
        String searchDataValue = (String) queryData.get("searchData");

        List<ShopInfoBack> shopInfoList = null;
        do {
            PositionModel positionModel = DistanceHelperUtil.FindNeighPosition(userLongitude, userLatitude, distance);
            distance += addDistance;
            //将最大最小经纬度信息装进map,避免server层依赖web层的PositionModel
            Map<String, Double> positionMap = new HashMap<>();
            positionMap.put("MinLng", positionModel.getMinLng());
            positionMap.put("MaxLng", positionModel.getMaxLng());
            positionMap.put("MinLat", positionModel.getMinLat());
            positionMap.put("MaxLat", positionModel.getMaxLat());

            //对附近商家进行模糊匹配
            shopInfoList = shopService.findShopBySearch(positionMap, searchDataValue);
        } while ((shopInfoList == null || shopInfoList.isEmpty()) && distance <= thresholdDistance);

        //根据用户的经纬度和商户的经纬度计算用户和商户的距离
        shopInfoList.forEach(shopInfo -> {
            Integer distanceUser2Shop = DistanceHelperUtil.GetDistance(Double.valueOf(shopInfo.getLatitude()),
                    Double.valueOf(shopInfo.getLongitude()), userLatitude, userLongitude);
            shopInfo.setDistance(String.valueOf(distanceUser2Shop));
        });

        //将shopList中商户按离用户最近->最远的顺序排序
        Collections.sort(shopInfoList, (s1, s2) -> Double.valueOf(s1.getDistance()).compareTo(Double.valueOf(s2.getDistance())));

        Map shop = CommonDataReturnUtil.requestSuccess(StatusInfo.SuccessInfo1, "shop", "shopInfo", shopInfoList);

        return shop;

    }
}
