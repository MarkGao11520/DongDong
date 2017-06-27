package com.zrkj.ecp.domain.member;

import java.util.Date;

public class MemberLevel {
    private Integer leid;

    private String lename;

    private String integrallower;

    private String integralupper;

    private Integer discount;

    private Integer specialgroup;

    private Date adddate;

    private String adddateString;

    private Integer isdel;

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public String getAdddateString() {
        return adddateString;
    }

    public void setAdddateString(String adddateString) {
        this.adddateString = adddateString;
    }

    public Integer getLeid() {
        return leid;
    }

    public void setLeid(Integer leid) {
        this.leid = leid;
    }

    public String getLename() {
        return lename;
    }

    public void setLename(String lename) {
        this.lename = lename == null ? null : lename.trim();
    }

    public String getIntegrallower() {
        return integrallower;
    }

    public void setIntegrallower(String integrallower) {
        this.integrallower = integrallower == null ? null : integrallower.trim();
    }

    public String getIntegralupper() {
        return integralupper;
    }

    public void setIntegralupper(String integralupper) {
        this.integralupper = integralupper == null ? null : integralupper.trim();
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getSpecialgroup() {
        return specialgroup;
    }

    public void setSpecialgroup(Integer specialgroup) {
        this.specialgroup = specialgroup;
    }

    public Date getAdddate() {
        return adddate;
    }

    public void setAdddate(Date adddate) {
        this.adddate = adddate;
    }
}