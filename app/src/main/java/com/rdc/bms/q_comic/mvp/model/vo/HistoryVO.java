package com.rdc.bms.q_comic.mvp.model.vo;

import com.rdc.bms.q_comic.base.BaseBookShelfVO;
import com.rdc.bms.q_comic.bean.BookBean;

import java.util.Objects;

public class HistoryVO extends BaseBookShelfVO implements Cloneable {
    private BookBean bookBean;
    public HistoryVO(BookBean bookBean) {
        this.bookBean = bookBean;
    }


    @Override
    public HistoryVO clone(){
        HistoryVO vo = null;
        try {
            vo = (HistoryVO) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return vo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryVO historyVO = (HistoryVO) o;
        return Objects.equals(bookBean.getBookId(), historyVO.bookBean.getBookId());
    }


    @Override
    public int hashCode() {
        return Objects.hash(bookBean.getBookId());
    }

    public BookBean getBookBean() {
        return bookBean;
    }

    public void setBookBean(BookBean bookBean) {
        this.bookBean = bookBean;
    }
}