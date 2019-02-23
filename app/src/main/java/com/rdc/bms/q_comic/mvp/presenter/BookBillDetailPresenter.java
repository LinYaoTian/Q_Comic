package com.rdc.bms.q_comic.mvp.presenter;

import com.rdc.bms.q_comic.base.BasePresenter;
import com.rdc.bms.q_comic.bean.BookBillBean;
import com.rdc.bms.q_comic.config.Constant;
import com.rdc.bms.q_comic.mvp.contract.IBookBillDetailContract;
import com.rdc.bms.q_comic.mvp.model.BookBillModel;
import com.rdc.bms.q_comic.mvp.model.dto.BookBillDetailContainerDTO;
import com.rdc.bms.q_comic.mvp.model.dto.BookBillDetailHeadDTO;
import com.rdc.bms.q_comic.mvp.model.vo.BookBillDetailVO;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BookBillDetailPresenter extends BasePresenter<IBookBillDetailContract.View>
        implements IBookBillDetailContract.Presenter {

    private BookBillModel model;
    private BookBillDetailVO mVo;
    private String mBookBillId;
    private BookBillBean mBookBillBean;
    private ArrayList<String> mCoverBookIdList;

    public BookBillDetailPresenter(){
        model = new BookBillModel();
    }

    @Override
    public void getBookBillDetailData(String bookBillId) {
        mBookBillId = bookBillId;
        model.getBookBillDetailHead(new Observer<BookBillDetailHeadDTO>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BookBillDetailHeadDTO bookBillDetailHeadDTO) {
                mVo = bookBillDetailHeadDTO.getBookBillDetailVO();
                mBookBillBean = mVo.getBookBillBean();
                model.getBookBillDetailContainer(new Observer<BookBillDetailContainerDTO>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BookBillDetailContainerDTO bookBillDetailContainerDTO) {
                        if (isAttachView()){
                            if (bookBillDetailContainerDTO.isSuccess()){
                                mVo.setBookList(bookBillDetailContainerDTO.toBookList());
                                getMVPView().setBookDetailData(mVo);
                            }else {
                                getMVPView().onError(Constant.TIP_ERROR_GET_DATA);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isAttachView()){
                            getMVPView().onError(Constant.TIP_ERROR_GET_DATA);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                }, mBookBillId,200);
            }

            @Override
            public void onError(Throwable e) {
                if (isAttachView()){
                    getMVPView().onError(Constant.TIP_ERROR_GET_DATA);
                }
            }

            @Override
            public void onComplete() {

            }
        },bookBillId);
    }


    @Override
    public void collectBookBill(boolean isCollect) {
        if (isCollect){
            mBookBillBean = new BookBillBean();
            mBookBillBean.setBookBillId(mBookBillId);
            mBookBillBean.setIntro(mVo.getSummary());
            mBookBillBean.setNum(Integer.valueOf(mVo.getBookCount()));
            mBookBillBean.setTitle(mVo.getBookTitle());
            mBookBillBean.setBookIdList(mCoverBookIdList);
            mBookBillBean.save();
        }else {
            mBookBillBean.delete();
        }
    }

    @Override
    public void updateBookBill() {
        assert mBookBillBean != null;
        mBookBillBean.setBookBillId(mBookBillId);
        mBookBillBean.setIntro(mVo.getSummary());
        mBookBillBean.setNum(Integer.valueOf(mVo.getBookCount()));
        mBookBillBean.setTitle(mVo.getBookTitle());
        mBookBillBean.setBookIdList(mCoverBookIdList);
        mBookBillBean.update(mBookBillBean.getPrimaryKey());
    }

    @Override
    public void setCoverBookIdList(ArrayList<String> coverBookIdList) {
        mCoverBookIdList = coverBookIdList;
    }
}
