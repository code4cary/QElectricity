package com.charge.entity.po.back.wechat.agent;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 25/09/2018.
 */
public class IncomeData implements Serializable{

    //类型 BOX=0或LINE=1
    private String type;

    //总收益
    private String totalIncome;
    //总分成
    private String totalSharing;
    //租借收益
    private String rentIncome;
    //99收益
    private String _99Income;
    //子代理商收益
    private String subAgentIncome;
    //每天分成
    private List<Map<String, Object>> daySharing;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(String totalIncome) {
        this.totalIncome = totalIncome;
    }

    public String getTotalSharing() {
        return totalSharing;
    }

    public void setTotalSharing(String totalSharing) {
        this.totalSharing = totalSharing;
    }

    public String getRentIncome() {
        return rentIncome;
    }

    public void setRentIncome(String rentIncome) {
        this.rentIncome = rentIncome;
    }

    public String get_99Income() {
        return _99Income;
    }

    public void set_99Income(String _99Income) {
        this._99Income = _99Income;
    }

    public String getSubAgentIncome() {
        return subAgentIncome;
    }

    public void setSubAgentIncome(String subAgentIncome) {
        this.subAgentIncome = subAgentIncome;
    }

    public List<Map<String, Object>> getDaySharing() {
        return daySharing;
    }

    public void setDaySharing(List<Map<String, Object>> daySharing) {
        this.daySharing = daySharing;
    }

    @Override
    public String toString() {
        return "IncomeData{" +
                "type='" + type + '\'' +
                ", totalIncome='" + totalIncome + '\'' +
                ", totalSharing='" + totalSharing + '\'' +
                ", rentIncome='" + rentIncome + '\'' +
                ", _99Income='" + _99Income + '\'' +
                ", subAgentIncome='" + subAgentIncome + '\'' +
                ", daySharing=" + daySharing +
                '}';
    }
}
