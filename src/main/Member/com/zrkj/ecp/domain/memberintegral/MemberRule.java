package com.zrkj.ecp.domain.memberintegral;

public class MemberRule {
    private Integer rid;

    private String ruledetail;

    private Integer integral;

    private Integer isdel;

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRuledetail() {
        return ruledetail;
    }

    public void setRuledetail(String ruledetail) {
        this.ruledetail = ruledetail == null ? null : ruledetail.trim();
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }
}