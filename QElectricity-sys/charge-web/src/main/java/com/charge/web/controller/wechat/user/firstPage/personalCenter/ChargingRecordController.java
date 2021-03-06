package com.charge.web.controller.wechat.user.firstPage.personalCenter;

import com.charge.common.enums.StatusInfo;
import com.charge.entity.po.back.wechat.user.ChargingRecordBack;
import com.charge.service.biz.wechat.user.UserService;
import com.charge.web.utils.CommonDataReturnUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 17/09/2018.
 */
@Slf4j
@RestController
@RequestMapping("wechat/user/firstPage/personalCenter/chargingRecord")
public class ChargingRecordController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public Map getChargingRecordInfo(@RequestBody(required = true) Map<String, String> skeyMap) {
        if (skeyMap == null || skeyMap.isEmpty()) return CommonDataReturnUtil.requestFail(StatusInfo.FailInfo1);

        log.info("查询用户充电记录页信息...");

        //获得skey
        String skey = skeyMap.get("skey");

        //查询用户充电记录
        List<ChargingRecordBack> userChargingRecord = userService.findUserChargingRecordBySkey(skey);

        Map chargingRecord = CommonDataReturnUtil.requestSuccess(StatusInfo.SuccessInfo1, "chargingRecordPage", "chargingRecord", userChargingRecord);

        log.info("over");
        return chargingRecord;
    }
}
