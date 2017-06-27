package com.zrkj.ecp.domain.shopadv;

public class ShopAdvPosition {
    private Long id;

    private String apName;

    private String apIntro;

    private Integer apClass;

    private Integer apDisplay;

    private Integer isUse;

    private Integer apWidth;

    private Integer apHeight;

    private String apKey;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApName() {
        return apName;
    }

    public void setApName(String apName) {
        this.apName = apName == null ? null : apName.trim();
    }

    public String getApIntro() {
        return apIntro;
    }

    public void setApIntro(String apIntro) {
        this.apIntro = apIntro == null ? null : apIntro.trim();
    }

    public Integer getApClass() {
        return apClass;
    }

    public void setApClass(Integer apClass) {
        this.apClass = apClass;
    }

    public Integer getApDisplay() {
        return apDisplay;
    }

    public void setApDisplay(Integer apDisplay) {
        this.apDisplay = apDisplay;
    }

    public Integer getIsUse() {
        return isUse;
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }

    public Integer getApWidth() {
        return apWidth;
    }

    public void setApWidth(Integer apWidth) {
        this.apWidth = apWidth;
    }

    public Integer getApHeight() {
        return apHeight;
    }

    public void setApHeight(Integer apHeight) {
        this.apHeight = apHeight;
    }

    public String getApKey() {
        return apKey;
    }

    public void setApKey(String apKey) {
        this.apKey = apKey == null ? null : apKey.trim();
    }
}