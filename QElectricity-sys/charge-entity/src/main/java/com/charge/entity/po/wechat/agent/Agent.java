package com.charge.entity.po.wechat.agent;

import com.charge.entity.po.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Agent extends BaseEntity implements Serializable {
    private String realName;

    private String credentialsSalt;

    private String userName;

    private String password;

    private String idNumber;

    private String level;

    private String parentAgentId;

    private String shargingRatio;

    private String serviceCharges;

    private String canWithdraw;

    private String chargingBoxNum;

    private String frozenAmount;

    private String orderNum;

    private String totalRevenue;

    private String totalShare;

    private Date leaveTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;


    /**
     * @des:代理商关联代理商提现 one2many
     * @associate:
     * @see:
     */
    private List<AgentWithdrawal> agentWithdrawalList;

    /**
     * @des:代理商关联商户 one2many
     * @associate:
     * @see:
     */

    private List<Shop> shopList;

    public List<Shop> getShopList() {
        return shopList;
    }

    public List<AgentWithdrawal> getAgentWithdrawalList() {
        return agentWithdrawalList;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getCredentialsSalt() {
        return credentialsSalt;
    }

    public void setCredentialsSalt(String credentialsSalt) {
        this.credentialsSalt = credentialsSalt == null ? null : credentialsSalt.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getParentAgentId() {
        return parentAgentId;
    }

    public void setParentAgentId(String parentAgentId) {
        this.parentAgentId = parentAgentId == null ? null : parentAgentId.trim();
    }

    public String getShargingRatio() {
        return shargingRatio;
    }

    public void setShargingRatio(String shargingRatio) {
        this.shargingRatio = shargingRatio == null ? null : shargingRatio.trim();
    }

    public String getServiceCharges() {
        return serviceCharges;
    }

    public void setServiceCharges(String serviceCharges) {
        this.serviceCharges = serviceCharges == null ? null : serviceCharges.trim();
    }

    public String getCanWithdraw() {
        return canWithdraw;
    }

    public void setCanWithdraw(String canWithdraw) {
        this.canWithdraw = canWithdraw == null ? null : canWithdraw.trim();
    }

    public String getChargingBoxNum() {
        return chargingBoxNum;
    }

    public void setChargingBoxNum(String chargingBoxNum) {
        this.chargingBoxNum = chargingBoxNum == null ? null : chargingBoxNum.trim();
    }

    public String getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(String frozenAmount) {
        this.frozenAmount = frozenAmount == null ? null : frozenAmount.trim();
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public String getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(String totalRevenue) {
        this.totalRevenue = totalRevenue == null ? null : totalRevenue.trim();
    }

    public String getTotalShare() {
        return totalShare;
    }

    public void setTotalShare(String totalShare) {
        this.totalShare = totalShare == null ? null : totalShare.trim();
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setAgentWithdrawalList(List<AgentWithdrawal> agentWithdrawalList) {
        this.agentWithdrawalList = agentWithdrawalList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", realName=").append(realName);
        sb.append(", credentialsSalt=").append(credentialsSalt);
        sb.append(", userName=").append(userName);
        sb.append(", password=").append(password);
        sb.append(", idNumber=").append(idNumber);
        sb.append(", level=").append(level);
        sb.append(", parentAgentId=").append(parentAgentId);
        sb.append(", shargingRatio=").append(shargingRatio);
        sb.append(", serviceCharges=").append(serviceCharges);
        sb.append(", canWithdraw=").append(canWithdraw);
        sb.append(", chargingBoxNum=").append(chargingBoxNum);
        sb.append(", frozenAmount=").append(frozenAmount);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", totalRevenue=").append(totalRevenue);
        sb.append(", totalShare=").append(totalShare);
        sb.append(", leaveTime=").append(leaveTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}