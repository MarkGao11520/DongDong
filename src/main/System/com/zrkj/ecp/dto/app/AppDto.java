package com.zrkj.ecp.dto.app;

public class AppDto {
     String appFullName;
     int page;
     int rows;
	public String getAppFullName() {
		return appFullName;
	}
	public void setAppFullName(String appFullName) {
		this.appFullName = appFullName;
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
