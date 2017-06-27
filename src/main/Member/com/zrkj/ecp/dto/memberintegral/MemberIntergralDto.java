package com.zrkj.ecp.dto.memberintegral;

/**
 * Created by gaowenfeng on 2017/1/17.
 */
public class MemberIntergralDto {
    private int page;
    private int rows;
    private Integer mid;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
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
