package com.charge.service.biz.base.impl;


import com.charge.dao.mapper.base.BaseMapper;
import com.charge.service.biz.base.BaseService;

import java.io.Serializable;

/**
 * @since: JDK1.7
 * @description:抽取通用Service
 */
public abstract class BaseServiceImpl<T,ID extends Serializable> implements BaseService<T,ID> {

    private BaseMapper<T,ID> baseMapper;

    public void setBaseMapper(BaseMapper<T, ID> baseMapper) {
        this.baseMapper = baseMapper;
    }

    public abstract void initBaseMapper();

    @Override
    public int deleteByPrimaryKey(ID id) {
        try {
            return baseMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(T record) {
        try {
            return baseMapper.insert(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insertSelective(T record) {
        try {
            return baseMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T selectByPrimaryKey(ID id) {
        try {
            return baseMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        try {
            return baseMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateByPrimaryKey(T record) {
        try {
            return baseMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
