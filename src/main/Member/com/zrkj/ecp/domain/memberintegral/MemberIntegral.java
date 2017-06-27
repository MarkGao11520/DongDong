package com.zrkj.ecp.domain.memberintegral;

import java.util.Date;

public class MemberIntegral {
    private Integer mid;

    private String cardnum;

    private Date carddate;

    private String carddateString;

    private String notes;

    private String integralaccess;

    private Double amount;

    private Integer integral;

    private Integer integralsum;

    private Date vaildate;

    private String vaildateString;

    private Integer isdel;

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public String getCarddateString() {
        return carddateString;
    }

    public void setCarddateString(String carddateString) {
        this.carddateString = carddateString;
    }

    public String getVaildateString() {
        return vaildateString;
    }

    public void setVaildateString(String vaildateString) {
        this.vaildateString = vaildateString;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum == null ? null : cardnum.trim();
    }

    public Date getCarddate() {
        return carddate;
    }

    public void setCarddate(Date carddate) {
        this.carddate = carddate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    public String getIntegralaccess() {
        return integralaccess;
    }

    public void setIntegralaccess(String integralaccess) {
        this.integralaccess = integralaccess == null ? null : integralaccess.trim();
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getIntegralsum() {
        return integralsum;
    }

    public void setIntegralsum(Integer integralsum) {
        this.integralsum = integralsum;
    }

    public Date getVaildate() {
        return vaildate;
    }

    public void setVaildate(Date vaildate) {
        this.vaildate = vaildate;
    }
}