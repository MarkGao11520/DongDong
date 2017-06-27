package com.zrkj.ecp.domain.expressreason;

import java.util.Date;

public class ExpressReason {
    private Integer reasonid;

    private String reasoncontent;

    private Date adddate;

    private String adddateString;

    private Integer uid;

    private Integer isdel;

    /**
     *  private Integer reasonid;
     *  属性：原因id
     *  get方法
     */
    public Integer getReasonid() {
        return reasonid;
    }

    /**
     *  private Integer reasonid;
     *  属性：原因id
     *  set方法
     */
    public void setReasonid(Integer reasonid) {
        this.reasonid = reasonid;
    }

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
     *  private String reasoncontent;
     *  属性：原因内容
     *  get方法
     */
    public String getReasoncontent() {
        return reasoncontent;
    }

    /**
     *  private String reasoncontent;
     *  属性：原因内容
     *  get方法
     */
    public void setReasoncontent(String reasoncontent) {
        this.reasoncontent = reasoncontent == null ? null : reasoncontent.trim();
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