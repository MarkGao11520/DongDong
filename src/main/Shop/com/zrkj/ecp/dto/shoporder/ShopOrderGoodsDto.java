package com.zrkj.ecp.dto.shoporder;

/**
 * Created by gaowenfeng on 2017/4/18.
 */
public class ShopOrderGoodsDto {
    private String goodsName;
    private Integer orderId;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
