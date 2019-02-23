package com.rdc.bms.q_comic.bean;

import java.util.List;

public class RankBean {
    private String name;//漫画名
    private String imageUrl;//漫画封面URL
    private String bookId;//漫画ID
    private List<String> tagList;//标签列表
    private String total;//人气值
    private String rank;//排名

    private int colorRank;
    private int colorName;
    private int bg;//用于界面的背景色
    private int totalIvResId;//界面图标ID

    @Override
    public String toString() {
        return "RankBean{" +
                "name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", bookId='" + bookId + '\'' +
                ", tagList=" + tagList +
                ", total='" + total + '\'' +
                ", rank='" + rank + '\'' +
                ", colorRank=" + colorRank +
                ", colorName=" + colorName +
                ", bg=" + bg +
                ", totalIvResId=" + totalIvResId +
                '}';
    }

    public int getColorRank() {
        return colorRank;
    }

    public void setColorRank(int colorRank) {
        this.colorRank = colorRank;
    }

    public int getColorName() {
        return colorName;
    }

    public void setColorName(int colorName) {
        this.colorName = colorName;
    }

    public int getTotalIvResId() {
        return totalIvResId;
    }

    public void setTotalIvResId(int totalIvResId) {
        this.totalIvResId = totalIvResId;
    }

    public int getBg() {
        return bg;
    }

    public void setBg(int bg) {
        this.bg = bg;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }

    public String getImageUrl() {
        return imageUrl == null ? "" : imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? "" : imageUrl;
    }

    public String getBookId() {
        return bookId == null ? "" : bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId == null ? "" : bookId;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public String getTotal() {
        return total == null ? "" : total;
    }

    public void setTotal(String total) {
        this.total = total == null ? "" : total;
    }

    public String getRank() {
        return rank == null ? "" : rank;
    }

    public void setRank(String rank) {
        this.rank = rank == null ? "" : rank;
    }
}
