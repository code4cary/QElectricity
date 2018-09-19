package com.charge.web.controller.wechat.user;

import com.charge.common.enums.StatusInfo;
import com.charge.common.pojo.ShopInfo;
import com.charge.service.biz.wechat.user.FirstPageService;
import com.charge.web.utils.CommonDataReturnUtil;
import com.charge.web.utils.DistanceHelperUtil;
import com.charge.web.utils.PositionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by vincent on 17/09/2018.
 */

@Controller
@RestController
@RequestMapping("wechat/user/firstPage")
public class FirstPageController {

    @Autowired
    private FirstPageService firstPageService;

    //目前查询距离用户50km范围内的商户
    private static final Double distance = 50.00;

    @RequestMapping("/test")
    public String home(HttpServletRequest request) {
        String name = "sb";
        System.out.println(request.getAttributeNames());
        return "傻屌 " + name;
    }

    // post模式下，使用@RequestBody 绑定请求对象，
    // Spring会帮你进行协议转换，将Json、Xml协议转换成你需要的对象。
    @ResponseBody
    @RequestMapping
    public Map getFirstPageInfo(@RequestBody Map<String, ArrayList<String>> userPosition) {//{"userPosition":["userLongitude"，"userLatitude"]}

        System.out.println("userPosition= " + userPosition.get("userPosition"));

        ArrayList userPosition1 = userPosition.get("userPosition");
        System.out.println(userPosition1.get(0));
        System.out.println(userPosition1.get(1));

        //如果传入的参数不符合要求
        if (userPosition == null || userPosition.isEmpty()) CommonDataReturnUtil.requestFail(StatusInfo.FailInfo0);

        System.out.println("userPosition" + userPosition.get("userPosition"));

        //获取用户当前所在位置的的经度
        Double userLongitude = Double.parseDouble((String)userPosition1.get(0));
        //获取用户当前所在位置的维度
        Double userLatitude =  Double.parseDouble((String)userPosition1.get(1));

        System.out.println("userLongitude= " + userLongitude);
        System.out.println("userLatitude= " + userLatitude);


        //获取离当前用户50km范围的最大最小经纬度,
        PositionModel positionModel = DistanceHelperUtil.FindNeighPosition(userLongitude, userLatitude, distance);
        //将最大最小经纬度信息装进map,避免server层依赖web层的PositionModel
        Map<String, Double> positionMap = new HashMap<>();
        positionMap.put("MinLng", positionModel.getMinLng());
        positionMap.put("MaxLng", positionModel.getMaxLng());
        positionMap.put("MinLat", positionModel.getMinLat());
        positionMap.put("MaxLat", positionModel.getMaxLat());

        //根据经纬度关系查询用户附近的商户
        List<ShopInfo> shopList = firstPageService.findShopByCoordinates(positionMap);

        //根据用户的经纬度和商户的经纬度计算用户和商户的距离,并按最近->最远的顺序排序
        shopList.forEach(shopInfo -> {
            Double distanceUser2Shop = DistanceHelperUtil.GetDistance(Double.valueOf(shopInfo.getLatitude()),
                    Double.valueOf(shopInfo.getLongitude()), userLatitude, userLongitude);
            shopInfo.setDistance(String.valueOf(distanceUser2Shop));
        });

        //将shopList中商户按离用户最近->最远的顺序排序
        Collections.sort(shopList, (s1, s2) -> Double.valueOf(s1.getDistance()).compareTo(Double.valueOf(s2.getDistance())));

        //将数据包装起来返回
        Map firstPage = CommonDataReturnUtil.requestSuccess(StatusInfo.SuccessInfo1, "firstPage", "shopInfo", shopList);

        return firstPage;

    }
}

