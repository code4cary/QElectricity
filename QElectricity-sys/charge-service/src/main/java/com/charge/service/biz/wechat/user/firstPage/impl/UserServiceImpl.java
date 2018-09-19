package com.charge.service.biz.wechat.user.firstPage.impl;

import com.charge.dao.mapper.wechat.user.UserMapper;
import com.charge.entity.po.wechat.user.User;
import com.charge.service.biz.base.impl.BaseServiceImpl;
import com.charge.service.biz.wechat.user.firstPage.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @since: JDK1.7
 * @description:用户信息操作
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * @des:注入之后执行初始化方法通用mapper注入
     * @param:
     * @return：
     * @exception:
     */
    @PostConstruct
    @Override
    public void initBaseMapper() {

        setBaseMapper(userMapper);
    }

    @Cacheable(value = "catCache")
    @Override
    public User findUserByUserName(String userName) {
        User user = userMapper.findUserByUserName(userName);
        return user;
    }

    /**
     * 根据用户openId查询用户
     *
     * @param openId
     * @return
     */
    @Override
    public User findUserByOpenId(String openId) {

        return userMapper.findUserByOpenId(openId);
    }

    @Override
    public void updateSkeyById(Integer Id, String skey) {
        userMapper.updateSkeyById(Id, skey);
    }

    @Override
    public void updateSkeyByOpenId(String openId, String skey) {
        userMapper.updateSkeyByOpenId(openId, skey);
    }
}
