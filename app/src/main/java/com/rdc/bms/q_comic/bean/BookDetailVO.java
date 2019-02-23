package com.rdc.bms.q_comic.bean;

import com.rdc.bms.q_comic.mvp.model.vo.BookTabDetailVO;
import com.rdc.bms.q_comic.mvp.model.vo.BookTabDirVO;
import com.rdc.bms.q_comic.mvp.model.vo.BookTabSupportVO;
import com.rdc.bms.q_comic.mvp.model.vo.BookTopInfoVO;

public class BookDetailVO {
    private BookBean bookBean;
    private BookTopInfoVO comicTopInfo;
    private BookTabDetailVO bookTabDetailVO;
    private BookTabDirVO bookTabDirVO;
    private BookTabSupportVO bookTabSupportVO;

    public boolean isSuccess(){
        return comicTopInfo != null
                && bookTabDetailVO != null
                && bookTabDirVO != null
                && bookTabSupportVO != null;
    }

    @Override
    public String toString() {
        return "BookDetailVO{" +
                "comicTopInfo=" + comicTopInfo +
                ", bookTabDetailVO=" + bookTabDetailVO +
                ", bookTabDirVO=" + bookTabDirVO +
                ", bookTabSupportVO=" + bookTabSupportVO +
                '}';
    }

    public BookDetailVO(){

    }

    public BookBean getBookBean() {
        return bookBean;
    }

    public void setBookBean(BookBean bookBean) {
        this.bookBean = bookBean;
    }

    public BookDetailVO(BookTopInfoVO comicTopInfo,
                        BookTabDetailVO bookTabDetailVO,
                        BookTabDirVO bookTabDirVO,
                        BookTabSupportVO bookTabSupportVO) {
        this.comicTopInfo = comicTopInfo;
        this.bookTabDetailVO = bookTabDetailVO;
        this.bookTabDirVO = bookTabDirVO;
        this.bookTabSupportVO = bookTabSupportVO;
    }

    public BookTopInfoVO getComicTopInfo() {
        return comicTopInfo;
    }

    public void setComicTopInfo(BookTopInfoVO comicTopInfo) {
        this.comicTopInfo = comicTopInfo;
    }

    public BookTabDetailVO getBookTabDetailVO() {
        return bookTabDetailVO;
    }

    public void setBookTabDetailVO(BookTabDetailVO bookTabDetailVO) {
        this.bookTabDetailVO = bookTabDetailVO;
    }

    public BookTabDirVO getBookTabDirVO() {
        return bookTabDirVO;
    }

    public void setBookTabDirVO(BookTabDirVO bookTabDirVO) {
        this.bookTabDirVO = bookTabDirVO;
    }

    public BookTabSupportVO getBookTabSupportVO() {
        return bookTabSupportVO;
    }

    public void setBookTabSupportVO(BookTabSupportVO bookTabSupportVO) {
        this.bookTabSupportVO = bookTabSupportVO;
    }
}
