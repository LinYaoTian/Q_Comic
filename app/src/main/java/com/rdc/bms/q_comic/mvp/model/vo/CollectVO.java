package com.rdc.bms.q_comic.mvp.model.vo;

import com.rdc.bms.q_comic.base.BaseBookShelfVO;
import com.rdc.bms.q_comic.bean.BookBean;

import java.util.Objects;

public class CollectVO extends BaseBookShelfVO implements Cloneable {
    private BookBean bookBean;
    public CollectVO(BookBean bookBean) {
        this.bookBean = bookBean;
    }


    @Override
    public CollectVO clone(){
        CollectVO vo = null;
        try {
            vo = (CollectVO) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return vo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectVO collectVO = (CollectVO) o;
        return Objects.equals(bookBean.getBookId(), collectVO.bookBean.getBookId());
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
