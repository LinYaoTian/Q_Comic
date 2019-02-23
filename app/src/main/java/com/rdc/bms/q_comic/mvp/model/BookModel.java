package com.rdc.bms.q_comic.mvp.model;

import com.rdc.bms.q_comic.bean.RankBean;
import com.rdc.bms.q_comic.bean.TypeBean;
import com.rdc.bms.q_comic.config.Constant;
import com.rdc.bms.q_comic.mvp.model.dto.HomePageDTO;
import com.rdc.bms.q_comic.util.JsoupUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class BookModel {

    private static Element sRootElement;//m.zymk.cn

    public void getTypeBeanList(final Observer<List<TypeBean>> observer, final int kind){
        Observable.create(new ObservableOnSubscribe<List<TypeBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<TypeBean>> emitter){
                try {
                    if (sRootElement == null){
                        sRootElement = Jsoup.connect(Constant.BASE_URL_WEB).get().body();
                    }
                    List<TypeBean> list = JsoupUtil.toTypeBeanList(sRootElement,kind);
                    if (list == null){
                        emitter.onError(new Exception(Constant.TIP_ERROR_GET_DATA));
                    }else {
                        emitter.onNext(list);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    emitter.onError(e);
                }finally {
                    emitter.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getKindTitleList(Observer<List<String>> observer){
        Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> emitter){
                try {
                    if (sRootElement == null){
                        sRootElement = Jsoup.connect(Constant.BASE_URL_WEB).get().body();
                    }
                    List<String> list = JsoupUtil.toKindTitleList(sRootElement);
                    if (list == null){
                        emitter.onError(new Exception(Constant.TIP_ERROR_GET_DATA));
                    }else {
                        emitter.onNext(list);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    emitter.onError(e);
                }finally {
                    emitter.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getRankData(Observer<List<RankBean>> observer, final int kind){
        Observable.create(new ObservableOnSubscribe<List<RankBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<RankBean>> emitter){
                try {
                    if (sRootElement == null){
                        sRootElement = Jsoup.connect(Constant.BASE_URL_WEB).get().body();
                    }
                    List<RankBean> list = JsoupUtil.toRankList(sRootElement,kind);
                    if (list == null){
                        emitter.onError(new Exception(Constant.TIP_ERROR_GET_DATA));
                    }else {
                        emitter.onNext(list);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    emitter.onError(e);
                }finally {
                    emitter.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getHomePageData(final Observer<HomePageDTO> observer){
        Observable.create(new ObservableOnSubscribe<HomePageDTO>() {
            @Override
            public void subscribe(ObservableEmitter<HomePageDTO> emitter){
                try {
                    if (sRootElement == null){
                        sRootElement = Jsoup.connect(Constant.BASE_URL_WEB).get().body();
                    }
                    HomePageDTO homePageDTO = JsoupUtil.toHomePageDTO(sRootElement);
                    if (homePageDTO == null){
                        emitter.onError(new Exception(Constant.TIP_ERROR_GET_DATA));
                    }else {
                        emitter.onNext(homePageDTO);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    emitter.onError(e);
                }finally {
                    emitter.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
