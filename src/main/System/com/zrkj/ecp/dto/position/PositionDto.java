package com.zrkj.ecp.dto.position;

public class PositionDto {
	private Integer cid;
	private Integer did;
	private String positionName;
    private Integer ppositionid;
	private int page;
	private int rows;
    private int type;

	public Integer getPpositionid() {
		return ppositionid;
	}

	public void setPpositionid(Integer ppositionid) {
		this.ppositionid = ppositionid;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
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
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	
	
}
