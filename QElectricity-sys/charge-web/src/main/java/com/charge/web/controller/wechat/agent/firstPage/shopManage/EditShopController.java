package com.charge.web.controller.wechat.agent.firstPage.shopManage;

import com.charge.entity.model.CommonOutputDO;
import com.charge.entity.po.wechat.agent.Shop;
import com.charge.service.biz.wechat.user.ShopService;
import com.charge.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by vincent on 01/10/2018.
 */
@Slf4j
@RestController
@RequestMapping("wechat/agent/firstPage/shopManage/editShop")
public class EditShopController extends BaseController {

    @Autowired
    private ShopService shopService;

    @RequestMapping
    public CommonOutputDO<Object> editShop(@RequestBody(required = true) Map<String, String> queryData) throws Exception {
        if (!validateParam(queryData)) {
            returnFailed(null, "没有商户id");
        }
        log.info("修改商户信息之大页面接口...");

        Shop shop = shopService.findShopInfoByShopId(queryData.get("shopID"));

        log.info("over...");
        return shop == null?returnFailed(null,"没有该商户信息"):returnSuccess(shop);
    }

    private boolean validateParam(Map<String, String> queryData) {
        if (StringUtils.isEmpty(queryData.get("agentID")) ||
                StringUtils.isEmpty(queryData.get("shopID"))
                ) {
            return false;
        }
        return true;
    }
}



