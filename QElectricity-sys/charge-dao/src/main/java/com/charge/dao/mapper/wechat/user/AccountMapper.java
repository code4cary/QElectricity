package com.charge.dao.mapper.wechat.user;

import com.charge.dao.mapper.base.BaseMapper;
import com.charge.entity.po.wechat.user.Account;
import org.apache.ibatis.annotations.Param;

/**
 * 由MyBatis Generator工具自动生成，请不要手动修改
 */
public interface AccountMapper extends BaseMapper<Account, Integer> {
    Integer findAIDByUID(@Param("uid") Integer uid);
}