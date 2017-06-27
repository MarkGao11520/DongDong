package com.zrkj.ecp.domain.expresssignphoto;

import java.util.Date;

public class ExpressSignPhoto {
    private Integer phid;

    private String phname;

    private String phurl;

    private Date adddate;

    private String adddateString;

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
     *  private Integer phid
     *  属性：附件id
     *  get方法
     */
    public Integer getPhid() {
        return phid;
    }

    /**
     *  private Integer phid
     *  属性：附件id
     *  get方法
     */
    public void setPhid(Integer phid) {
        this.phid = phid;
    }

    /**
     *  private String phname
     *  属性：附件名称
     *  get方法
     */
    public String getPhname() {
        return phname;
    }

    /**
     *  private String phname
     *  属性：附件名称
     *  set方法
     */
    public void setPhname(String phname) {
        this.phname = phname == null ? null : phname.trim();
    }

    /**
     *  private String phurl
     *  属性：附件url
     *  get方法
     */
    public String getPhurl() {
        return phurl;
    }

    /**
     *  private String phurl
     *  属性：附件url
     *  set方法
     */
    public void setPhurl(String phurl) {
        this.phurl = phurl == null ? null : phurl.trim();
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