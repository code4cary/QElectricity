package com.charge.web.controller.wechat.user.firstPage.personalCenter.myWallet;

import com.alibaba.fastjson.JSON;
import com.charge.ChargeApplication;
import com.charge.common.enums.StatusInfo;
import com.charge.entity.po.back.wechat.user.TransactionDetailBack;
import com.charge.service.biz.wechat.user.firstPage.personalCenter.myWallet.WaterBillsService;
import com.charge.web.utils.CommonDataReturnUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 22/09/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChargeApplication.class)
public class TransactionDetailControllerTest {

    @Autowired
    private WaterBillsService waterBillsService;

    //
    public static void main(String... args) {

    }

    @Test
    public void testGetTransactionDetail() throws Exception {

        String skey = "NZxk/qZSozPf7VEWdfun/Q!!";
        List<TransactionDetailBack> transactionDetailList =
                waterBillsService.findTransactionDetail(skey);

        Map transactionDetail =
                CommonDataReturnUtil.requestSuccess(StatusInfo.SuccessInfo1, "transactionDetailPage", "transactionDetail", transactionDetailList);

        Object json = JSON.toJSON(transactionDetail);
        System.out.println(json);
    }
}