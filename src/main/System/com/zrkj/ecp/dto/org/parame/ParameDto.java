package com.zrkj.ecp.dto.org.parame;


public class ParameDto {
	String paraName;
	Integer cid;
	int page;
	int rows;
	int type;
	Integer paraid;


	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public Integer getParaid() {
		return paraid;
	}

	public void setParaid(Integer paraid) {
		this.paraid = paraid;
	}

	public String getParaName() {
		return paraName;
	}
	public void setParaName(String paraName) {
		this.paraName = paraName;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
}
