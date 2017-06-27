package com.zrkj.ecp.domain.member;

import java.util.Date;

public class MemberRealName {
    private Integer mid;

    private String address;

    private String idnum;

    private String issuer;

    private String validdate;

    private String idfronturl;

    private String idback;

    private String handid;

    private Integer status;

    private Date submitdate;

    private Date checkdate;

    private Date updatedate;

    private Integer submitsum;

    private Integer uid;

    private Integer isdel;

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum == null ? null : idnum.trim();
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer == null ? null : issuer.trim();
    }

    public String getValiddate() {
        return validdate;
    }

    public void setValiddate(String validdate) {
        this.validdate = validdate == null ? null : validdate.trim();
    }

    public String getIdfronturl() {
        return idfronturl;
    }

    public void setIdfronturl(String idfronturl) {
        this.idfronturl = idfronturl == null ? null : idfronturl.trim();
    }

    public String getIdback() {
        return idback;
    }

    public void setIdback(String idback) {
        this.idback = idback == null ? null : idback.trim();
    }

    public String getHandid() {
        return handid;
    }

    public void setHandid(String handid) {
        this.handid = handid == null ? null : handid.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getSubmitdate() {
        return submitdate;
    }

    public void setSubmitdate(Date submitdate) {
        this.submitdate = submitdate;
    }

    public Date getCheckdate() {
        return checkdate;
    }

    public void setCheckdate(Date checkdate) {
        this.checkdate = checkdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public Integer getSubmitsum() {
        return submitsum;
    }

    public void setSubmitsum(Integer submitsum) {
        this.submitsum = submitsum;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}