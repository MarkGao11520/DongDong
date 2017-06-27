package com.zrkj.ecp.domain.org;

public class BasCompanyWithBLOBs extends BasCompany {
    private String shortname;
//    private byte[] shortname;
    private String config;
    
    



    public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config == null ? null : config.trim();
    }
}