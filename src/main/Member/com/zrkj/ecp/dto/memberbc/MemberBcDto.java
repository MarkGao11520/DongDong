package com.zrkj.ecp.dto.memberbc;

/**
 * Created by gaowenfeng on 2017/1/17.
 */
public class MemberBcDto {
    private int page;
    private int rows;
    private Integer mid;
    private String userName;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
