package com.zrkj.ecp.domain.mod;

public class BasMod {
    private Integer modid;

    private Integer pid;

    private String modname;

    private String quickcode;

    private String instantiatemethod;

    private String typename;

    private String extradata;

    private String url;

    private Integer disabled;

    private String notes;

    private Integer classlevel;

    private Integer treeorder;

    private Integer appid;
    
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

	public Integer getModid() {
        return modid;
    }

    public void setModid(Integer modid) {
        this.modid = modid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getModname() {
        return modname;
    }

    public void setModname(String modname) {
        this.modname = modname == null ? null : modname.trim();
    }

    public String getQuickcode() {
        return quickcode;
    }

    public void setQuickcode(String quickcode) {
        this.quickcode = quickcode == null ? null : quickcode.trim();
    }

    public String getInstantiatemethod() {
        return instantiatemethod;
    }

    public void setInstantiatemethod(String instantiatemethod) {
        this.instantiatemethod = instantiatemethod == null ? null : instantiatemethod.trim();
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    public String getExtradata() {
        return extradata;
    }

    public void setExtradata(String extradata) {
        this.extradata = extradata == null ? null : extradata.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    public Integer getClasslevel() {
        return classlevel;
    }

    public void setClasslevel(Integer classlevel) {
        this.classlevel = classlevel;
    }

    public Integer getTreeorder() {
        return treeorder;
    }

    public void setTreeorder(Integer treeorder) {
        this.treeorder = treeorder;
    }

    public Integer getAppid() {
        return appid;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }
}