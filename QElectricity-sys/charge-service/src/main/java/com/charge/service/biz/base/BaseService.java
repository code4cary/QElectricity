package com.charge.service.biz.base;

import java.io.Serializable;

/**
 * @since: JDK1.7
 * @description:通用service接口抽取
 */
public interface BaseService<T,ID extends Serializable> {

    int deleteByPrimaryKey(ID id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(ID id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

}
