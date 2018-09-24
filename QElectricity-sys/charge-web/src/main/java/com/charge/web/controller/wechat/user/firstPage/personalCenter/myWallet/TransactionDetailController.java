package com.charge.web.controller.wechat.user.firstPage.personalCenter.myWallet;

import com.charge.common.enums.StatusInfo;
import com.charge.entity.po.back.wechat.user.TransactionDetailBack;
import com.charge.service.biz.wechat.user.firstPage.personalCenter.myWallet.WaterBillsService;
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
@RequestMapping("wechat/user/firstPage/personalCenter/myWallet/transactionDetail")
public class TransactionDetailController {

    @Autowired
    private WaterBillsService waterBillsService;

    @RequestMapping
    public Map getTransactionDetail(@RequestBody Map<String, String> queryData) {
        //如果传入的参数不符合要求
        if (queryData == null || queryData.isEmpty()) CommonDataReturnUtil.requestFail(StatusInfo.FailInfo1);

        //获取请求参数skey
        String skey = queryData.get("skey");

        //查询交易明细,主要在流水表里查询,关联其他表再查询一些字段
        List<TransactionDetailBack> transactionDetailList =
                waterBillsService.findTransactionDetail(skey);

        Map transactionDetail =
                CommonDataReturnUtil.requestSuccess(StatusInfo.SuccessInfo1, "transactionDetailPage", "transactionDetail", transactionDetailList);
        return transactionDetail;
    }
}
