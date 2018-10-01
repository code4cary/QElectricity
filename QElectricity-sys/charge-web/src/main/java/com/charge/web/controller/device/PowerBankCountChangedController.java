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
public class PowerBankCountChangedController extends BaseController{

    @Autowired
    private ChargingBoxMapper chargingBoxMapper;

    /**
     * 充电箱充电宝状态变化主动上报接口
     *
     * @param queryData chargingBoxNo:充电箱的编号
     *                  canBorrowNum:用户可借的充电宝数量
     *                  canBackNum:用户可还的充电宝数量
     * @return
     * @json {"chargingBox":{"chargingBoxNo":"","canBorrowNum":"","canBackNum":""}}
     */
    @RequestMapping(value = "powerBankCountChanged")
    public CommonOutputDO<Object> powerBankCountChanged(Map<String, Map<String, String>> queryData) {

        if (!validateParam(queryData))  {
            return returnFailed(null, "参数为空异常");
        }

        log.info("充电箱充电宝状态变化主动上报接口...");

        String chargingBoxNo = queryData.get("chargingBox").get("chargingBoxNo");
        ChargingBox chargingBox = chargingBoxMapper.findChargingBoxByChargingBoxNo(chargingBoxNo);
        if (chargingBox == null || chargingBox.getId() == null) {
            return returnFailed(null,"没有该商户存在...");
        }

        chargingBox.setCanBorrowNum(queryData.get("chargingBox").get("canBorrowNum"));
        chargingBox.setCanBackNum(queryData.get("chargingBox").get("canBackNum"));
        int isUpdate = chargingBoxMapper.updateByPrimaryKeySelective(chargingBox);

        log.info("over...");
        return isUpdate>0?returnSuccess(null):returnFailed(null,"更新数据库失败...");
    }


    private boolean validateParam(Map<String, Map<String, String>> queryData) {
        if (StringUtils.isEmpty(queryData) ||
                StringUtils.isEmpty(queryData.get("chargingBox").get("chargingBoxNo")) ||
                StringUtils.isEmpty(queryData.get("chargingBox").get("canBorrowNum")) ||
                StringUtils.isEmpty(queryData.get("chargingBox").get("canBackNum"))) {
            return false;
        }
        return true;
    }
}
