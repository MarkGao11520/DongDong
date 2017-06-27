package com.zrkj.ecp.dto.shopgoods;

/**
 * Created by gaowenfeng on 2017/4/14.
 */
public class ShopGoodsClassDto {
    private String gcName;

    private Integer gcParentId;

    public Integer getGcParentId() {
        return gcParentId;
    }

    public void setGcParentId(Integer gcParentId) {
        this.gcParentId = gcParentId;
    }

    public String getGcName() {
        return gcName;
    }

    public void setGcName(String gcName) {
        this.gcName = gcName;
    }
}
