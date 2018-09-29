package com.charge.service.biz.wechat.user.firstPage;

import com.charge.entity.po.back.wechat.user.ChargingRecordBack;
import com.charge.entity.po.wechat.user.User;
import com.charge.service.biz.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 10/09/2018.
 *
 * @description:用户操作业务层
 */
public interface UserService extends BaseService<User, Integer> {

    User findUserByUserName(String userName);

    User findUserByOpenId(String openId);

    void updateSkeyById(Integer Id,String skey);

    void updateSkeyByOpenId(String openId,String skey);

    Map<String,String> findUserInfoBySkey(String skey);

    Map<String,String> findUserWalletInfoBySkey(String skey);

    List<ChargingRecordBack> findUserChargingRecordBySkey(String skey);

    String findUserOpenIdBySkey(String skey);

}