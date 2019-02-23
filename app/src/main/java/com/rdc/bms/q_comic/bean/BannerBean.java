package com.rdc.bms.q_comic.bean;

/**
 * 首页轮播图实体
 */
public class BannerBean {

    private String title;
    private String imageUrl;
    private String bookId;

    @Override
    public String toString() {
        return "BannerBean{" +
                "title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", bookId=" + bookId +
                '}';
    }

    public BannerBean(String title, String imageUrl, String bookId) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.bookId = bookId;
    }

    public BannerBean(){

    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title == null ? "" : title;
    }

    public String getImageUrl() {
        return imageUrl == null ? "" : imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? "" : imageUrl;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
