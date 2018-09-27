package com.charge.entity.po.wechat.agent;

/**
 * Created by vincent on 27/09/2018.
 */
public class MyAccount {

    //可提现金额
    private String withdrawalAmountCan;

    //已提现金额(元)
    private String withdrawalAmountDone;

    //直营总收入
    private String totalIncomeDirect;

    //子代理分成
    private String subAgentSharing;

    //提现次数(月/次),为了给提现数据显示,所以就提前返回到小程序
    private String withdrawNum;

    //冻结资金,为了给提现数据显示,所以就提前返回到小程序
    private String freezingFunds;

    public String getWithdrawalAmountCan() {
        return withdrawalAmountCan;
    }

    public void setWithdrawalAmountCan(String withdrawalAmountCan) {
        this.withdrawalAmountCan = withdrawalAmountCan;
    }

    public String getWithdrawalAmountDone() {
        return withdrawalAmountDone;
    }

    public void setWithdrawalAmountDone(String withdrawalAmountDone) {
        this.withdrawalAmountDone = withdrawalAmountDone;
    }

    public String getTotalIncomeDirect() {
        return totalIncomeDirect;
    }

    public void setTotalIncomeDirect(String totalIncomeDirect) {
        this.totalIncomeDirect = totalIncomeDirect;
    }

    public String getSubAgentSharing() {
        return subAgentSharing;
    }

    public void setSubAgentSharing(String subAgentSharing) {
        this.subAgentSharing = subAgentSharing;
    }

    public String getWithdrawNum() {
        return withdrawNum;
    }

    public void setWithdrawNum(String withdrawNum) {
        this.withdrawNum = withdrawNum;
    }

    public String getFreezingFunds() {
        return freezingFunds;
    }

    public void setFreezingFunds(String freezingFunds) {
        this.freezingFunds = freezingFunds;
    }

    @Override
    public String toString() {
        return "MyAccount{" +
                "withdrawalAmountCan='" + withdrawalAmountCan + '\'' +
                ", withdrawalAmountDone='" + withdrawalAmountDone + '\'' +
                ", totalIncomeDirect='" + totalIncomeDirect + '\'' +
                ", subAgentSharing='" + subAgentSharing + '\'' +
                ", withdrawNum='" + withdrawNum + '\'' +
                ", freezingFunds='" + freezingFunds + '\'' +
                '}';
    }
}
