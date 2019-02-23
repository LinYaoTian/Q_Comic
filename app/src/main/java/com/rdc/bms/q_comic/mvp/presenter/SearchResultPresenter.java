package com.rdc.bms.q_comic.mvp.presenter;

import com.rdc.bms.q_comic.base.BasePresenter;
import com.rdc.bms.q_comic.mvp.model.dto.PageDTO;
import com.rdc.bms.q_comic.config.Constant;
import com.rdc.bms.q_comic.mvp.contract.ISearchResultContract;
import com.rdc.bms.q_comic.mvp.model.SearchResultModel;
import com.rdc.bms.q_comic.mvp.model.dto.SearchDTO;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SearchResultPresenter extends BasePresenter<ISearchResultContract.View> implements ISearchResultContract.Presenter {

    private SearchResultModel mSearchResultModel;
    private PageDTO mPageDTO;

    public SearchResultPresenter(){
        mSearchResultModel = new SearchResultModel();
        mPageDTO = new PageDTO(1,0);
    }

    @Override
    public void SearchKey(String key) {
        if (mPageDTO.hasMore()){
            mSearchResultModel.searchKey(new Observer<SearchDTO>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(SearchDTO dto) {
                    if (isAttachView()){
                        if (dto.isSuccess()){
                            mPageDTO = dto.toPageDTO();
                            getMVPView().setSearchResult(dto.toComicList());
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
            },mPageDTO.getCurrentPage()+1,key);
        }else {
            if (isAttachView()){
                getMVPView().noMore();
            }
        }
    }

    @Override
    public void SearchType(String type) {
        if (mPageDTO.hasMore()){
            mSearchResultModel.searchType(new Observer<SearchDTO>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(SearchDTO dto) {
                    if (isAttachView()){
                        if (dto.isSuccess()){
                            mPageDTO = dto.toPageDTO();
                            getMVPView().setSearchResult(dto.toComicList());
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
            },type,mPageDTO.getCurrentPage()+1);
        }else {
            if (isAttachView()){
                getMVPView().noMore();
            }
        }
    }
}
