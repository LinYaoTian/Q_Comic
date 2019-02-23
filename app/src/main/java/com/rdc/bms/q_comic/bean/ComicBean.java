package com.rdc.bms.q_comic.bean;

public class ComicBean {

    private String imageUrl;//图片URL
    private boolean isFirstPage;//是否本章第一页漫画
    private boolean isLastPage;//是否时本章最后一个漫画
    private long chapterId;//所在章节ID
    private int page;//在本章节中的页数

    public String getComicId(){
        return "*"+chapterId+"*"+page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getChapterId() {
        return chapterId;
    }

    public void setChapterId(long chapterId) {
        this.chapterId = chapterId;
    }

    public String getImageUrl() {
        return imageUrl == null ? "" : imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? "" : imageUrl;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }
}
