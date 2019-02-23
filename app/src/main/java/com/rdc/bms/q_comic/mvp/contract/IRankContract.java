package com.rdc.bms.q_comic.mvp.contract;

import com.rdc.bms.q_comic.bean.RankBean;

import java.util.List;

public interface IRankContract {
    interface View{
        void setRankData(List<RankBean> list);
        void onError(String msg);
    }

    interface Presenter{
        void getRankData(int kind);
    }
}
