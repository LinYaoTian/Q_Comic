package com.rdc.bms.q_comic.mvp.model;

import com.rdc.bms.q_comic.config.Constant;
import com.rdc.bms.q_comic.mvp.model.dto.SearchDTO;
import com.rdc.bms.q_comic.mvp.model.service.SearchService;
import com.rdc.bms.q_comic.util.RetrofitUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchResultModel {

    public void searchType(Observer<SearchDTO> observer, final String type, int page){
        RetrofitUtil.bind(Constant.BASE_URL_WEB_JSON).create(SearchService.class)
                .search(type,"",page)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void searchKey(Observer<SearchDTO> observer, int page, String key){
        RetrofitUtil.bind(Constant.BASE_URL_WEB_JSON).create(SearchService.class)
                .search("all",key,page)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
