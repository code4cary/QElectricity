package com.charge.service.biz.device;

import java.util.Map;

/**
 * Created by vincent on 29/09/2018.
 */
public interface SysToSocket {

    Map<Object, Object> borrowChargingBox(String chargingBoxNo);

    Map<Object, Object> rebootDevice(Map<String, String> reqPara);

    Map<Object, Object> queryPowerBankAmount(Map<String, String> reqPara);

    Map<Object, Object> queryPowerBankDetail(Map<String, String> reqPara);

    Map<Object, Object> queryPowerBankIDByLockID(Map<String, String> reqPara);

    Map<Object, Object> queryChargingBoxConfigFile(Map<String, String> reqPara);

    Map<Object, Object> updateChargingBoxConfigFile(Map<String, String> reqPara);

    Map<Object, Object> Loudspeaker(Map<String, String> reqPara);

    Map<Object, Object> upgradeDevice(Map<String, String> reqPara);

    Map<Object, Object> openLockByLockID(Map<String, String> reqPara);

    Map<Object, Object> querySIM(Map<String, String> reqPara);

}
