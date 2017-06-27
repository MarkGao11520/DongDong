package com.zrkj.ecp.domain.expressfdex;

import java.util.Date;

public class ExpressSendFee {
    private Integer sendid;

    private Double sendfee;

    private String notes;

    private Date adddate;

    private String adddateString;

    private Integer fdid;

    private Integer uid;

    private Integer isdel;
    
    
    /**
     *  private String adddateString;
     *  属性：添加时间
     *  get方法
     *  查询时用
     */
    public String getAdddateString() {
        return adddateString;
    }

    /**
     *  private String adddateString;
     *  属性：添加时间
     *  set方法
     *  查询时用
     */
    public void setAdddateString(String adddateString) {
        this.adddateString = adddateString;
    }

    /**
     *  private Integer sendid
     *  属性：派送费id
     *  get方法
     */
    public Integer getSendid() {
        return sendid;
    }

    /**
     *  private Integer sendid
     *  属性：派送费id
     *  set方法
     */
    public void setSendid(Integer sendid) {
        this.sendid = sendid;
    }

    /**
     *  private Double sendfee
     *  属性：派送费
     *  get方法
     */
    public Double getSendfee() {
        return sendfee;
    }

    /**
     *  private Double sendfee
     *  属性：派送费
     *  set方法
     */
    public void setSendfee(Double sendfee) {
        this.sendfee = sendfee;
    }

    /**
     *  private String notes
     *  属性：备注
     *  get方法
     */
    public String getNotes() {
        return notes;
    }

    /**
     *  private String notes
     *  属性：备注
     *  set方法
     */
    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    /**
     *  private Date adddate
     *  属性：添加时间
     *  get方法
     *  添加编辑时用
     */
    public Date getAdddate() {
        return adddate;
    }

    /**
     *  private Date adddate
     *  属性：添加时间
     *  set方法
     *  添加编辑时用
     */
    public void setAdddate(Date adddate) {
        this.adddate = adddate;
    }

    /**
     *  private Integer fdid
     *  属性：快递公司id
     *  get方法
     */
    public Integer getFdid() {
        return fdid;
    }

    /**
     *  private Integer fdid
     *  属性：快递公司id
     *  set方法
     */
    public void setFdid(Integer fdid) {
        this.fdid = fdid;
    }

    /**
     *  private Integer uid
     *  属性：用户id
     *  get方法
     */
    public Integer getUid() {
        return uid;
    }

    /**
     *  private Integer uid
     *  属性：用户id
     *  set方法
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     *  private Integer isdel
     *  属性：是否删除
     *  get方法
     *  0：否，1：是
     */
    public Integer getIsdel() {
        return isdel;
    }

    /**
     *  private Integer isdel
     *  属性：是否删除
     *  set方法
     *  0：否，1：是
     */
    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }
}