package com.charge.web.controller.wechat.agent.firstPage.shopManage;

import com.charge.entity.model.CommonOutputDO;
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
 * Created by vincent on 17/09/2018.
 */
@Slf4j
@RestController
@RequestMapping("wechat/agent/firstPage/shopManage/deleteShop")
public class DeleteShopController extends BaseController{

    @Autowired
    private ShopService shopService;

    @RequestMapping
    public CommonOutputDO<Object> deleteShop(@RequestBody(required = true) Map<String, String> queryData) {

        if (!validateParam(queryData)) {
            return returnFailed(null, "参属为空异常");
        }
        log.info("代理商删除商户...");

        //删除商户
        int deleteRow = shopService.deleteByPrimaryKey(Integer.valueOf(queryData.get("shopID")));

        log.info("over");
        return deleteRow>0?returnSuccess(null):
                returnFailed(null,"删除商户失败");
    }

    private boolean validateParam(Map<String, String> queryData) {
        if (StringUtils.isEmpty(queryData.get("agentID")) ||
                StringUtils.isEmpty(queryData.get("shopID"))){
            return false;
        }
        return true;
    }
}
