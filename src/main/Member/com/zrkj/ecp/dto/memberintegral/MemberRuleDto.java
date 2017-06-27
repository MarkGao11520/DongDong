package com.zrkj.ecp.dto.memberintegral;

/**
 * Created by gaowenfeng on 2017/1/17.
 */
public class MemberRuleDto {
    private int page;
    private int rows;
    private String ruleDetail;

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

    public String getRuleDetail() {
        return ruleDetail;
    }

    public void setRuleDetail(String ruleDetail) {
        this.ruleDetail = ruleDetail;
    }
}
