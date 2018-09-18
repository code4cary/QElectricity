package com.charge.entity.po.base;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by vincent on 14/09/2018.
 */

public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    protected Integer id;

    /**
     * createTime
     * @return
     */
    protected Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
