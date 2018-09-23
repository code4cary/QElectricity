package com.charge.entity.model.fix;

import java.io.Serializable;


public class DeviceFixDO implements Serializable {

    private String chargingboxno;

    private String reasons;

    private String windowno;

    private String powerbankno;

    private String remark;

    private String photo;

    private String wxOpenid;

    private String skey;

    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey;
    }

    public String getChargingboxno() {
        return chargingboxno;
    }

    public void setChargingboxno(String chargingboxno) {
        this.chargingboxno = chargingboxno;
    }

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    public String getWindowno() {
        return windowno;
    }

    public void setWindowno(String windowno) {
        this.windowno = windowno;
    }

    public String getPowerbankno() {
        return powerbankno;
    }

    public void setPowerbankno(String powerbankno) {
        this.powerbankno = powerbankno;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    @Override
    public String toString() {
        return "DeviceFixDO{" +
                "chargingboxno='" + chargingboxno + '\'' +
                ", reasons='" + reasons + '\'' +
                ", windowno='" + windowno + '\'' +
                ", powerbankno='" + powerbankno + '\'' +
                ", remark='" + remark + '\'' +
                ", photo='" + photo + '\'' +
                ", wxOpenid='" + wxOpenid + '\'' +
                ", skey='" + skey + '\'' +
                '}';
    }
}
