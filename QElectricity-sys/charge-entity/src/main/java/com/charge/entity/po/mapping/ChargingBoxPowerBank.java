package com.charge.entity.po.mapping;

import com.charge.entity.po.base.BaseEntity;

import java.io.Serializable;

public class ChargingBoxPowerBank extends BaseEntity implements Serializable {
    private Integer cbId;

    private Integer pbId;

    private static final long serialVersionUID = 1L;

    public Integer getCbId() {
        return cbId;
    }

    public void setCbId(Integer cbId) {
        this.cbId = cbId;
    }

    public Integer getPbId() {
        return pbId;
    }

    public void setPbId(Integer pbId) {
        this.pbId = pbId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cbId=").append(cbId);
        sb.append(", pbId=").append(pbId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}