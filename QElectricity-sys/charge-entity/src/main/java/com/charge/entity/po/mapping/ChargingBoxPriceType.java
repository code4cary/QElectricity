package com.charge.entity.po.mapping;

import com.charge.entity.po.base.BaseEntity;

import java.io.Serializable;

/**
 * Created by vincent on 18/09/2018.
 */
public class ChargingBoxPriceType extends BaseEntity implements Serializable {
    private Integer cbId;

    private Integer ptId;

    private static final long serialVersionUID = 1L;

    public Integer getCbId() {
        return cbId;
    }

    public void setCbId(Integer cbId) {
        this.cbId = cbId;
    }

    public Integer getPtId() {
        return ptId;
    }

    public void setPtId(Integer ptId) {
        this.ptId = ptId;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cbId=").append(cbId);
        sb.append(", ptId=").append(ptId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}