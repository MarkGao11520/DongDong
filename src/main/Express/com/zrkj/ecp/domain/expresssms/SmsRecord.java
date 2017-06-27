package com.zrkj.ecp.domain.expresssms;

import java.util.Date;

public class SmsRecord {
    private Integer recordid;

    private Integer revid;

    private String revname;

    private String revphone;

    private String content;

    private Date adddate;

    private String adddateString;

    private Integer status;

    private Integer cid;

    private Integer isdel;

    public String getAdddateString() {
        return adddateString;
    }

    public void setAdddateString(String adddateString) {
        this.adddateString = adddateString;
    }

    public Integer getRecordid() {
        return recordid;
    }

    public void setRecordid(Integer recordid) {
        this.recordid = recordid;
    }

    public Integer getRevid() {
        return revid;
    }

    public void setRevid(Integer revid) {
        this.revid = revid;
    }

    public String getRevname() {
        return revname;
    }

    public void setRevname(String revname) {
        this.revname = revname == null ? null : revname.trim();
    }

    public String getRevphone() {
        return revphone;
    }

    public void setRevphone(String revphone) {
        this.revphone = revphone == null ? null : revphone.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getAdddate() {
        return adddate;
    }

    public void setAdddate(Date adddate) {
        this.adddate = adddate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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