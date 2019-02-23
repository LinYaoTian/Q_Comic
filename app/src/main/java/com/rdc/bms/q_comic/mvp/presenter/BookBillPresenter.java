package com.rdc.bms.q_comic.mvp.presenter;

import android.util.Log;

import com.rdc.bms.q_comic.base.BasePresenter;
import com.rdc.bms.q_comic.mvp.model.dto.PageDTO;
import com.rdc.bms.q_comic.mvp.contract.IBookListContract;
import com.rdc.bms.q_comic.mvp.model.BookBillModel;
import com.rdc.bms.q_comic.mvp.model.dto.BookListDTO;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BookBillPresenter extends BasePresenter<IBookListContract.view>
        implements IBookListContract.presenter {

    private BookBillModel mModel;

    private PageDTO mPageDTO;

    public BookBillPresenter(){
        mModel = new BookBillModel();
        mPageDTO = new PageDTO(2,0);
    }

    @Override
    public void getBookBillList(String tagName) {
        Log.d("BookBillPresenter", "getBookBillList: "+mPageDTO.toString());
        if (mPageDTO.hasMore()){
            mModel.getBookBillList(new Observer<BookListDTO>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(BookListDTO bookListDTO) {
                    mPageDTO = bookListDTO.getPageBean();
                    getMVPView().setBookList(bookListDTO.getBookListBeanList());
                }

                @Override
                public void onError(Throwable e) {
                    if(isAttachView()){
                        getMVPView().showError(e.getMessage());
                    }
                }

                @Override
                public void onComplete() {

                }
            },tagName, mPageDTO.getCurrentPage()+1);
        }else {
            if (isAttachView()){
                getMVPView().setNoMore();
            }
        }
    }

    @Override
    public void clearFlag() {
        mPageDTO = new PageDTO(2,0);
    }
}
