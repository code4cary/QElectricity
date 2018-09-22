package com.charge.web.controller.wechat.user.firstPage.personalCenter;

import com.charge.common.enums.StatusInfo;
import com.charge.service.biz.wechat.user.firstPage.UserService;
import com.charge.web.utils.CommonDataReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by vincent on 17/09/2018.
 */

@RestController
@RequestMapping("wechat/user/firstPage/personalCenter/myWallet")
public class MyWalletController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public Map getMyWalletInfo(@RequestBody(required = true) Map<String,String> skeyMap) {
        if (skeyMap == null || skeyMap.isEmpty())  return CommonDataReturnUtil.requestFail(StatusInfo.FailInfo1);

        //获得skey
        String skey = skeyMap.get("skey");

        //直接查询数据库该skey对应的用户的钱包信息.如果前端能传来skey,说明skey肯定没过期,所以数据库存的skey也没有更新
        Map<String,String> userWalletInfo = userService.findUserWalletInfoBySkey(skey);

        Map myWallet = CommonDataReturnUtil.requestSuccess(StatusInfo.SuccessInfo1, "myWalletPage", "myWallet", userWalletInfo);

        return myWallet;
    }
}
