package com.charge.common.enums;

/**
 * Created by vincent on 18/09/2018.
 *
 * @description:前端返回状态码和信息
 */
public enum StatusInfo {

    FailInfo0("0","请求失败"),
    FailInfo1("1","请求失败,没有参数"),
    FailInfo2("1","请求失败,用户不存在"),
    FailInfo3("1","请求失败,密码错误"),
    SuccessInfo1("1", "请求成功"),
    StatusInfo2("10002","缺少必要字段"),
    StatusInfo3("10003","报文格式错误"),
    StatusInfo4("10004","登陆异常"),

    StatusInfo6666("6666","权限系统异常") ,
    StatusInfo7777("7777","没有权限") ,
    StatusInfo9999("9999","系统错误") ,
    StatusInfo5555("5555","用户验证失败") ,
    StatusInfo8888("8888","认证失败") ,
    StatusInfo4444("4444","日期格式错误");

    private String code;

    private String msg;


    StatusInfo(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }



}
