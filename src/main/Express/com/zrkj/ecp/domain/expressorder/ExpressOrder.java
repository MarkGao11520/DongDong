package com.zrkj.ecp.domain.expressorder;

import java.util.Date;

public class ExpressOrder {
    private Integer orderid;

    private Integer cid;

    private Integer type;

    private String ordercode;

    private String ordercontent;

    private Double money;

    private String description;

    private Date paydate;

    private String paydateString;

    private String username;

    private Date createdate;

    private String createdateString;

    private String billids;

    private String mname;

    private String seller_id;

    private Integer result;

    private Integer uid;

    private Integer mid;

    public String getCreatedateString() {
        return createdateString;
    }

    public void setCreatedateString(String createdateString) {
        this.createdateString = createdateString;
    }

    public String getPaydateString() {
        return paydateString;
    }

    public void setPaydateString(String paydateString) {
        this.paydateString = paydateString;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode == null ? null : ordercode.trim();
    }

    public String getOrdercontent() {
        return ordercontent;
    }

    public void setOrdercontent(String ordercontent) {
        this.ordercontent = ordercontent == null ? null : ordercontent.trim();
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getBillids() {
        return billids;
    }

    public void setBillids(String billids) {
        this.billids = billids == null ? null : billids.trim();
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname == null ? null : mname.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ExpressOrder{" +
                "orderid=" + orderid +
                ", cid=" + cid +
                ", type=" + type +
                ", ordercode='" + ordercode + '\'' +
                ", ordercontent='" + ordercontent + '\'' +
                ", money=" + money +
                ", description='" + description + '\'' +
                ", paydate=" + paydate +
                ", paydateString='" + paydateString + '\'' +
                ", username='" + username + '\'' +
                ", createdate=" + createdate +
                ", createdateString='" + createdateString + '\'' +
                ", billids='" + billids + '\'' +
                ", mname='" + mname + '\'' +
                ", seller_id='" + seller_id + '\'' +
                ", result=" + result +
                ", uid=" + uid +
                ", mid=" + mid +
                '}';
    }
}