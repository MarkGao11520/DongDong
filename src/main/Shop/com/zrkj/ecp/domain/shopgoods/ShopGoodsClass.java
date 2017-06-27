package com.zrkj.ecp.domain.shopgoods;

public class ShopGoodsClass {
    private Long id;

    private String gcName;

    private String gcPic;

    private Long typeId;

    private String typeName;

    private Long gcParentId;

    private Boolean gcSort;

    private Boolean gcShow;

    private String gcTitle;

    private String gcKeywords;

    private String gcDescription;

    private String gcIdpath;

    private Float expenScale;

    private Boolean isRelate;

    private Long parentId;

    private String parentIds;

    private Integer isdel;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGcName() {
        return gcName;
    }

    public void setGcName(String gcName) {
        this.gcName = gcName == null ? null : gcName.trim();
    }

    public String getGcPic() {
        return gcPic;
    }

    public void setGcPic(String gcPic) {
        this.gcPic = gcPic == null ? null : gcPic.trim();
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public Long getGcParentId() {
        return gcParentId;
    }

    public void setGcParentId(Long gcParentId) {
        this.gcParentId = gcParentId;
    }

    public Boolean getGcSort() {
        return gcSort;
    }

    public void setGcSort(Boolean gcSort) {
        this.gcSort = gcSort;
    }

    public Boolean getGcShow() {
        return gcShow;
    }

    public void setGcShow(Boolean gcShow) {
        this.gcShow = gcShow;
    }

    public String getGcTitle() {
        return gcTitle;
    }

    public void setGcTitle(String gcTitle) {
        this.gcTitle = gcTitle == null ? null : gcTitle.trim();
    }

    public String getGcKeywords() {
        return gcKeywords;
    }

    public void setGcKeywords(String gcKeywords) {
        this.gcKeywords = gcKeywords == null ? null : gcKeywords.trim();
    }

    public String getGcDescription() {
        return gcDescription;
    }

    public void setGcDescription(String gcDescription) {
        this.gcDescription = gcDescription == null ? null : gcDescription.trim();
    }

    public String getGcIdpath() {
        return gcIdpath;
    }

    public void setGcIdpath(String gcIdpath) {
        this.gcIdpath = gcIdpath == null ? null : gcIdpath.trim();
    }

    public Float getExpenScale() {
        return expenScale;
    }

    public void setExpenScale(Float expenScale) {
        this.expenScale = expenScale;
    }

    public Boolean getIsRelate() {
        return isRelate;
    }

    public void setIsRelate(Boolean isRelate) {
        this.isRelate = isRelate;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds == null ? null : parentIds.trim();
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }
}