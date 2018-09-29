package com.charge.service.biz.wechat.user.impl;

import com.charge.dao.mapper.device.MalfunctionMapper;
import com.charge.entity.po.device.Malfunction;
import com.charge.service.biz.base.impl.BaseServiceImpl;
import com.charge.service.biz.wechat.user.MalfunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class MalfunctionServiceImpl extends BaseServiceImpl<Malfunction,Integer> implements MalfunctionService {

    @Autowired
    MalfunctionMapper malfunctionMapper;

    @PostConstruct
    @Override
    public void initBaseMapper() {
        setBaseMapper(malfunctionMapper);
    }
}
