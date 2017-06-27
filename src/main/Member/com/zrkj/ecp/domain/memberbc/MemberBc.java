package com.zrkj.ecp.domain.memberbc;

import java.util.Date;

public class MemberBc {
    private Integer bcid;

    private String bccode;

    private String username;

    private String usertele;

    private String bcpassword;

    private Integer status;

    private Integer isdefault;

    private String bcname;

    private Integer mid;

    private String mName;

    private Date adddate;

    private String adddateString;

    private Integer isdel;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getAdddateString() {
        return adddateString;
    }

    public void setAdddateString(String adddateString) {
        this.adddateString = adddateString;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public Integer getBcid() {
        return bcid;
    }

    public void setBcid(Integer bcid) {
        this.bcid = bcid;
    }

    public String getBccode() {
        return bccode;
    }

    public void setBccode(String bccode) {
        this.bccode = bccode == null ? null : bccode.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUsertele() {
        return usertele;
    }

    public void setUsertele(String usertele) {
        this.usertele = usertele == null ? null : usertele.trim();
    }

    public String getBcpassword() {
        return bcpassword;
    }

    public void setBcpassword(String bcpassword) {
        this.bcpassword = bcpassword == null ? null : bcpassword.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Integer isdefault) {
        this.isdefault = isdefault;
    }

    public String getBcname() {
        return bcname;
    }

    public void setBcname(String bcname) {
        this.bcname = bcname == null ? null : bcname.trim();
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Date getAdddate() {
        return adddate;
    }

    public void setAdddate(Date adddate) {
        this.adddate = adddate;
    }
}