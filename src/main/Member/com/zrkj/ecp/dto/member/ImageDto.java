package com.zrkj.ecp.dto.member;

import org.springframework.web.multipart.MultipartFile;

public class ImageDto {
	private MultipartFile idfaceurl;
	private MultipartFile idbackurl;
	private MultipartFile handidurl;
	public MultipartFile getIdfaceurl() {
		return idfaceurl;
	}
	public void setIdfaceurl(MultipartFile idfaceurl) {
		this.idfaceurl = idfaceurl;
	}
	public MultipartFile getIdbackurl() {
		return idbackurl;
	}
	public void setIdbackurl(MultipartFile idbackurl) {
		this.idbackurl = idbackurl;
	}
	public MultipartFile getHandidurl() {
		return handidurl;
	}
	public void setHandidurl(MultipartFile handidurl) {
		this.handidurl = handidurl;
	}
	
	

}
