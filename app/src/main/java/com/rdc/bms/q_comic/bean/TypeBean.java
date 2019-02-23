package com.rdc.bms.q_comic.bean;

public class TypeBean {
    private String name;//分类名
    private String coverUrl;//分类封面
    private String typeId;//分类ID

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }

    public String getCoverUrl() {
        return coverUrl == null ? "" : coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl == null ? "" : coverUrl;
    }

    public String getTypeId() {
        return typeId == null ? "" : typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? "" : typeId;
    }
}
