package com.rdc.bms.q_comic.mvp.presenter;

import com.rdc.bms.q_comic.base.BasePresenter;
import com.rdc.bms.q_comic.bean.RankBean;
import com.rdc.bms.q_comic.mvp.contract.IRankContract;
import com.rdc.bms.q_comic.mvp.model.BookModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RankPresenter extends BasePresenter<IRankContract.View>
        implements IRankContract.Presenter {

    private BookModel mBookModel;

    public RankPresenter(){
        mBookModel = new BookModel();
    }

    @Override
    public void getRankData(int kind) {
        mBookModel.getRankData(new Observer<List<RankBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<RankBean> rankBeans) {
                if (isAttachView()){
                    getMVPView().setRankData(rankBeans);
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
        },kind);
    }
}
