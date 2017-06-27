package com.zrkj.ecp.dto.user;

/**
 * Created by gaowenfeng on 2017/2/19.
 */
public class ExpressUserDto {
    private Integer uid;
    private String realname;
    private Integer posid;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Integer getPosid() {
        return posid;
    }

    public void setPosid(Integer posid) {
        this.posid = posid;
    }
}
