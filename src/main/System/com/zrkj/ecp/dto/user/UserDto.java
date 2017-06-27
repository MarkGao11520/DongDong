package com.zrkj.ecp.dto.user;

public class UserDto {
 private String realname;
 private int did;
 private int cid;
 private int page;
 private int rows;

 


public int getDid() {
	return did;
}
public void setDid(int did) {
	this.did = did;
}
public int getCid() {
	return cid;
}
public void setCid(int cid) {
	this.cid = cid;
}

public String getRealname() {
	return realname;
}
public void setRealname(String realname) {
	this.realname = realname;
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
