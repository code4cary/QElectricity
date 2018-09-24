package com.charge.web.exception.handle;

import com.charge.common.enums.StatusInfo;
import com.charge.web.utils.CommonDataReturnUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Slf4j
@ControllerAdvice
public class QEExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public Map dealGlobalException(Exception ex){
        log.error("系统异常 = 【{ }】",ex.getMessage());
        ex.printStackTrace();
        return CommonDataReturnUtil.requestFail(StatusInfo.StatusInfo9999);
    }

//    @ExceptionHandler
//    @ResponseBody
//    public Map dateFormatException(Exception ex) {
//        log.error("前端传输的字符串日期格式不对..." + ex.getMessage());
//        ex.printStackTrace();
//        return CommonDataReturnUtil.requestFail(StatusInfo.StatusInfo4444);
//    }


}
