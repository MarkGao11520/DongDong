package com.zrkj.ecp.dto.memberbalance;

/**
 * Created by gaowenfeng on 2017/1/17.
 */
public class MemberBalancesActicityDto {
    private int page;
    private int rows;
    private Integer baId;

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

    public Integer getBaId() {
        return baId;
    }

    public void setBaId(Integer baId) {
        this.baId = baId;
    }
}
