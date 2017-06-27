package com.zrkj.ecp.dto.member;

/**
 * Created by gaowenfeng on 2017/1/17.
 */
public class MemberLevelDto {
    private int page;
    private int rows;
    private String leName;

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

    public String getLeName() {
        return leName;
    }

    public void setLeName(String leName) {
        this.leName = leName;
    }
}
