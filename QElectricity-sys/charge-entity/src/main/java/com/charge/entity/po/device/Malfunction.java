package com.charge.entity.po.device;

import com.charge.entity.po.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class Malfunction extends BaseEntity implements Serializable {
    private String chargingboxno;

    private String reasons;

    private String windowno;

    private String powerbankno;

    private String remark;

    private String photo;

    private String wxOpenid;

    private Date endTime;

    private static final long serialVersionUID = 1L;

    public String getChargingboxno() {
        return chargingboxno;
    }

    public void setChargingboxno(String chargingboxno) {
        this.chargingboxno = chargingboxno == null ? null : chargingboxno.trim();
    }

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons == null ? null : reasons.trim();
    }

    public String getWindowno() {
        return windowno;
    }

    public void setWindowno(String windowno) {
        this.windowno = windowno == null ? null : windowno.trim();
    }

    public String getPowerbankno() {
        return powerbankno;
    }

    public void setPowerbankno(String powerbankno) {
        this.powerbankno = powerbankno == null ? null : powerbankno.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid == null ? null : wxOpenid.trim();
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", chargingboxno=").append(chargingboxno);
        sb.append(", reasons=").append(reasons);
        sb.append(", windowno=").append(windowno);
        sb.append(", powerbankno=").append(powerbankno);
        sb.append(", remark=").append(remark);
        sb.append(", photo=").append(photo);
        sb.append(", wxOpenid=").append(wxOpenid);
        sb.append(", endTime=").append(endTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}