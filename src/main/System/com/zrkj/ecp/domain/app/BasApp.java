package com.zrkj.ecp.domain.app;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class BasApp {
    private Integer appid;

    private String appfullname;

    private String appshortname;

    private String appver;

    private MultipartFile apk;

    private String appurl;

    private String appicon;

    private Date deployeddate;

    private String deployeddateString;

    private Integer maxlicences;

    private Integer disabled;

    private String dbserver;

    private String username;

    private String password;

    private String notes;

    private Date lastupdated;

    private String lastupdatedString;

    private Integer apporder;
    
    private Integer isdel;

    public MultipartFile getApk() {
        return apk;
    }

    public void setApk(MultipartFile apk) {
        this.apk = apk;
    }

    public String getLastupdatedString() {
        return lastupdatedString;
    }

    public void setLastupdatedString(String lastupdatedString) {
        this.lastupdatedString = lastupdatedString;
    }

    public String getDeployeddateString() {
        return deployeddateString;
    }

    public void setDeployeddateString(String deployeddateString) {
        this.deployeddateString = deployeddateString;
    }

    public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public Integer getAppid() {
        return appid;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    public String getAppfullname() {
        return appfullname;
    }

    public void setAppfullname(String appfullname) {
        this.appfullname = appfullname == null ? null : appfullname.trim();
    }

    public String getAppshortname() {
        return appshortname;
    }

    public void setAppshortname(String appshortname) {
        this.appshortname = appshortname == null ? null : appshortname.trim();
    }

    public String getAppver() {
        return appver;
    }

    public void setAppver(String appver) {
        this.appver = appver == null ? null : appver.trim();
    }

    public String getAppurl() {
        return appurl;
    }

    public void setAppurl(String appurl) {
        this.appurl = appurl == null ? null : appurl.trim();
    }

    public String getAppicon() {
        return appicon;
    }

    public void setAppicon(String appicon) {
        this.appicon = appicon == null ? null : appicon.trim();
    }

    public Date getDeployeddate() {
        return deployeddate;
    }

    public void setDeployeddate(Date deployeddate) {
        this.deployeddate = deployeddate;
    }

    public Integer getMaxlicences() {
        return maxlicences;
    }

    public void setMaxlicences(Integer maxlicences) {
        this.maxlicences = maxlicences;
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

    public String getDbserver() {
        return dbserver;
    }

    public void setDbserver(String dbserver) {
        this.dbserver = dbserver == null ? null : dbserver.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    public Date getLastupdated() {
        return lastupdated;
    }

    public void setLastupdated(Date lastupdated) {
        this.lastupdated = lastupdated;
    }

    public Integer getApporder() {
        return apporder;
    }

    public void setApporder(Integer apporder) {
        this.apporder = apporder;
    }
}