package com.zrkj.ecp.domain.shoporder;

public class ShopOrderWithBLOBs extends ShopOrder {
    private String payMessage;

    private String deliverExplain;

    public String getPayMessage() {
        return payMessage;
    }

    public void setPayMessage(String payMessage) {
        this.payMessage = payMessage == null ? null : payMessage.trim();
    }

    public String getDeliverExplain() {
        return deliverExplain;
    }

    public void setDeliverExplain(String deliverExplain) {
        this.deliverExplain = deliverExplain == null ? null : deliverExplain.trim();
    }
}