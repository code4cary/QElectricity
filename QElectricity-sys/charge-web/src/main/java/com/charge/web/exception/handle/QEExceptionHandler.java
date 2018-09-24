package com.charge.web.exception.handle;

import com.charge.common.enums.StatusInfo;
import com.charge.web.utils.CommonDataReturnUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.Map;


/**
 * 通过@ControllerAdvice来定义统一的异常处理类，使用@ExceptionHandler来决定哪些异常需要处理
 */
@Slf4j
@ControllerAdvice
public class QEExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public Map dealGlobalException(Exception ex) {
        if (ex instanceof ParseException) {//日期格式转换异常
            log.error("前端传输的字符串日期格式不对..." + ex.getMessage());
            ex.printStackTrace();
            return CommonDataReturnUtil.requestFail(StatusInfo.StatusInfo4444);
        }


        //其他异常
        log.error("系统异常 = 【{ }】", ex.getMessage());
        ex.printStackTrace();
        return CommonDataReturnUtil.requestFail(StatusInfo.StatusInfo9999);
    }

}
