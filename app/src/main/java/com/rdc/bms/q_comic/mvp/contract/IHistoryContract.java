package com.rdc.bms.q_comic.mvp.contract;

import com.rdc.bms.easy_rv_adapter.OnClickViewRvListener;
import com.rdc.bms.q_comic.bean.BookBean;
import com.rdc.bms.q_comic.mvp.model.vo.HistoryVO;

import java.util.List;

public interface IHistoryContract {
    interface View{
        void setHistoryData(List<HistoryVO> oldList, List<HistoryVO> newList);
        void onError(String msg);
        void noMore();
    }

    interface Presenter{
        void refresh();
        void getHistoryData();
        void startEdit();
        void endEdit();
        void deleteBooks();
        void selectAll(boolean isSelect);
    }
}
