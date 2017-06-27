package com.zrkj.ecp.dto.memberaddress;

/**
 * Created by gaowenfeng on 2017/2/18.
 */
public class MemberAddressdDto {
    private int page;
    private int rows;
    private Integer mid;

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

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }
}
