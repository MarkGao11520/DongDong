package com.zrkj.ecp.domain.expresslog;

import java.util.Date;

public class ExpressLog {
    private Integer logid;

    private String logcontent;

    private Date adddate;

    private String adddateString;

    private String notes;

    private Integer billid;

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
     *  private Integer logid;
     *  属性：日志id
     *  get方法
     */
    public Integer getLogid() {
        return logid;
    }

    /**
     *  private Integer logid;
     *  属性：日志id
     *  set方法
     */
    public void setLogid(Integer logid) {
        this.logid = logid;
    }

    /**
     *  private String logcontent;
     *  属性：日志内容
     *  get方法
     */
    public String getLogcontent() {
        return logcontent;
    }

    /**
     *  private String logcontent;
     *  属性：日志内容
     *  set方法
     */
    public void setLogcontent(String logcontent) {
        this.logcontent = logcontent == null ? null : logcontent.trim();
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