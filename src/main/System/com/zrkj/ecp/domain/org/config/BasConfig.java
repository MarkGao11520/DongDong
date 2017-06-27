package com.zrkj.ecp.domain.org.config;

public class BasConfig {
    private Integer cofid;

    private String name;

    private String val;

    private Integer cid;
    
    private Integer isdel;
    
    

    public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public Integer getCofid() {
        return cofid;
    }

    public void setCofid(Integer cofid) {
        this.cofid = cofid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val == null ? null : val.trim();
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }
}