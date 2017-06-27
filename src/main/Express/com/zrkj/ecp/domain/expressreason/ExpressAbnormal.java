package com.zrkj.ecp.domain.expressreason;

public class ExpressAbnormal {
    private Integer billid;

    private Integer reasonid;

    private String reasonname;

    private Integer uid;

    private String uname;

    private String billcode;

    private Integer isdel;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    /**
     *  private String reasonname
     *  属性：原因内容
     *  get方法
     */
    public String getReasonname() {
        return reasonname;
    }

    /**
     *  private String reasonname
     *  属性：原因内容
     *  set方法
     */
    public void setReasonname(String reasonname) {
        this.reasonname = reasonname;
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
     *  private Integer reasoinid
     *  属性：原因id
     *  get方法
     */
    public Integer getReasonid() {
        return reasonid;
    }

    /**
     *  private Integer reasoinid
     *  属性：原因id
     *  set方法
     */
    public void setReasonid(Integer reasonid) {
        this.reasonid = reasonid;
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