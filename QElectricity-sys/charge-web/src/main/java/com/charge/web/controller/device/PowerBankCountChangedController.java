package com.charge.web.controller.device;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by vincent on 01/10/2018.
 */
@Slf4j
@RestController
@RequestMapping("device/")
public class PowerBankCountChangedController {

    /**
     * 充电箱充电宝状态变化主动上报接口
     *
     * @param data chargingBoxNo:充电箱的编号
     *             canBorrowNum:用户可借的充电宝数量
     *             canBackNum:用户可还的充电宝数量
     * @return
     */
    @RequestMapping(value = "powerBankCountChanged")
    public Map powerBankCountChanged(Map data) {
        return null;
    }
}
