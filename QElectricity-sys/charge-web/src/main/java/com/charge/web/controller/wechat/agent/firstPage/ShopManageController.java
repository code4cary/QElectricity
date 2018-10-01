package com.charge.web.controller.wechat.agent.firstPage;

import com.charge.entity.model.CommonOutputDO;
import com.charge.entity.po.back.wechat.agent.ShopManage;
import com.charge.service.biz.wechat.user.ShopService;
import com.charge.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 17/09/2018.
 */
@Slf4j
@RestController
@RequestMapping("wechat/agent/firstPage/shopManage")
public class ShopManageController extends BaseController {

    @Autowired
    private ShopService shopService;

    @RequestMapping
    public CommonOutputDO<Object> getShopManageInfo(@RequestBody(required = true) Map<String, String> queryData) {
        if (!validateParam(queryData)) {
            return returnFailed(null, "参属为空异常");
        }
        log.info("查询代理商商户管理页信息...");

        //获取agentId
        String agentId = queryData.get("agentID");

        //获取searchData
        String searchData = queryData.get("searchData");

        //获取类型:充电箱还是充电线:1代表充电线,0代表充电宝
        String type = queryData.get("type");

        //封装查询条件到map
        Map<String, String> queryDataMap = new HashMap<>();
        queryDataMap.put("agentId", agentId);
        queryDataMap.put("searchData", searchData);
        queryDataMap.put("type", type);

        //查询代理商商户管理页信息
        List<ShopManage> shopManageList =  shopService.findShopManageInfo(queryDataMap);

        log.info("over");
        return returnSuccess(shopManageList);
    }


    private boolean validateParam(Map<String, String> queryData) {
        if (StringUtils.isEmpty(queryData.get("agentID")) ||
                StringUtils.isEmpty(queryData.get("type"))) {
            return false;
        }
        return true;
    }

}
