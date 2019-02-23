package com.rdc.bms.q_comic.mvp.presenter;

import com.rdc.bms.q_comic.base.BasePresenter;
import com.rdc.bms.q_comic.mvp.contract.IHomeContract;
import com.rdc.bms.q_comic.mvp.model.BookModel;
import com.rdc.bms.q_comic.mvp.model.dto.HomePageDTO;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HomePresenter extends BasePresenter<IHomeContract.View> implements IHomeContract.Presenter {

    private BookModel mBookModel;

    public HomePresenter(){
        mBookModel = new BookModel();
    }


    @Override
    public void getHomePageData() {
        mBookModel.getHomePageData(new Observer<HomePageDTO>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HomePageDTO homePageDTO) {
                if (isAttachView()){
                    getMVPView().showData(homePageDTO.getBannerList(),homePageDTO.getItemList());
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
}
