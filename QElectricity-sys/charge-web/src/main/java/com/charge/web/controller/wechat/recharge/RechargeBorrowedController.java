package com.charge.web.controller.wechat.recharge;

import com.charge.entity.model.CommonOutputDO;
import com.charge.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by vincent on 30/09/2018.
 */
@Slf4j
@RestController
@RequestMapping("wechat/recharge/rechargeBorrowed")
public class RechargeBorrowedController extends BaseController{

    @RequestMapping
    public CommonOutputDO<Object> rechargeBorrowed(@RequestBody Map<String, String> queryData) {

        if (!validateParam(queryData)) {
            return returnFailed(null, "参属为空异常");
        }
        log.info("用户归还完充电宝后进行付费...");




        log.info("查询用户是否归还充电宝...");
        return null;
    }

    private boolean validateParam(Map<String, String> queryData) {
        if (StringUtils.isEmpty(queryData.get("skey"))) {
            return false;
        }
        return true;
    }
}
