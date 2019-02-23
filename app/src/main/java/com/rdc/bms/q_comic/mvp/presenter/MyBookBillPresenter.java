package com.rdc.bms.q_comic.mvp.presenter;

import com.rdc.bms.q_comic.base.BasePresenter;
import com.rdc.bms.q_comic.bean.BookBillBean;
import com.rdc.bms.q_comic.mvp.contract.IMyBookBillContract;
import com.rdc.bms.q_comic.mvp.model.vo.CollectVO;
import com.rdc.bms.q_comic.mvp.model.vo.MyBookBillVO;

import org.litepal.LitePal;
import org.litepal.crud.callback.FindMultiCallback;
import org.litepal.crud.callback.UpdateOrDeleteCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class MyBookBillPresenter extends BasePresenter<IMyBookBillContract.View>
        implements IMyBookBillContract.Presenter {

    private List<MyBookBillVO> mNewBookBillBookList;
    private List<MyBookBillVO> mOldBookBillBookList;
    private boolean isStartEdit;

    public MyBookBillPresenter(){
        mNewBookBillBookList = new ArrayList<>();
        mOldBookBillBookList = new ArrayList<>();
    }

    @Override
    public void getMyBookBillData() {
        LitePal.where()
                .findAsync(BookBillBean.class)
                .listen(new FindMultiCallback<BookBillBean>() {
                    @Override
                    public void onFinish(List<BookBillBean> list) {
                        if (isAttachView()){
                            if (list.size() > 0){
                                mOldBookBillBookList = mNewBookBillBookList;
                                mNewBookBillBookList = new ArrayList<>();
                                for (BookBillBean bookBillBean : list) {
                                    mNewBookBillBookList.add(new MyBookBillVO(bookBillBean));
                                }
                                getMVPView().setMyBookBillData(mOldBookBillBookList,mNewBookBillBookList);
                                if (isStartEdit){
                                    //此时正在编辑
                                    startEdit();
                                }
                            }else {
                                getMVPView().onError("还没有收藏任何书单哦！");
                            }
                        }
                    }
                });
    }

    @Override
    public void startEdit() {
        isStartEdit = true;
        mOldBookBillBookList.clear();
        mOldBookBillBookList.addAll(mNewBookBillBookList);
        ListIterator<MyBookBillVO> listIterator = mNewBookBillBookList.listIterator();
        while (listIterator.hasNext()){
            MyBookBillVO c = listIterator.next().clone();
            c.setStartSelect(true);
            listIterator.set(c);
        }
        if (isAttachView()){
            getMVPView().setMyBookBillData(mOldBookBillBookList,mNewBookBillBookList);
        }
    }

    @Override
    public void endEdit() {
        isStartEdit = false;
        mOldBookBillBookList.clear();
        mOldBookBillBookList.addAll(mNewBookBillBookList);
        ListIterator<MyBookBillVO> listIterator = mNewBookBillBookList.listIterator();
        while (listIterator.hasNext()){
            MyBookBillVO c = listIterator.next().clone();
            c.setStartSelect(false);
            c.setSelect(false);
            listIterator.set(c);
        }
        if (isAttachView()){
            getMVPView().setMyBookBillData(mOldBookBillBookList,mNewBookBillBookList);
        }
    }

    @Override
    public void deleteBooks() {
        List<MyBookBillVO> temp = new ArrayList<>();
        mOldBookBillBookList.clear();
        mOldBookBillBookList.addAll(mNewBookBillBookList);
        for (MyBookBillVO c : mNewBookBillBookList) {
            if (c.isSelect()) {
                c.getBookBillBean().deleteAsync().listen(new UpdateOrDeleteCallback() {
                    @Override
                    public void onFinish(int rowsAffected) {

                    }
                });
                temp.add(c);
            }
        }
        mNewBookBillBookList.removeAll(temp);
        if (isAttachView()){
            getMVPView().setMyBookBillData(mOldBookBillBookList,mNewBookBillBookList);
        }
    }

    @Override
    public void selectAll(boolean isSelect) {
        mOldBookBillBookList.clear();
        mOldBookBillBookList.addAll(mNewBookBillBookList);
        ListIterator<MyBookBillVO> listIterator = mNewBookBillBookList.listIterator();
        while (listIterator.hasNext()){
            MyBookBillVO c = listIterator.next().clone();
            c.setSelect(isSelect);
            listIterator.set(c);
        }
        if (isAttachView()){
            getMVPView().setMyBookBillData(mOldBookBillBookList,mNewBookBillBookList);
        }
    }
}
