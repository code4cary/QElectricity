package com.charge.common.enums;

/**
 * @since: JDK1.7
 * @description:前端返回错误码和信息
 */
public enum ErrCode {

  ErrCode1111("1111"),
  ErrCode00("00000","保留错误"),
  ErrCode01("1", "Success"),
  ErrCode02("10002","缺少必要字段"),
  ErrCode03("10003","报文格式错误"),
  ErrCode04("10004","登陆异常"),
  ErrCode6666("6666","权限系统异常") ,
  ErrCode7777("7777","没有权限") ,
  ErrCode9999("9999","系统错误") ,
  ErrCode8888("8888","认证失败") ;


  ErrCode(String ecode, String msg) {
    this.setEcode(ecode);
    this.setEmsg(msg);
  }

  ErrCode(String msg) {
    this.setEmsg(msg);
  }

  private String ecode;

  private String emsg;

  public String getEcode() {
    return ecode;
  }

  public void setEcode(String ecode) {
    this.ecode = ecode;
  }

  public String getEmsg() {
    return emsg;
  }



  public void setEmsg(String emsg) {
    this.emsg = emsg;
  }



}