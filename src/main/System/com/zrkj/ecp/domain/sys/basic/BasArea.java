package com.zrkj.ecp.domain.sys.basic;

public class BasArea {
    private Integer aid;

    private String aname;

    private String code;

    private String zonetype;

    private String sortsum;

    private Integer pid;
    
    private Integer isdel;

    private String state = "closed";

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public String getZonetype() {
		return zonetype;
	}

	public void setZonetype(String zonetype) {
		this.zonetype = zonetype;
	}

	public String getSortsum() {
		return sortsum;
	}

	public void setSortsum(String sortsum) {
		this.sortsum = sortsum;
	}

	public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname == null ? null : aname.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

   

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}