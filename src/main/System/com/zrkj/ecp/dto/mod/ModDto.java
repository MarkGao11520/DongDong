package com.zrkj.ecp.dto.mod;

public class ModDto {
	private String modname;
	private int page;
	private Integer modid;
	private Integer allpid;
	private String appid;
	private int type;
	private int rows;
	
	
	
	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Integer getAllpid() {
		return allpid;
	}
	public void setAllpid(Integer allpid) {
		this.allpid = allpid;
	}
	public Integer getModid() {
		return modid;
	}
	public void setModid(Integer modid) {
		this.modid = modid;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getModname() {
		return modname;
	}
	public void setModname(String modname) {
		this.modname = modname;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	
	

}
