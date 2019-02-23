package com.rdc.bms.q_comic.mvp.model;

import com.rdc.bms.q_comic.bean.SearchHistoryBean;
import com.rdc.bms.q_comic.config.Constant;
import com.rdc.bms.q_comic.mvp.model.dto.HotKeyDTO;
import com.rdc.bms.q_comic.mvp.model.service.SearchService;
import com.rdc.bms.q_comic.util.RetrofitUtil;

import org.litepal.LitePal;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchModel {
    public void getHotKeys(Observer<HotKeyDTO> observer){
        RetrofitUtil.bind(Constant.BASE_URL_WEB_JSON).create(SearchService.class)
                .getHotKeys()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getSearchHistory(Observer<List<SearchHistoryBean>> observer){
        Observable.create(new ObservableOnSubscribe<List<SearchHistoryBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<SearchHistoryBean>> emitter) {
                List<SearchHistoryBean> list = LitePal.findAll(SearchHistoryBean.class);
                emitter.onNext(list);
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void saveHistoryList(final List<SearchHistoryBean> list){
        LitePal.deleteAll(SearchHistoryBean.class);
        LitePal.saveAll(list);
    }
}
