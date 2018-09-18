package com.charge.entity.model;


import com.charge.common.enums.ErrCode;

/**
 * @since: JDK1.7
 * @description:封装前端返回实体
 */
public class ResponseDO<T> {
    private String responseCode;
    private String message;
    private T data;

    public ResponseDO(String responseCode, String message, T data) {
        this.responseCode = responseCode;
        this.message = message;
        this.data = data;
    }

    public ResponseDO() {
    }

    public ResponseDO(ErrCode errCode){
        this.responseCode = errCode.getEcode();
        this.message = errCode.getEmsg();
    }

    public String getResponseCode() {
        return this.responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
