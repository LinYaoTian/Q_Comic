package com.rdc.bms.q_comic.bean;

import com.rdc.bms.q_comic.mvp.model.vo.BookTabDetailVO;

public class BookInfoBean {
    private String intro;
    private String authorName;
    private String authorCoverUrl;
    private String fansNum;
    private String notice;
    private int priorityResId;

    public static BookInfoBean toThis(BookTabDetailVO bean){
        BookInfoBean bookInfoBean = new BookInfoBean();
        bookInfoBean.intro = bean.getIntro();
        bookInfoBean.authorName = bean.getAuthorName();
        bookInfoBean.authorCoverUrl = bean.getAuthorCoverUrl();
        bookInfoBean.fansNum = bean.getFansNum();
        bookInfoBean.notice = bean.getNotice();
        bookInfoBean.priorityResId = bean.getPriorityResId();
        return bookInfoBean;
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
}
