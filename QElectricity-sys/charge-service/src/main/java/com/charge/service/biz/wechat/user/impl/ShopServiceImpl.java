package com.charge.service.biz.wechat.user.impl;

import com.charge.dao.mapper.device.ChargingBoxMapper;
import com.charge.dao.mapper.wechat.agent.ShopMapper;
import com.charge.entity.po.back.wechat.agent.ShopManage;
import com.charge.entity.po.back.wechat.user.ShopInfoBack;
import com.charge.entity.po.device.ChargingBox;
import com.charge.entity.po.wechat.agent.Shop;
import com.charge.service.biz.base.impl.BaseServiceImpl;
import com.charge.service.biz.wechat.user.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 20/09/2018.
 */
@Service
public class ShopServiceImpl extends BaseServiceImpl<Shop, Integer> implements ShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private ChargingBoxMapper chargingBoxMapper;


    @PostConstruct
    @Override
    public void initBaseMapper() {

        setBaseMapper(shopMapper);
    }


    /**
     * 根据搜索内容对附近商家进行模糊匹配
     * 搜索内容匹配字段:NAME,ADDRESS,SHOP_STATUS
     *
     * @param positionMap
     * @param searchData
     * @return
     */
    @Override
    public List<ShopInfoBack> findShopBySearch(Map positionMap, String searchData) {

        List<ShopInfoBack> shopInfoList = shopMapper.findShopBySearch(positionMap, searchData);

        //查询数据库充电宝的可借可还数量,并将可借数量最大的充电宝的可借可还数据封装进shopInfo
        shopInfoList.forEach(shopInfo -> {

            String shopId = shopInfo.getShopId();

            //查询商户名下的充电宝编号,一个商户名下可能存在多个充电宝
            List<String> chargingBoxNoList = chargingBoxMapper.findChargingBoxNO(shopId);

            //查询充电宝的可借可还数量
            Integer canBorrowNumMax = 0;
            Integer canBackNum = 0;
            for (String chargingBoxNo : chargingBoxNoList) {
                ChargingBox chargingBox = chargingBoxMapper.findBorrowBackNum(chargingBoxNo);
                System.out.println(chargingBox);
                int canBorrowNum = Integer.valueOf(chargingBox.getCanBorrowNum());
                //这里用等于的原因是可借的数量可能会为0
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

    @Override
    public int updateContractPhoto(String agentId, String contractPhoto) {
        return shopMapper.updateContractPhoto(agentId, contractPhoto);
    }


    /**
     * 通过agentId,searchData,type获取商户管理页信息
     *
     * @param queryDataMap
     * @return
     */
    @Override
    public List<ShopManage> findShopManageInfo(Map<String, String> queryDataMap) {
        List<ShopManage> shopManageList = shopMapper.findShopManageInfo(queryDataMap);
        return shopManageList;
    }


}
