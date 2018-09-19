package com.charge.service.biz.wechat.user.firstPage;

import com.charge.entity.po.wechat.user.User;
import com.charge.service.biz.base.BaseService;

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
}