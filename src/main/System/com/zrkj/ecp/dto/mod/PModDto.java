package com.zrkj.ecp.dto.mod;

public class PModDto {
	private Integer modid;
	private String modname;
	private String chailed;
	private boolean checked = false;

	public String getChailed() {
		return chailed;
	}

	public void setChailed(String chailed) {
		this.chailed = chailed;
	}

	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Integer getModid() {
		return modid;
	}
	public void setModid(Integer modid) {
		this.modid = modid;
	}
	public String getModname() {
		return modname;
	}
	public void setModname(String modname) {
		this.modname = modname;
	}
	
	

}
