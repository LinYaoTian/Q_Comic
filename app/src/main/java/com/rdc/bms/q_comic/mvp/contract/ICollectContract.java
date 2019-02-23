package com.rdc.bms.q_comic.mvp.contract;

import com.rdc.bms.q_comic.bean.BookBean;
import com.rdc.bms.q_comic.mvp.model.vo.CollectVO;

import java.util.List;

public interface ICollectContract {
    interface View{
        void setCollectData(List<CollectVO> oldList, List<CollectVO> newList);
        void onError(String msg);
    }

    interface Presenter{
        void getCollectData();
        void startEdit();
        void endEdit();
        void deleteBooks();
        void selectAll(boolean isSelect);
    }
}
