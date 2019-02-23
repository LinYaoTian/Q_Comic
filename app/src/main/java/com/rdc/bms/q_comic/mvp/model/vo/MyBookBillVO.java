package com.rdc.bms.q_comic.mvp.model.vo;

import com.rdc.bms.q_comic.base.BaseBookShelfVO;
import com.rdc.bms.q_comic.bean.BookBillBean;

import java.util.Objects;

public class MyBookBillVO extends BaseBookShelfVO implements Cloneable {
    private BookBillBean bookBillBean;

    public MyBookBillVO(BookBillBean bookBillBean) {
        this.bookBillBean = bookBillBean;
    }

    @Override
    public MyBookBillVO clone(){
        MyBookBillVO vo = null;
        try {
            vo = (MyBookBillVO) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return vo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyBookBillVO that = (MyBookBillVO) o;
        return Objects.equals(bookBillBean.getBookBillId(), that.bookBillBean.getBookBillId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookBillBean.getBookBillId());
    }

    public BookBillBean getBookBillBean() {
        return bookBillBean;
    }

    public void setBookBillBean(BookBillBean bookBillBean) {
        this.bookBillBean = bookBillBean;
    }
}
