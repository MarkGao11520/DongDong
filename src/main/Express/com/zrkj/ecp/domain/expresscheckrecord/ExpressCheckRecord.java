package com.zrkj.ecp.domain.expresscheckrecord;

import java.util.Date;

public class ExpressCheckRecord {
    private Integer recordid;

    private Date recorddate;

    private String recorddateString;

    private Integer billid;

    private String billcode;

    private Integer isdel;

    public String getBillcode() {
        return billcode;
    }

    public void setBillcode(String billcode) {
        this.billcode = billcode;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public String getRecorddateString() {
        return recorddateString;
    }

    public void setRecorddateString(String recorddateString) {
        this.recorddateString = recorddateString;
    }

    public Integer getRecordid() {
        return recordid;
    }

    public void setRecordid(Integer recordid) {
        this.recordid = recordid;
    }

    public Date getRecorddate() {
        return recorddate;
    }

    public void setRecorddate(Date recorddate) {
        this.recorddate = recorddate;
    }

    public Integer getBillid() {
        return billid;
    }

    public void setBillid(Integer billid) {
        this.billid = billid;
    }
}