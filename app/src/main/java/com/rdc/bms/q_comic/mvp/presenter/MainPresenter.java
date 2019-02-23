package com.rdc.bms.q_comic.mvp.presenter;

import com.rdc.bms.q_comic.base.BasePresenter;
import com.rdc.bms.q_comic.mvp.contract.IMainContract;
import com.rdc.bms.q_comic.mvp.model.BookModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainPresenter extends BasePresenter<IMainContract.View>
        implements IMainContract.Presenter {

    private BookModel mBookModel;

    public MainPresenter(){
        mBookModel = new BookModel();
    }

    @Override
    public void getKindTitleList() {
        mBookModel.getKindTitleList(new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<String> strings) {
                if (isAttachView()){
                    getMVPView().setKindTitle(strings);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
