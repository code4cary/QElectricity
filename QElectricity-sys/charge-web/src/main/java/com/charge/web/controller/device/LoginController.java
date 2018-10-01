package com.charge.web.controller.device;

import com.charge.dao.mapper.device.ChargingBoxMapper;
import com.charge.entity.model.CommonOutputDO;
import com.charge.entity.po.device.ChargingBox;
import com.charge.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by vincent on 01/10/2018.
 */
@Slf4j
@RestController
@RequestMapping("device/")
public class LoginController extends BaseController{

    @Autowired
    private ChargingBoxMapper chargingBoxMapper;

    /**
     * 充电箱登录接口
     *
     * @param queryData chargingBoxNo:充电箱的编号
     *                  chargingBoxVersion:充电箱固件版本
     * @return
     * @json {"chargingBox":{"chargingBoxNo":"","chargingBoxVersion":""}}
     */
    @RequestMapping(value = "login")
    public CommonOutputDO<Object> login(Map<String, Map<String, String>> queryData) {
        if (!validateParam(queryData))  {
            return returnFailed(null, "参数为空异常");
        }

        log.info("充电箱登录后台系统...");
        //获取参数
        String chargingBoxNo = queryData.get("chargingBox").get("chargingBoxNo");
        String chargingBoxVersion = queryData.get("chargingBox").get("chargingBoxVersion");

        //判断数据库是否已经存在该充电箱的记录
        ChargingBox chargingBox = chargingBoxMapper.findIsExistChargingBox(chargingBoxNo);

        if (chargingBox != null) {//充电箱在数据库有记录
            //更新数据数据库
            chargingBox.setSoftwareVersion(chargingBoxVersion);
            chargingBoxMapper.updateByPrimaryKeySelective(chargingBox);
        } else {//充电箱在数据库无记录
            chargingBox.setSoftwareVersion(chargingBoxVersion);
            chargingBox.setCanBorrowNum(chargingBoxNo);
            //为充电箱插入一条记录
            chargingBoxMapper.insertSelective(chargingBox);
        }

        log.info("over...");
        return returnSuccess(null);
    }

    private boolean validateParam(Map<String, Map<String, String>> queryData) {
        if (StringUtils.isEmpty(queryData) ||
                StringUtils.isEmpty(queryData.get("chargingBox").get("chargingBoxNo")) ||
                StringUtils.isEmpty(queryData.get("chargingBox").get("chargingBoxVersion"))) {
            return false;
        }
        return true;
    }
}
