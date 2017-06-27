package com.zrkj.ecp.domain.expresssms;

import java.util.Date;

public class SmsTemplate {
    private Integer temid;

    private String temname;

    private String temcontent;

    private Date adddate;

    private String adddateString;

    private Integer isdefault;

    private Integer uid;

    private Integer cid;

    private Integer isdel;

    public String getAdddateString() {
        return adddateString;
    }

    public void setAdddateString(String adddateString) {
        this.adddateString = adddateString;
    }

    public Integer getTemid() {
        return temid;
    }

    public void setTemid(Integer temid) {
        this.temid = temid;
    }

    public String getTemname() {
        return temname;
    }

    public void setTemname(String temname) {
        this.temname = temname == null ? null : temname.trim();
    }

    public String getTemcontent() {
        return temcontent;
    }

    public void setTemcontent(String temcontent) {
        this.temcontent = temcontent == null ? null : temcontent.trim();
    }

    public Date getAdddate() {
        return adddate;
    }

    public void setAdddate(Date adddate) {
        this.adddate = adddate;
    }

    public Integer getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Integer isdefault) {
        this.isdefault = isdefault;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }
}