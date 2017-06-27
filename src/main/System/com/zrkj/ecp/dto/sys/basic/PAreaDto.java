package com.zrkj.ecp.dto.sys.basic;

/**
 * Created by gaowenfeng on 2016/12/21.
 */
public class PAreaDto {
    private String pname;
    private Integer pid;
    private boolean selected=false;
    

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}
