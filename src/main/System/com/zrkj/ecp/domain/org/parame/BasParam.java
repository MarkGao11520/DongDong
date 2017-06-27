package com.zrkj.ecp.domain.org.parame;

public class BasParam {
    private Integer paraid;

    private Integer pparaid;

    private String paraname;

    private Integer paraorder;

    private Integer type;

    private String description;

    private Integer isend;

    private Integer cid;
    
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

	public Integer getParaid() {
        return paraid;
    }

    public void setParaid(Integer paraid) {
        this.paraid = paraid;
    }

    public Integer getPparaid() {
        return pparaid;
    }

    public void setPparaid(Integer pparaid) {
        this.pparaid = pparaid;
    }

    public String getParaname() {
        return paraname;
    }

    public void setParaname(String paraname) {
        this.paraname = paraname == null ? null : paraname.trim();
    }

    public Integer getParaorder() {
        return paraorder;
    }

    public void setParaorder(Integer paraorder) {
        this.paraorder = paraorder;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getIsend() {
        return isend;
    }

    public void setIsend(Integer isend) {
        this.isend = isend;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }
}