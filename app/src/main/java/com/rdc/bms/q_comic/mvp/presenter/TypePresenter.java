package com.rdc.bms.q_comic.mvp.presenter;

import com.rdc.bms.q_comic.base.BasePresenter;
import com.rdc.bms.q_comic.bean.TypeBean;
import com.rdc.bms.q_comic.mvp.contract.ITypeContract;
import com.rdc.bms.q_comic.mvp.model.BookModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class TypePresenter extends BasePresenter<ITypeContract.View> implements ITypeContract.Presenter {

    private BookModel mBookModel;

    public TypePresenter(){
        mBookModel = new BookModel();
    }

    @Override
    public void getTypeList(int kind) {
        mBookModel.getTypeBeanList(new Observer<List<TypeBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<TypeBean> typeBeans) {
                if (isAttachView()){
                    getMVPView().setTypeList(typeBeans);
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
