package com.charge.dao.mapper.base;

import java.io.Serializable;

/**
 * Created by vincent on 14/09/2018.
 */

/**
 * @since: JDK1.8
 * @description:通用Mapper抽取
 */
public interface BaseMapper<T,ID extends Serializable> {

    /**
     * 根据主键删除数据库的记录
     * @param id 主键
     * @return
     */
    int deleteByPrimaryKey(ID id);

    /**
     * 新写入数据库记录
     * @param record
     * @return
     */
    int insert(T record);

    /**
     * 动态字段,写入数据库记录
     * @param record
     * @return
     */
    int insertSelective(T record);

    /**
     * 根据指定主键获取一条数据库记录
     * @param id
     * @return
     */
    T selectByPrimaryKey(ID id);

    /**
     * 动态字段,根据主键来更新符合条件的数据库记录
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * 根据主键来更新符合条件的数据库记录
     * @param record
     * @return
     */
    int updateByPrimaryKey(T record);

}
