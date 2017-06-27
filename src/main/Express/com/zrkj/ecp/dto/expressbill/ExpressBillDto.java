package com.zrkj.ecp.dto.expressbill;

import java.util.Date;

/**
 * Created by gaowenfeng on 2017/2/16.
 */
public class ExpressBillDto extends ExpressDto{
    private Integer billStauts;
    private Integer status;
    private String startDate;
    private String endDate;
    private String revname;
    private String revphone;
    private String billCode;
    private Integer fdedid;
    private Integer uid;
    private Integer revmemberid;
    private Integer sendmemberid;

    public Integer getRevmemberid() {
        return revmemberid;
    }

    public void setRevmemberid(Integer revmemberid) {
        this.revmemberid = revmemberid;
    }

    public Integer getSendmemberid() {
        return sendmemberid;
    }

    public void setSendmemberid(Integer sendmemberid) {
        this.sendmemberid = sendmemberid;
    }

    /**收件人id**/
    private Integer revMemberID;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRevMemberID() {
        return revMemberID;
    }

    public void setRevMemberID(Integer revMemberID) {
        this.revMemberID = revMemberID;
    }

    public Integer getFdedid() {
        return fdedid;
    }

    public void setFdedid(Integer fdedid) {
        this.fdedid = fdedid;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRevname() {
        return revname;
    }

    public void setRevname(String revname) {
        this.revname = revname;
    }

    public String getRevphone() {
        return revphone;
    }

    public void setRevphone(String revphone) {
        this.revphone = revphone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBillStauts() {
        return billStauts;
    }

    public void setBillStauts(Integer billStauts) {
        this.billStauts = billStauts;
    }
}
