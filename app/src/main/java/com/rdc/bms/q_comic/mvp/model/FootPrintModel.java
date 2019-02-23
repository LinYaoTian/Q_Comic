package com.rdc.bms.q_comic.mvp.model;

import android.util.Log;

import com.rdc.bms.q_comic.bean.BookBean;

import org.litepal.LitePal;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FootPrintModel {

    public void getMyCollect(Observer<List<BookBean>> observer){
        Observable.create(new ObservableOnSubscribe<List<BookBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<BookBean>> emitter){
                try {
                    List<BookBean> list = LitePal.where("isCollect = ?","1").find(BookBean.class);
                    Log.d("FootPrintModel", "subscribe: "+list.size());
                    emitter.onNext(list);
                }catch (Exception e){
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

    public void getHistoryRead(Observer<List<BookBean>> observer){
        Observable.create(new ObservableOnSubscribe<List<BookBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<BookBean>> emitter){
                try {
                    emitter.onNext(LitePal.where("").find(BookBean.class));
                }catch (Exception e){
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
