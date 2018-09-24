package com.charge.web.controller.base;


import com.charge.common.enums.StatusInfo;
import com.charge.entity.model.CommonOutputDO;

/**
 * @since: JDK1.7
 * @description: 抽取通用controller
 */
public abstract class BaseController {

    public <T> CommonOutputDO returnSuccess(T t){
        CommonOutputDO<T> commonOutputDO = new CommonOutputDO<T>(StatusInfo.SuccessInfo1);
        commonOutputDO.setFlag("1");
        commonOutputDO.setData(t);
        return commonOutputDO;
    }

    public <T> CommonOutputDO returnFailed(T t,String msg){
        CommonOutputDO<T> commonOutputDO = new CommonOutputDO<>(StatusInfo.FailInfo0);
        commonOutputDO.setFlag("0");
        commonOutputDO.setMsg(msg);
        commonOutputDO.setData(t);
        return commonOutputDO;
    }



}
