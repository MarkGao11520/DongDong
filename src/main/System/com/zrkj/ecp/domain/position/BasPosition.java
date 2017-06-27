package com.zrkj.ecp.domain.position;

public class BasPosition {
    private Integer positionid;

    private Integer ppositionid;

    private String positionname;

    private String notes;

    private Integer disabled;

    private Integer quota;

    private Integer posorder;

    private Integer cid;

    private Integer did;
    
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

	public Integer getPositionid() {
        return positionid;
    }

    public void setPositionid(Integer positionid) {
        this.positionid = positionid;
    }

    public Integer getPpositionid() {
        return ppositionid;
    }

    public void setPpositionid(Integer ppositionid) {
        this.ppositionid = ppositionid;
    }

    public String getPositionname() {
        return positionname;
    }

    public void setPositionname(String positionname) {
        this.positionname = positionname == null ? null : positionname.trim();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public Integer getPosorder() {
        return posorder;
    }

    public void setPosorder(Integer posorder) {
        this.posorder = posorder;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }
}