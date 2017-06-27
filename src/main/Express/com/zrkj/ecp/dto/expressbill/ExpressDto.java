package com.zrkj.ecp.dto.expressbill;

/**
 * Created by gaowenfeng on 2017/1/19.
 * 该类属性说明
	/**
	 *  页数
    private int page;
    *

    /**
     * 每页条数
    private int rows;
    */
public class ExpressDto {
	/**
	 * 页数
	 */
    private int page;
    /**
     * 每页条数
     */
    private int rows;

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
