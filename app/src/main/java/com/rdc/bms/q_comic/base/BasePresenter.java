package com.rdc.bms.q_comic.base;


public class BasePresenter<V>{

    public final String TAG = "LYT";

    private V view;

    public void attachView(V v){
        view = v;
    }

    public void detachView(){
        view = null;
    }

    public boolean isAttachView(){
        return view != null;
    }

    public V getMVPView(){
        return view;
    }

}
