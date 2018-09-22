package com.charge.common.pojo;

import java.io.Serializable;

/**
 * Created by vincent on 22/09/2018.
 */
public class TransactionDetail implements Serializable{

    //记录编号,即交易号
    private String recordNo;

    //交易类型:0:表示消费;1:表示充值;2:提现
    private String type;

    //交易源
    private String transactionSource;

    //交易时间
    private String transactionTime;

    //交易金额
    private String transactionAmount;

    public String getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(String recordNo) {
        this.recordNo = recordNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTransactionSource() {
        return transactionSource;
    }

    public void setTransactionSource(String transactionSource) {
        this.transactionSource = transactionSource;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    @Override
    public String toString() {
        return "TransactionDetail{" +
                "recordNo='" + recordNo + '\'' +
                ", type='" + type + '\'' +
                ", transactionSource='" + transactionSource + '\'' +
                ", transactionTime='" + transactionTime + '\'' +
                ", transactionAmount='" + transactionAmount + '\'' +
                '}';
    }
}
