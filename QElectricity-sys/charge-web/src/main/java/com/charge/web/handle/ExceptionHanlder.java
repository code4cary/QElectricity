package com.charge.web.handle;

import com.charge.common.enums.ErrCode;
import com.charge.entity.model.ResponseDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @since: JDK1.7
 * @description: 全局异常处理
 */
@Slf4j
@ControllerAdvice(basePackages = "com.charge.web.controller")
public class ExceptionHanlder {
    /**
     * @param
     * @return
     * @throws
     * @see： 处理自定义的异常并返回结果
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    private ResponseDO handleAllException(HttpServletRequest request, HttpServletResponse response, RuntimeException ex){
        log.error("starWisdomSys occurred error msg = {}", ex.getMessage());
        ResponseDO outputDO = new ResponseDO(ErrCode.ErrCode9999);
        return outputDO;
    }

    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseBody
    public ResponseDO handle403(UnauthenticatedException e){
        e.printStackTrace();
        return new ResponseDO(ErrCode.ErrCode8888);
    }

    // 捕捉shiro的异常
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public ResponseDO handle401(ShiroException e) {
        return new ResponseDO(ErrCode.ErrCode6666);
    }

    // 捕捉UnauthorizedException
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseDO handle401() {
        return new ResponseDO(ErrCode.ErrCode8888);
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDO globalException(HttpServletRequest request, Throwable ex) {
        return new ResponseDO(ErrCode.ErrCode9999);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
