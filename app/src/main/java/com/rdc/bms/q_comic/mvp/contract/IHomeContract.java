package com.rdc.bms.q_comic.mvp.contract;

import com.rdc.bms.q_comic.bean.BannerBean;
import com.rdc.bms.q_comic.bean.ItemBean;

import java.util.List;

public interface IHomeContract {

    interface Presenter{
        void getHomePageData();
    }

    interface View{
        void showData(List<BannerBean> bannerList, List<ItemBean> itemBeanList);
        void onError(String msg);
    }
}
