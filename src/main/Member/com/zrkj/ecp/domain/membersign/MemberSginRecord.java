package com.zrkj.ecp.domain.membersign;

import java.util.Date;

public class MemberSginRecord {
    private Integer signid;

    private String signcontent;

    private String signaddress;

    private Double longitude;

    private Double dimension;

    private String signip;

    private Date signdate;

    private String signdateString;

    private Integer mid;

    private Integer isdel;

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public String getSigndateString() {
        return signdateString;
    }

    public void setSigndateString(String signdateString) {
        this.signdateString = signdateString;
    }

    public Integer getSignid() {
        return signid;
    }

    public void setSignid(Integer signid) {
        this.signid = signid;
    }

    public String getSigncontent() {
        return signcontent;
    }

    public void setSigncontent(String signcontent) {
        this.signcontent = signcontent == null ? null : signcontent.trim();
    }

    public String getSignaddress() {
        return signaddress;
    }

    public void setSignaddress(String signaddress) {
        this.signaddress = signaddress == null ? null : signaddress.trim();
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getDimension() {
        return dimension;
    }

    public void setDimension(Double dimension) {
        this.dimension = dimension;
    }

    public String getSignip() {
        return signip;
    }

    public void setSignip(String signip) {
        this.signip = signip == null ? null : signip.trim();
    }

    public Date getSigndate() {
        return signdate;
    }

    public void setSigndate(Date signdate) {
        this.signdate = signdate;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }
}