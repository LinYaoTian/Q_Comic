package com.rdc.bms.q_comic.mvp.contract;

import com.rdc.bms.q_comic.bean.SearchHistoryBean;
import com.rdc.bms.q_comic.bean.HotKeyBean;

import java.util.List;

public interface ISearchContract {
    interface View{
        void setHotKeys(List<HotKeyBean> list);
        void setHistoryList(List<SearchHistoryBean> list);
        void onError(String msg);
    }

    interface Presenter{
        void getHotKeys();
        void getHistoryList();
        void addHistory(List<SearchHistoryBean> list, SearchHistoryBean searchHistoryBean);
        void saveHistoryList(List<SearchHistoryBean> list);
    }
}
