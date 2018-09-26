package com.charge.entity.po.back.wechat.agent;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vincent on 26/09/2018.
 */
public class DevicePop implements Serializable {


//        //充电宝编号
//        "powerBankNO" : "",
//            //对应的位置编号
//            "window" : ""

    //版本号
    private String version;
    //箱子状态
    private String deviceStatus;
    //绑定商户
    private String shopName;
    //可弹出的充电宝锁位id对应充电宝在充电箱里放置的窗口号:窗口号1-10
    private List<String> windowNo;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<String> getWindowNo() {
        return windowNo;
    }

    public void setWindowNo(List<String> windowNo) {
        this.windowNo = windowNo;
    }

    @Override
    public String toString() {
        return "DevicePop{" +
                "version='" + version + '\'' +
                ", deviceStatus='" + deviceStatus + '\'' +
                ", shopName='" + shopName + '\'' +
                ", windowNo=" + windowNo +
                '}';
    }
}
