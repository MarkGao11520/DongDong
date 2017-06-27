package com.zrkj.ecp.domain.memberbalance;

public class MemberBalanceActicityWithBLOBs extends MemberBalanceActicity {
    private String contents;

    private String agent;

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents == null ? null : contents.trim();
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent == null ? null : agent.trim();
    }
}