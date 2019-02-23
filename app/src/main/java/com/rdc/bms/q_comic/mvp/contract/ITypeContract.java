package com.rdc.bms.q_comic.mvp.contract;

import com.rdc.bms.q_comic.bean.TypeBean;

import java.util.List;

public interface ITypeContract {
    interface View{
        void setTypeList(List<TypeBean> list);
        void onError(String msg);
    }

    interface Presenter{
        void getTypeList(int kind);
    }
}
