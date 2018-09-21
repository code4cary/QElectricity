package com.charge.web.controller.wechat.user.firstPage.personalCenter;

import com.charge.common.enums.StatusInfo;
import com.charge.common.pojo.ChargingRecord;
import com.charge.service.biz.wechat.user.firstPage.UserService;
import com.charge.web.utils.CommonDataReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 17/09/2018.
 */

@RestController
@RequestMapping("wechat/user/firstPage/personalCenter/chargingRecord")
public class ChargingRecordController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public Map getChargingRecordInfo(@RequestBody(required = true) Map<String, String> skeyMap) {
        if (skeyMap == null || skeyMap.isEmpty()) return CommonDataReturnUtil.requestFail(StatusInfo.FailInfo1);

        //获得skey
        String skey = skeyMap.get("skey");

        //查询用户充电记录
        List<ChargingRecord> userChargingRecord = userService.findUserChargingRecordBySkey(skey);

        Map chargingRecord = CommonDataReturnUtil.requestSuccess(StatusInfo.SuccessInfo1, "personalCenter", "personalInfo", userChargingRecord);

        return chargingRecord;
    }
}
