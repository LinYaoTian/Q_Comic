package com.rdc.bms.q_comic.mvp.contract;

import com.rdc.bms.q_comic.bean.BookBillBean;
import com.rdc.bms.q_comic.mvp.model.vo.MyBookBillVO;

import java.util.List;

public interface IMyBookBillContract {
    interface View{
        void setMyBookBillData(List<MyBookBillVO> oldList, List<MyBookBillVO> newList);
        void onError(String msg);
    }

    interface Presenter{
        void getMyBookBillData();
        void startEdit();
        void endEdit();
        void deleteBooks();
        void selectAll(boolean isSelect);
    }
}
