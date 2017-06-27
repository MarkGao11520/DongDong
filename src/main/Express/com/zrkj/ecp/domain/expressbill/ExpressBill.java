package com.zrkj.ecp.domain.expressbill;

import java.util.Date;


/**
 *此为该类的属性说明 
 *
* 快递单id
*
private Integer billid;

/**
* 快递单号
*
private String billcode;

/**
* 收件人姓名
*
private String revname;

/**
* 收件人地址
*
private String revaddress;

/**
* 收件人手机号
*
private String revphone;

/**
* 寄件人姓名
*
private String sendname;

/**
* 寄件人地址
*
private String sendaddress;

/**
* 寄件人手机号
*
private String sendphone;

/**
* 托运物
*
private String cargo;

/**
* 是否易碎 1：易碎，0：非易碎
*
private Integer isfragile;

/**
* 运单日期（添加编辑时用）
*
private Date billdate;

/**
* 运单日子的字符串形式（查询时用）
*
private String billdateString;

/**
* 运费
*
private Double freight;

/**
* 付款方式：-10：到付 ，10：现金，20：支付宝，30：微信，40：银联
*
private Integer payment;

/**
* 申明价值
*
private Double claimvalue;

/**
* 代收货款
*
private Double cargomoney;

/**
* 状态
* 寄件状态
* 		100：待收件，200：已收件，300：快递员收件，400：退回件
* 	派件状态
* 		500：货物到达仓库，600：派送中 ，700：已签收
*
private Integer stauts;

/**
* 备注
*
private String notes;

/**
* 收件员id
*
private Integer revuserid;

/**
* 收件员姓名
*
private String revusername;

/**
* 寄件员id
*
private Integer senduserid;

/**
* 寄件员姓名
*
private String sendusername;

/**
* 寄件人id
*
private Integer sendmemberid;

/**
* 寄件人姓名
*
private String sendmembername;

/**
* 收件人id
*
private Integer revmemberid;

/**
* 收件人姓名
*
private String revmembername;

/**
* 签收人身份证号
*
private String sgincardnum;

/**
* 仓位号
*
private Integer positionno;

/**
* 序列号
*
private Integer serialno;

/**
* 快递公司id
*
private Integer fdid;

/**
* 快递公司姓名
*
private String fdname;

/**
* 企业id
*
private Integer cid;

/**
* 企业名称
*
private String cname;

/**
* 签收照片id
*
private Integer phid;

/**
* 附件名称
*
private String phname;

/**
* 附件url
*
private String phurl;

/**
* 是否删除
* private String isdel;
*/
public class ExpressBill {
	/**
	 * 快递单id
	 */
    private Integer billid;

    /**
     * 快递单号
     */
    private String billcode;

    /**
     * 收件人姓名
     */
    private String revname;

    /**
     * 收件人地址
     */
    private String revaddress;

    /**
     * 收件人手机号
     */
    private String revphone;

    /**
     * 寄件人姓名
     */
    private String sendname;

    /**
     * 寄件人地址
     */
    private String sendaddress;

    /**
     * 寄件人手机号
     */
    private String sendphone;

    /**
     * 托运物
     */
    private String cargo;

    /**
     * 是否易碎 1：易碎，0：非易碎
     */
    private Integer isfragile;

    /**
     * 运单日期（添加编辑时用）
     */
    private Date billdate;
    
    /**
     * 运单日子的字符串形式（查询时用）
     */
    private String billdateString;

    /**
     * 运费
     */
    private Double freight;

    /**
     * 付款方式：-10：到付 ，10：现金，20：支付宝，30：微信，40：银联
     */
    private Integer payment;

    /**
     * 申明价值
     */
    private Double claimvalue;

    /**
     * 代收货款
     */
    private Double cargomoney;

    /**
     * 状态
     * 寄件状态
     * 		100：待收件，200：已收件，300：快递员收件，400：退回件
     * 	派件状态
     * 		500：货物到达仓库，600：派送中 ，700：已签收
     */
    private Integer stauts;

    /**
     * 备注
     */
    private String notes;

    /**
     * 收件员id
     */
    private Integer revuserid;

    /**
     * 收件员姓名
     */
    private String revusername;

    /**
     * 寄件员id
     */
    private Integer senduserid;

    /**
     * 寄件员姓名
     */
    private String sendusername;

    /**
     * 寄件人id
     */
    private Integer sendmemberid;

    /**
     * 寄件人姓名
     */
    private String sendmembername;

    /**
     * 收件人id
     */
    private Integer revmemberid;

    /**
     * 收件人姓名
     */
    private String revmembername;

    /**
     * 签收人身份证号
     */
    private String sgincardnum;

    /**
     * 仓位号
     */
    private Integer positionno;

    /**
     * 序列号
     */
    private Integer serialno;

    /**
     * 快递公司id
     */
    private Integer fdid;

    /**
     * 快递公司姓名
     */
    private String fdname;

    /**
     * 企业id
     */
    private Integer cid;

    /**
     * 企业名称
     */
    private String cname;

    /**
     * 签收照片id
     */
    private Integer phid;

    /**
     * 附件名称
     */
    private String phname;

    /**
     * 附件url
     */
    private String phurl;

    /**
     * 是否删除
     */
    private Integer isdel;
    

    public String getPhname() {
        return phname;
    }

    public void setPhname(String phname) {
        this.phname = phname;
    }

    public String getPhurl() {
        return phurl;
    }

    public void setPhurl(String phurl) {
        this.phurl = phurl;
    }

    public String getRevusername() {
        return revusername;
    }

    public void setRevusername(String revusername) {
        this.revusername = revusername;
    }

    public String getSendusername() {
        return sendusername;
    }

    public void setSendusername(String sendusername) {
        this.sendusername = sendusername;
    }

