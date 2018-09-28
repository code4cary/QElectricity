package com.charge.entity.po.back.wechat.agent;

import java.io.Serializable;

/**
 * Created by vincent on 28/09/2018.
 */
public class WithdrawalRecord implements Serializable {

    //提现订单编号
    private String withdrawalNo;

    //具体时间,该时间为发起提现的时间
    private String withdrawalTime;

    //提现金额
    private String withdrawalAmount;

    //提现状态
    private String withdrawalStatus;

    //收款方式,为了给用户点击状态栏后进入的页面的数据显示,提前返回给小程序端
    String withdrawalType;

    public String getWithdrawalNo() {
        return withdrawalNo;
    }

    public void setWithdrawalNo(String withdrawalNo) {
        this.withdrawalNo = withdrawalNo;
    }

    public String getWithdrawalTime() {
        return withdrawalTime;
    }

    public void setWithdrawalTime(String withdrawalTime) {
        this.withdrawalTime = withdrawalTime;
    }

    public String getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(String withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public String getWithdrawalStatus() {
        return withdrawalStatus;
    }

    public void setWithdrawalStatus(String withdrawalStatus) {
        this.withdrawalStatus = withdrawalStatus;
    }

    public String getWithdrawalType() {
        return withdrawalType;
    }

    public void setWithdrawalType(String withdrawalType) {
        this.withdrawalType = withdrawalType;
    }


    @Override
    public String toString() {
        return "WithdrawalRecord{" +
                "withdrawalNo='" + withdrawalNo + '\'' +
                ", withdrawalTime=" + withdrawalTime +
                ", withdrawalAmount='" + withdrawalAmount + '\'' +
                ", withdrawalStatus='" + withdrawalStatus + '\'' +
                ", withdrawalType='" + withdrawalType + '\'' +
                '}';
    }
}
