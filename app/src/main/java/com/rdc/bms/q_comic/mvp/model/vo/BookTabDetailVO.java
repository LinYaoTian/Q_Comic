package com.rdc.bms.q_comic.mvp.model.vo;

import com.rdc.bms.q_comic.bean.BookBean;

import java.util.List;

public class BookTabDetailVO {
    private String intro;
    private String authorName;
    private String authorCoverUrl;
    private String fansNum;
    private String notice;
    private int priorityResId;//等级
    private List<BookBean> authorOtherComicList;
    private List<BookBean> recommendComicList;

    @Override
    public String toString() {
        return "BookTabDetailVO{" +
                "intro='" + intro + '\'' +
                ", authorName='" + authorName + '\'' +
                ", authorCoverUrl='" + authorCoverUrl + '\'' +
                ", fansNum='" + fansNum + '\'' +
                ", notice='" + notice + '\'' +
                ", authorOtherComicList=" + authorOtherComicList +
                ", recommendComicList=" + recommendComicList +
                '}';
    }

    public int getPriorityResId() {
        return priorityResId;
    }

    public void setPriorityResId(int priorityResId) {
        this.priorityResId = priorityResId;
    }

    public String getIntro() {
        return intro == null ? "" : intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? "" : intro;
    }

    public String getAuthorName() {
        return authorName == null ? "" : authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName == null ? "" : authorName;
    }

    public String getAuthorCoverUrl() {
        return authorCoverUrl == null ? "" : authorCoverUrl;
    }

    public void setAuthorCoverUrl(String authorCoverUrl) {
        this.authorCoverUrl = authorCoverUrl == null ? "" : authorCoverUrl;
    }

    public String getFansNum() {
        return fansNum == null ? "" : fansNum;
    }

    public void setFansNum(String fansNum) {
        this.fansNum = fansNum == null ? "" : fansNum;
    }

    public String getNotice() {
        return notice == null ? "" : notice;
    }

    public void setNotice(String notice) {
        this.notice = notice == null ? "" : notice;
    }

    public List<BookBean> getAuthorOtherComicList() {
        return authorOtherComicList;
    }

    public void setAuthorOtherComicList(List<BookBean> authorOtherComicList) {
        this.authorOtherComicList = authorOtherComicList;
    }

    public List<BookBean> getRecommendComicList() {
        return recommendComicList;
    }

    public void setRecommendComicList(List<BookBean> recommendComicList) {
        this.recommendComicList = recommendComicList;
    }
}
