package com.charge.web.controller.wechat.user.firstPage;

import com.charge.common.enums.StatusInfo;
import com.charge.service.biz.wechat.user.UserService;
import com.charge.web.utils.CommonDataReturnUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by vincent on 17/09/2018.
 */
@Slf4j
@RestController
@RequestMapping("wechat/user/firstPage/personalCenter")
public class personalCenterController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public Map getPersonCenterInfo(@RequestBody(required = true) Map<String,String> skeyMap) {

        if (skeyMap == null || skeyMap.isEmpty())  return CommonDataReturnUtil.requestFail(StatusInfo.FailInfo1);

        log.info("查询用个人中心页信息...");

        //获得skey
        String skey = skeyMap.get("skey");

        //直接查询数据库该skey对应的用户的信息.如果前端能传来skey,说明skey肯定没过期,所以数据库存的skey也没有更新
        Map<String,String> personInfo = userService.findUserInfoBySkey(skey);

        Map personCenter = CommonDataReturnUtil.requestSuccess(StatusInfo.SuccessInfo1, "personalCenter", "personalInfo", personInfo);

        log.info("over");

        return personCenter;
    }

}
