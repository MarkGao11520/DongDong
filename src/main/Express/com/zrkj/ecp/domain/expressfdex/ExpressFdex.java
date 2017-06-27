package com.zrkj.ecp.domain.expressfdex;

import java.util.Date;

public class ExpressFdex {
    private Integer fdid;

    private String fdcode;

    private String fdname;

    private String contectperson;

    private String telephone;

    private Date adddate;

    private String adddateString;

    private Integer isdel;

    /**
     *  private String adddateString;
     *  属性 快递公司添加时间的get方法
     *  查询时用
     */
    public String getAdddateString() {
        return adddateString;
    }
    
    /**
     *  private String adddateString;
     *  属性 快递公司添加时间的set方法
     *  查询时用
     */
    public void setAdddateString(String adddateString) {
        this.adddateString = adddateString;
    }

    /**
     *  private Integer fdid;
     *  属性 快递公司id的get方法
     */
    public Integer getFdid() {
        return fdid;
    }

    /**
     *  private Integer fdid;
     *  属性 快递公司id的set方法
     */
    public void setFdid(Integer fdid) {
        this.fdid = fdid;
    }

    /**
     * private String fdcode;
     * 属性快递公司编号的get方法 
     */
    public String getFdcode() {
        return fdcode;
    }

    /**
     * private String fdcode;
     * 属性快递公司编号的set方法 
     */
    public void setFdcode(String fdcode) {
        this.fdcode = fdcode == null ? null : fdcode.trim();
    }

    /**
     * private String fdname;
     * 属性快递公司名称的get方法
     */
    public String getFdname() {
        return fdname;
    }

    /**
     * private String fdname;
     * 属性快递公司名称的set方法
     */
    public void setFdname(String fdname) {
        this.fdname = fdname == null ? null : fdname.trim();
    }

    /**
     *  private String contectperson;
     *  属性联系人的get方法
     */
    public String getContectperson() {
        return contectperson;
    }

    /**
     *  private String contectperson;
     *  属性联系人的set方法
     */
    public void setContectperson(String contectperson) {
        this.contectperson = contectperson == null ? null : contectperson.trim();
    }

    /**
     *  private String telephone;
     * 属性联系电话的get方法
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 属性联系电话的set方法
     * @param telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     *     private Date adddate;
     *     属性添加时间的get方法；
     *     添加编辑时用
     */
    public Date getAdddate() {
        return adddate;
    }

    /**
     *     private Date adddate;
     *     属性添加时间的set方法；
     *     添加编辑时用
     */
    public void setAdddate(Date adddate) {
        this.adddate = adddate;
    }

    /**
     * private Integer isdel
     * 属性isdel的get方法
     * 1：以删除，0：未删除
     */
    public Integer getIsdel() {
        return isdel;
    }

    /**
     * private Integer isdel
     * 属性isdel的set方法
     * 1：以删除，0：未删除
     */
    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }
}