    public String getSendmembername() {
        return sendmembername;
    }

    public void setSendmembername(String sendmembername) {
        this.sendmembername = sendmembername;
    }

    public String getRevmembername() {
        return revmembername;
    }

    public void setRevmembername(String revmembername) {
        this.revmembername = revmembername;
    }

    public String getFdname() {
        return fdname;
    }

    public void setFdname(String fdname) {
        this.fdname = fdname;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getBilldateString() {
        return billdateString;
    }

    public void setBilldateString(String billdateString) {
        this.billdateString = billdateString;
    }

    public Integer getBillid() {
        return billid;
    }

    public void setBillid(Integer billid) {
        this.billid = billid;
    }

    public String getBillcode() {
        return billcode;
    }

    public void setBillcode(String billcode) {
        this.billcode = billcode == null ? null : billcode.trim();
    }

    public String getRevname() {
        return revname;
    }

    public void setRevname(String revname) {
        this.revname = revname == null ? null : revname.trim();
    }

    public String getRevaddress() {
        return revaddress;
    }

    public void setRevaddress(String revaddress) {
        this.revaddress = revaddress == null ? null : revaddress.trim();
    }

    public String getRevphone() {
        return revphone;
    }

    public void setRevphone(String revphone) {
        this.revphone = revphone == null ? null : revphone.trim();
    }

    public String getSendname() {
        return sendname;
    }

    public void setSendname(String sendname) {
        this.sendname = sendname == null ? null : sendname.trim();
    }

    public String getSendaddress() {
        return sendaddress;
    }

    public void setSendaddress(String sendaddress) {
        this.sendaddress = sendaddress == null ? null : sendaddress.trim();
    }

    public String getSendphone() {
        return sendphone;
    }

    public void setSendphone(String sendphone) {
        this.sendphone = sendphone == null ? null : sendphone.trim();
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo == null ? null : cargo.trim();
    }

    public Integer getIsfragile() {
        return isfragile;
    }

    public void setIsfragile(Integer isfragile) {
        this.isfragile = isfragile;
    }

    public Date getBilldate() {
        return billdate;
    }

    public void setBilldate(Date billdate) {
        this.billdate = billdate;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public Double getClaimvalue() {
        return claimvalue;
    }

    public void setClaimvalue(Double claimvalue) {
        this.claimvalue = claimvalue;
    }

    public Double getCargomoney() {
        return cargomoney;
    }

    public void setCargomoney(Double cargomoney) {
        this.cargomoney = cargomoney;
    }

    public Integer getStauts() {
        return stauts;
    }

    public void setStauts(Integer stauts) {
        this.stauts = stauts;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    public Integer getRevuserid() {
        return revuserid;
    }

    public void setRevuserid(Integer revuserid) {
        this.revuserid = revuserid;
    }

    public Integer getSenduserid() {
        return senduserid;
    }

    public void setSenduserid(Integer senduserid) {
        this.senduserid = senduserid;
    }

    public Integer getSendmemberid() {
        return sendmemberid;
    }

    public void setSendmemberid(Integer sendmemberid) {
        this.sendmemberid = sendmemberid;
    }

    public Integer getRevmemberid() {
        return revmemberid;
    }

    public void setRevmemberid(Integer revmemberid) {
        this.revmemberid = revmemberid;
    }

    public String getSgincardnum() {
        return sgincardnum;
    }

    public void setSgincardnum(String sgincardnum) {
        this.sgincardnum = sgincardnum == null ? null : sgincardnum.trim();
    }

    public Integer getPositionno() {
        return positionno;
    }

    public void setPositionno(Integer positionno) {
        this.positionno = positionno;
    }

    public Integer getSerialno() {
        return serialno;
    }

    public void setSerialno(Integer serialno) {
        this.serialno = serialno;
    }

    public Integer getFdid() {
        return fdid;
    }

    public void setFdid(Integer fdid) {
        this.fdid = fdid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getPhid() {
        return phid;
    }

    public void setPhid(Integer phid) {
        this.phid = phid;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    @Override
    public String toString() {
        return "ExpressBill{" +
                "billid=" + billid +
                ", billcode='" + billcode + '\'' +
                ", revname='" + revname + '\'' +
                ", revaddress='" + revaddress + '\'' +
                ", revphone='" + revphone + '\'' +
                ", sendname='" + sendname + '\'' +
                ", sendaddress='" + sendaddress + '\'' +
                ", sendphone='" + sendphone + '\'' +
                ", cargo='" + cargo + '\'' +
                ", isfragile=" + isfragile +
                ", billdate=" + billdate +
                ", billdateString='" + billdateString + '\'' +
                ", freight=" + freight +
                ", payment=" + payment +
                ", claimvalue=" + claimvalue +
                ", cargomoney=" + cargomoney +
                ", stauts=" + stauts +
                ", notes='" + notes + '\'' +
                ", revuserid=" + revuserid +
                ", revusername='" + revusername + '\'' +
                ", senduserid=" + senduserid +
                ", sendusername='" + sendusername + '\'' +
                ", sendmemberid=" + sendmemberid +
                ", sendmembername='" + sendmembername + '\'' +
                ", revmemberid=" + revmemberid +
                ", revmembername='" + revmembername + '\'' +
                ", sgincardnum='" + sgincardnum + '\'' +
                ", positionno=" + positionno +
                ", serialno=" + serialno +
                ", fdid=" + fdid +
                ", fdname='" + fdname + '\'' +
                ", cid=" + cid +
                ", cname='" + cname + '\'' +
                ", phid=" + phid +
                ", phname='" + phname + '\'' +
                ", phurl='" + phurl + '\'' +
                ", isdel=" + isdel +
                '}';
    }
}