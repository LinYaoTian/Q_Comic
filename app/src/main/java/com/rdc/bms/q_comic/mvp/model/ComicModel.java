package com.rdc.bms.q_comic.mvp.model;

import com.rdc.bms.q_comic.config.Constant;
import com.rdc.bms.q_comic.mvp.model.dto.ComicInfoDTO;

import com.rdc.bms.q_comic.mvp.model.service.ComicService;
import com.rdc.bms.q_comic.util.RetrofitUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ComicModel {
    public void getComic(Observer<ComicInfoDTO> observer, String bookId){
        RetrofitUtil.bind(Constant.BASE_URL_WEB_JSON).create(ComicService.class)
                .getComicInfo(bookId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
