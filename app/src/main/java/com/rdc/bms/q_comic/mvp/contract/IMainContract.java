package com.rdc.bms.q_comic.mvp.contract;

import java.util.List;

public interface IMainContract {

    interface Presenter{
        void getKindTitleList();
    }

    interface View{
        void setKindTitle(List<String> list);
    }
}
