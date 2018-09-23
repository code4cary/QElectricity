package com.charge.web.controller.wechat.user.firstPage.personalCenter;

import com.alibaba.fastjson.JSON;
import com.charge.ChargeApplication;
import com.charge.common.enums.StatusInfo;
import com.charge.common.back.wechat.user.ChargingRecordBack;
import com.charge.service.biz.wechat.user.firstPage.UserService;
import com.charge.web.utils.CommonDataReturnUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 21/09/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChargeApplication.class)
public class ChargingRecordControllerTest {
    @Autowired
    private UserService userService;


    //
    public static void main(String... args) {

    }

    @Test
    public void testGetChargingRecordInfo() throws Exception {


        String skey = "skey123456";

        //直接查询数据库该skey对应的用户的信息.如果前端能传来skey,说明skey肯定没过期,所以数据库存的skey也没有更新
        List<ChargingRecordBack> chargingRecordList= userService.findUserChargingRecordBySkey(skey);

        Map chargingRecord = CommonDataReturnUtil.requestSuccess(StatusInfo.SuccessInfo1, "chargingRecord", "chargingRecord", chargingRecordList);
        Object json = JSON.toJSON(chargingRecord);
        System.out.println(json);


    }
}