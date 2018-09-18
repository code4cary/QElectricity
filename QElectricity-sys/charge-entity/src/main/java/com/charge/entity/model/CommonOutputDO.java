package com.charge.entity.model;


import com.charge.common.enums.ErrCode;

public class CommonOutputDO<T extends Object> extends BaseDO {

    /**
     *
     */
    private static final long serialVersionUID = 1045623461739984210L;

    private String code;

    private String flag;

    private String msg;

    private T data;

    public CommonOutputDO() {

    }

    public CommonOutputDO(RuntimeException se) {
        this.msg = se.getMessage();
    }

    public CommonOutputDO(ErrCode ec) {
        this.code = ec.getEcode();
        this.msg = ec.getEmsg();
    }

    public CommonOutputDO(String flag,String code,String msg) {
        this.code = code;
        this.msg = msg;
        this.flag = flag;
    }

    public CommonOutputDO(String code, String flag, String msg, T data) {
        this.code = code;
        this.flag = flag;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}