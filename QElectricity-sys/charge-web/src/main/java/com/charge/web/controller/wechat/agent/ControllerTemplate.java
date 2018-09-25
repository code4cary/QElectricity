package com.charge.web.controller.wechat.agent;

import com.charge.entity.model.CommonOutputDO;
import com.charge.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by vincent on 25/09/2018.
 */
@Slf4j
@RestController
@RequestMapping("xxxx")
public class ControllerTemplate extends BaseController {

    @RequestMapping
    public CommonOutputDO<Object> xxxx(Map<String, String> queryData) {
        if (!validateParam(queryData)) {
            return returnFailed(null, "参属为空异常");
        }
        log.info("xxx...");




        log.info("over");;
        return returnSuccess(null);
    }


    private boolean validateParam(Map<String, String> queryData) {
        if (StringUtils.isEmpty(queryData.get("xxx")) ||
                StringUtils.isEmpty(queryData.get("xxx")) ||
                StringUtils.isEmpty(queryData.get("xxx"))) {
            return false;
        }
        return true;
    }
}
