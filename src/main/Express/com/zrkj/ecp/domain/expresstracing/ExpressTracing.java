package com.zrkj.ecp.domain.expresstracing;

import java.util.Date;

public class ExpressTracing {
    private Integer tracid;

    private String billcode;

    private Integer statusbefore;

    private Integer statusafter;

    private Date changedate;

    private String changedateString;

    private String notes;

    private Integer billid;

    private Integer uid;

    private String uname;

    private Integer isdel;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    /**
     *  private String adddateString;
     *  属性：添加时间
     *  get方法
     *  查询时用
     */
    public String getChangedateString() {
        return changedateString;
    }

    /**
     *  private String adddateString;
     *  属性：添加时间
     *  set方法
     *  查询时用
     */
    public void setChangedateString(String changedateString) {
        this.changedateString = changedateString;
    }

    /**
     * private Integer tracid
     * 属性：跟踪记录id
     * get方法
     */
    public Integer getTracid() {
        return tracid;
    }

    /**
     * private Integer tracid
     * 属性：跟踪记录id
     * set方法
     */
    public void setTracid(Integer tracid) {
        this.tracid = tracid;
    }

    /**
     *  private String billcode
     *  属性：快递单
     *  get方法
     */
    public String getBillcode() {
        return billcode;
    }

    /**
     *  private String billcode
     *  属性：快递单
     *  set方法
     */
    public void setBillcode(String billcode) {
        this.billcode = billcode == null ? null : billcode.trim();
    }

    /**
     * private Integer statusbefore
     * 属性：变化之前状态
     * get方法
     */
    public Integer getStatusbefore() {
        return statusbefore;
    }

    /**
     * private Integer statusbefore
     * 属性：变化之前状态
     * set方法
     */
    public void setStatusbefore(Integer statusbefore) {
        this.statusbefore = statusbefore;
    }

    /**
     * private Integer statusafter
     * 属性：变化之后状态
     * get方法
     */
    public Integer getStatusafter() {
        return statusafter;
    }

    /**
     * private Integer statusafter
     * 属性：变化之后状态
     * get方法
     */
    public void setStatusafter(Integer statusafter) {
        this.statusafter = statusafter;
    }

    /**
     *  private Date adddate
     *  属性：添加时间
     *  get方法
     *  添加编辑时用
     */
    public Date getChangedate() {
        return changedate;
    }

    /**
     *  private Date adddate
     *  属性：添加时间
     *  set方法
     *  添加编辑时用
     */
    public void setChangedate(Date changedate) {
        this.changedate = changedate;
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
     *  private Integer billid
     *  属性：快递单id
     *  get方法
     */
    public Integer getBillid() {
        return billid;
    }

    /**
     *  private Integer billid
     *  属性：快递单id
     *  set方法
     */
    public void setBillid(Integer billid) {
        this.billid = billid;
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