package com.charge.dao.mapper.wechat.user;

import com.charge.dao.mapper.base.BaseMapper;
import com.charge.entity.po.wechat.user.User;
import org.apache.ibatis.annotations.Param;

/**
 * 由MyBatis Generator工具自动生成，请不要手动修改
 */
public interface UserMapper extends BaseMapper<User, Integer> {

    User findUserByUserName(@Param("userName") String userName);
}