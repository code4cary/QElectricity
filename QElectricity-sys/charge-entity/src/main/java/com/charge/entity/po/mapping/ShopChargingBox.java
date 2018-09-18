package com.charge.entity.po.mapping;

import com.charge.entity.po.base.BaseEntity;

import java.io.Serializable;

public class ShopChargingBox extends BaseEntity implements Serializable {
    private Integer sId;

    private Integer cbId;

    private static final long serialVersionUID = 1L;

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public Integer getCbId() {
        return cbId;
    }

    public void setCbId(Integer cbId) {
        this.cbId = cbId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sId=").append(sId);
        sb.append(", cbId=").append(cbId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}