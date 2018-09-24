package com.charge.web.controller.wechat.user;

import com.charge.entity.po.back.wechat.user.ShopInfoBack;
import com.charge.common.enums.StatusInfo;
import com.charge.service.biz.wechat.user.FirstPageService;
import com.charge.web.utils.CommonDataReturnUtil;
import com.charge.web.utils.DistanceHelperUtil;
import com.charge.web.utils.PositionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by vincent on 17/09/2018.
 */

//@RestController是@Controller和@ResponseBody的结合体，两个标注合并起来的作用。
@RestController
@PropertySource(value = "classpath:prop.properties")
@RequestMapping("wechat/user/firstPage")
public class FirstPageUserController {

    @Autowired
    private FirstPageService firstPageService;

    //目前查询距离用户50km范围内的商户
    @Value("${distance}")
    private Double distance;

    //每次查询增加的距离
    @Value("${addDistance}")
    private Double addDistance;

    //查询距离范围的的上限
    @Value("${thresholdDistance}")
    private Double thresholdDistance;

    // post模式下，使用@RequestBody 绑定请求对象，
    // Spring会帮你进行协议转换，将Json、Xml协议转换成你需要的对象。
    @RequestMapping
    public Map getFirstPageInfo(@RequestBody Map<String, List<String>> userPosition) {//{"userPosition":["userLongitude"，"userLatitude"]}


        //如果传入的参数不符合要求
        if (userPosition == null || userPosition.isEmpty()) CommonDataReturnUtil.requestFail(StatusInfo.FailInfo1);

        // ArrayList userPositionList = userPosition.get("userPosition");
        List userPositionList = userPosition.get("userPosition");

        //获取用户当前所在位置的的经度
        Double userLongitude = Double.parseDouble((String) userPositionList.get(0));
        //获取用户当前所在位置的维度
        Double userLatitude = Double.parseDouble((String) userPositionList.get(1));

        //获取离当前用户50km范围的最大最小经纬度,
        List<ShopInfoBack> shopList = null;
        do {
            PositionModel positionModel = DistanceHelperUtil.FindNeighPosition(userLongitude, userLatitude, distance);
            distance += addDistance;
            //将最大最小经纬度信息装进map,避免server层依赖web层的PositionModel
            Map<String, Double> positionMap = new HashMap<>();
            positionMap.put("MinLng", positionModel.getMinLng());
            positionMap.put("MaxLng", positionModel.getMaxLng());
            positionMap.put("MinLat", positionModel.getMinLat());
            positionMap.put("MaxLat", positionModel.getMaxLat());

            //根据经纬度关系查询用户附近的商户
            shopList = firstPageService.findShopByCoordinates(positionMap);
        } while ((shopList == null || shopList.isEmpty()) && distance <= thresholdDistance);//如果50千米范围内没有商户,那么将distance增大50,继续去数据库查询.直到设定的上限

        //根据用户的经纬度和商户的经纬度计算用户和商户的距离
        shopList.forEach(shopInfo -> {
            Integer distanceUser2Shop = DistanceHelperUtil.GetDistance(Double.valueOf(shopInfo.getLatitude()),
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

