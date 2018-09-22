package com.charge.web.controller.wechat.user.firstPage.personalCenter.myWallet;

import com.alibaba.fastjson.JSON;
import com.charge.common.enums.StatusInfo;
import com.charge.common.pojo.TransactionDetail;
import com.charge.service.biz.wechat.user.firstPage.personalCenter.myWallet.WaterBillsService;
import com.charge.web.utils.CommonDataReturnUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 22/09/2018.
 */
@RestController
@RequestMapping("wechat/user/firstPage/personalCenter/myWallet/transactionDetail")
public class TransactionDetailControllerTest {

    @Autowired
    private WaterBillsService waterBillsService;

    //
    public static void main(String... args) {

    }

    @Test
    public void testGetTransactionDetail() throws Exception {

        String skey = "4zROpnglsYcPyJR0cgTTYg!!";
        List<TransactionDetail> transactionDetailList =
                waterBillsService.findTransactionDetail(skey);

        Map transactionDetail =
                CommonDataReturnUtil.requestSuccess(StatusInfo.SuccessInfo1, "transactionDetailPage", "transactionDetail", transactionDetailList);

        Object json = JSON.toJSON(transactionDetail);
        System.out.println(json);
    }
}