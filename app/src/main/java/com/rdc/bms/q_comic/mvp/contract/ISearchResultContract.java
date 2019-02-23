package com.rdc.bms.q_comic.mvp.contract;

import com.rdc.bms.q_comic.bean.BookBean;

import java.util.List;

public interface ISearchResultContract {
    interface View{
        void onError(String msg);
        void noMore();
        void setSearchResult(List<BookBean> list);
    }

    interface Presenter{
        void SearchKey(String key);
        void SearchType(String type);
    }
}
