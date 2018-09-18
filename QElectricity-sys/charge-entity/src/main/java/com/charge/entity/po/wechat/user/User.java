package com.charge.entity.po.wechat.user;

import com.charge.entity.po.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class User extends BaseEntity implements Serializable {
    private String nickName;

    private String avatar;

    private String gender;

    private String from;

    private String wxOpenid;

    private String city;

    private String location;

    private Integer point;

    private String telephone;

    private String appStatus;

    private String isStaff;

    private String isAgent;

    private String isVip;

    private String freeTime;

    private String skey;

    private String wxUionid;

    private String isUnsubscribe;

    private String description;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from == null ? null : from.trim();
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid == null ? null : wxOpenid.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus == null ? null : appStatus.trim();
    }

    public String getIsStaff() {
        return isStaff;
    }

    public void setIsStaff(String isStaff) {
        this.isStaff = isStaff == null ? null : isStaff.trim();
    }

    public String getIsAgent() {
        return isAgent;
    }

    public void setIsAgent(String isAgent) {
        this.isAgent = isAgent == null ? null : isAgent.trim();
    }

    public String getIsVip() {
        return isVip;
    }

    public void setIsVip(String isVip) {
        this.isVip = isVip == null ? null : isVip.trim();
    }

    public String getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(String freeTime) {
        this.freeTime = freeTime == null ? null : freeTime.trim();
    }

    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey == null ? null : skey.trim();
    }

    public String getWxUionid() {
        return wxUionid;
    }

    public void setWxUionid(String wxUionid) {
        this.wxUionid = wxUionid == null ? null : wxUionid.trim();
    }

    public String getIsUnsubscribe() {
        return isUnsubscribe;
    }

    public void setIsUnsubscribe(String isUnsubscribe) {
        this.isUnsubscribe = isUnsubscribe == null ? null : isUnsubscribe.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", nickName=").append(nickName);
        sb.append(", avatar=").append(avatar);
        sb.append(", gender=").append(gender);
        sb.append(", from=").append(from);
        sb.append(", wxOpenid=").append(wxOpenid);
        sb.append(", city=").append(city);
        sb.append(", location=").append(location);
        sb.append(", point=").append(point);
        sb.append(", telephone=").append(telephone);
        sb.append(", appStatus=").append(appStatus);
        sb.append(", isStaff=").append(isStaff);
        sb.append(", isAgent=").append(isAgent);
        sb.append(", isVip=").append(isVip);
        sb.append(", freeTime=").append(freeTime);
        sb.append(", skey=").append(skey);
        sb.append(", wxUionid=").append(wxUionid);
        sb.append(", isUnsubscribe=").append(isUnsubscribe);
        sb.append(", description=").append(description);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}