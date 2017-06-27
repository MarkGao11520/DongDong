package com.zrkj.ecp.domain.shopgoods;

public class ShopGoodsWithBLOBs extends ShopGoods {
    private String specName;

    private String goodsImageMore;

    private String goodsBody;

    private String goodsAttr;

    private String goodsSpec;

    private String goodsColImg;

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName == null ? null : specName.trim();
    }

    public String getGoodsImageMore() {
        return goodsImageMore;
    }

    public void setGoodsImageMore(String goodsImageMore) {
        this.goodsImageMore = goodsImageMore == null ? null : goodsImageMore.trim();
    }

    public String getGoodsBody() {
        return goodsBody;
    }

    public void setGoodsBody(String goodsBody) {
        this.goodsBody = goodsBody == null ? null : goodsBody.trim();
    }

    public String getGoodsAttr() {
        return goodsAttr;
    }

    public void setGoodsAttr(String goodsAttr) {
        this.goodsAttr = goodsAttr == null ? null : goodsAttr.trim();
    }

    public String getGoodsSpec() {
        return goodsSpec;
    }

    public void setGoodsSpec(String goodsSpec) {
        this.goodsSpec = goodsSpec == null ? null : goodsSpec.trim();
    }

    public String getGoodsColImg() {
        return goodsColImg;
    }

    public void setGoodsColImg(String goodsColImg) {
        this.goodsColImg = goodsColImg == null ? null : goodsColImg.trim();
    }
}