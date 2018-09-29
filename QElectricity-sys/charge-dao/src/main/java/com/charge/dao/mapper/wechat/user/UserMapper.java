package com.charge.dao.mapper.wechat.user;

import com.charge.dao.mapper.base.BaseMapper;
import com.charge.entity.po.wechat.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 由MyBatis Generator工具自动生成，请不要手动修改
 */
public interface UserMapper extends BaseMapper<User, Integer> {

    User findUserByUserName(@Param("userName") String userName);

    User findUserByOpenId(@Param("openId") String openId);

    void updateSkeyById(@Param("Id")Integer id,@Param("skey") String skey);

    void updateSkeyByOpenId(@Param("openId")String openId,@Param("skey")String skey);

    Map<String,String> findUserInfoBySkey(@Param("skey") String skey);

    Map<String,String> findUserWalletInfoBySkey(@Param("skey") String skey);

    String findUserOpenIdBySkey(@Param("skey") String skey);

    Integer findUserIdBySkey(@Param("skey") String skey9876543210);
}