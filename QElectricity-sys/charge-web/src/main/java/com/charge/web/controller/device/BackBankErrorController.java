package com.charge.web.controller.device;

import com.charge.entity.model.CommonOutputDO;
import com.charge.service.biz.wechat.agent.PowerBankService;
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
public class BackBankErrorController extends BaseController {

    @Autowired
    private PowerBankService powerBankService;

    /**
     * 充电箱锁位归还失败接口
     *
     * @param queryData chargingBoxNo:充电箱的编号
     *                  lockID:归还失败的锁ID
     * @return
     * @json {"chargingBox":{"chargingBoxNo":"","lockID":""}}
     */
    @RequestMapping(value = "backBankError")
    public CommonOutputDO<Object> backBankError(Map<String, Map<String, String>> queryData) {
        if (!validateParam(queryData)) {
            return returnFailed(null, "参数为空异常");
        }
        log.info("充电宝锁位归还失败信息...");

        //更新充电宝的锁位状态为0,以及充电宝锁位操作状态0
        int updateRow = powerBankService.updateLockStatusByLockID(queryData.get("chargingBox").get("lockID"));

        log.info("over...");
        return updateRow > 0?returnSuccess(null):returnFailed(null,"充电宝锁位状态更新失败");
    }


    private boolean validateParam(Map<String, Map<String, String>> queryData) {
        if (StringUtils.isEmpty(queryData.get("chargingBox")) ||
                StringUtils.isEmpty(queryData.get("chargingBox").get("chargingBoxNo")) ||
                StringUtils.isEmpty(queryData.get("chargingBox").get("lockID"))) {
            return false;
        }
        return true;
    }
}
