package com.rdc.bms.q_comic.mvp.model.vo;

import java.util.List;

public class BookTopInfoVO {

    private String name;
    private String score;
    private String coverUrl;
    private String author;
    private List<String> mTagList;
    private String fireNum;//热度
    private boolean isCollected;//是否收藏

    @Override
    public String toString() {
        return "BookTopInfoVO{" +
                "name='" + name + '\'' +
                ", score='" + score + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                ", author='" + author + '\'' +
                ", mTagList=" + mTagList +
                ", fireNum='" + fireNum + '\'' +
                ", isCollected=" + isCollected +
                '}';
    }

    public String getScore() {
        return score == null ? "" : score;
    }

    public void setScore(String score) {
        this.score = score == null ? "" : score;
    }

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

    public String getAuthor() {
        return author == null ? "" : author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? "" : author;
    }

    public List<String> getmTagList() {
        return mTagList;
    }

    public void setmTagList(List<String> mTagList) {
        this.mTagList = mTagList;
    }

    public String getFireNum() {
        return fireNum == null ? "" : fireNum;
    }

    public void setFireNum(String fireNum) {
        this.fireNum = fireNum == null ? "" : fireNum;
    }

    public boolean isCollected() {
        return isCollected;
    }

    public void setCollected(boolean collected) {
        isCollected = collected;
    }
}
