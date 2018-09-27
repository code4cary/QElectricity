package com.charge.service.biz.wechat.user.firstPage.impl;

import com.charge.entity.po.back.wechat.agent.ShopManage;
import com.charge.entity.po.back.wechat.user.ShopInfoBack;
import com.charge.dao.mapper.wechat.agent.ShopMapper;
import com.charge.entity.po.wechat.agent.Shop;
import com.charge.service.biz.base.impl.BaseServiceImpl;
import com.charge.service.biz.wechat.user.firstPage.ShopService;
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
    ShopMapper shopMapper;

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

        //向device查询符合当前经纬度范围的商户名下的充电箱可借和可还的充电宝数量
        //首次向数据库查询当前商户名下的设备的编号

        //再想device查询其当前可借和可还的充电宝数量

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
