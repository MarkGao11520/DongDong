package com.zrkj.ecp.dto.log;

import java.util.List;

public class LogDto {
	private String date;
	private Integer uid;
	private Integer size;
	private int rows;
	private int page;
	private	List<Integer> sizearr;
	
	
	
	
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public List<Integer> getSizearr() {
		return sizearr;
	}
	public void setSizearr(List<Integer> sizearr) {
		this.sizearr = sizearr;
	}



}
