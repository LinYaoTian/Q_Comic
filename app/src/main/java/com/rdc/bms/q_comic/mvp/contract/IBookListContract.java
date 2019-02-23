package com.rdc.bms.q_comic.mvp.contract;

import com.rdc.bms.q_comic.bean.BookBillBean;

import java.util.List;

public interface IBookListContract{
    interface view{
        void setBookList(List<BookBillBean> list);
        void showError(String msg);
        void setNoMore();
    }

    interface presenter{
        void getBookBillList(String tagName);
        void clearFlag();//重新加载数据，清除之前的标志值
    }
}
