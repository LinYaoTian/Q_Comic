package com.rdc.bms.q_comic.mvp.presenter;

import com.rdc.bms.q_comic.base.BasePresenter;
import com.rdc.bms.q_comic.bean.SearchHistoryBean;
import com.rdc.bms.q_comic.config.Constant;
import com.rdc.bms.q_comic.mvp.contract.ISearchContract;
import com.rdc.bms.q_comic.mvp.model.SearchModel;
import com.rdc.bms.q_comic.mvp.model.dto.HotKeyDTO;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SearchPresenter extends BasePresenter<ISearchContract.View>
        implements ISearchContract.Presenter {

    private SearchModel mSearchModel;

    public SearchPresenter(){
        mSearchModel = new SearchModel();
    }

    @Override
    public void getHotKeys() {
        mSearchModel.getHotKeys(new Observer<HotKeyDTO>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HotKeyDTO hotKeyDTO) {
                if (isAttachView()){
                    if (hotKeyDTO.isSuccess()){
                        getMVPView().setHotKeys(hotKeyDTO.toHotKeyBeanList());
                    }else {
                        getMVPView().onError(Constant.TIP_ERROR_GET_DATA);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                if (isAttachView()){
                    getMVPView().onError(e.getMessage());
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getHistoryList() {
        mSearchModel.getSearchHistory(new Observer<List<SearchHistoryBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<SearchHistoryBean> list) {
                if (isAttachView()){
                    getMVPView().setHistoryList(list);
                }
            }

            @Override
            public void onError(Throwable e) {
                if (isAttachView()){
                    getMVPView().onError(e.getMessage());
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void addHistory(List<SearchHistoryBean> list, SearchHistoryBean searchHistoryBean) {
        int p = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getKey().equals(searchHistoryBean.getKey())){
                p = i;
                break;
            }
        }
        if (p != -1){
            //已经存在此关键字，则将记录提前
            list.remove(p);
        }else {
            //否则插入最后
            list.add(searchHistoryBean);
        }
    }

    @Override
    public void saveHistoryList(List<SearchHistoryBean> list) {
        mSearchModel.saveHistoryList(list);
    }
}
