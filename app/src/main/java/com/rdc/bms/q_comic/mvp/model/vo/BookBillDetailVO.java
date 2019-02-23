package com.rdc.bms.q_comic.mvp.model.vo;

import com.rdc.bms.q_comic.bean.BookBean;
import com.rdc.bms.q_comic.bean.BookBillBean;

import java.util.List;

public class BookBillDetailVO {
    private BookBillBean bookBillBean;
    private String bookBillId;
    private String bookTitle;
    private String summary;
    private String authorImage;
    private String collectNum;
    private String shareNum;
    private String commentNum;
    private String bookBillCover;
    private String bookCount;
    private List<BookBean> bookList;
    private boolean isCollected;

    public BookBillBean getBookBillBean() {
        return bookBillBean;
    }

    public void setBookBillBean(BookBillBean bookBillBean) {
        this.bookBillBean = bookBillBean;
    }

    public void setBookBillId(String bookBillId) {
        this.bookBillId = bookBillId == null ? "" : bookBillId;
    }

    public boolean isCollected() {
        return isCollected;
    }

    public void setCollected(boolean collected) {
        isCollected = collected;
    }

    public String getBookBillId() {
        return bookBillId == null ? "" : bookBillId;
    }

    public String getBookTitle() {
        return bookTitle == null ? "" : bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle == null ? "" : bookTitle;
    }

    public String getSummary() {
        return summary == null ? "" : summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? "" : summary;
    }

    public String getAuthorImage() {
        return authorImage == null ? "" : authorImage;
    }

    public void setAuthorImage(String authorImage) {
        this.authorImage = authorImage == null ? "" : authorImage;
    }

    public String getCollectNum() {
        return collectNum == null ? "" : collectNum;
    }

    public void setCollectNum(String collectNum) {
        this.collectNum = collectNum == null ? "" : collectNum;
    }

    public String getShareNum() {
        return shareNum == null ? "" : shareNum;
    }

    public void setShareNum(String shareNum) {
        this.shareNum = shareNum == null ? "" : shareNum;
    }

    public String getCommentNum() {
        return commentNum == null ? "" : commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum == null ? "" : commentNum;
    }

    public String getBookBillCover() {
        return bookBillCover == null ? "" : bookBillCover;
    }

    public void setBookBillCover(String bookBillCover) {
        this.bookBillCover = bookBillCover == null ? "" : bookBillCover;
    }

    public String getBookCount() {
        return bookCount == null ? "" : bookCount;
    }

    public void setBookCount(String bookCount) {
        this.bookCount = bookCount == null ? "" : bookCount;
    }

    public List<BookBean> getBookList() {
        return bookList;
    }

    public void setBookList(List<BookBean> bookList) {
        this.bookList = bookList;
    }
}
