package com.rdc.bms.q_comic.mvp.contract;

import com.rdc.bms.q_comic.mvp.model.vo.BookBillDetailVO;

import java.util.ArrayList;

public interface IBookBillDetailContract {
    interface View{
        void setBookDetailData(BookBillDetailVO data);
        void onError(String msg);
    }

    interface Presenter {
        void getBookBillDetailData(String bookId);
        void collectBookBill(boolean isCollect);
        void updateBookBill();
        void setCoverBookIdList(ArrayList<String> coverBookIdList);
    }
}
