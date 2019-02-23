package com.rdc.bms.q_comic.mvp.model.dto;

public class PageDTO {
    private int totalPage;
    private int currentPage;

    @Override
    public String toString() {
        return "PageDTO{" +
                "totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                '}';
    }

    public PageDTO(){

    }

    public PageDTO(int totalPage, int currentPage) {
        this.totalPage = totalPage;
        this.currentPage = currentPage;
    }

    /**
     * 是否还有更多页
     * @return
     */
    public boolean hasMore(){
        return totalPage > currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
