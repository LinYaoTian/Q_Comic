package com.rdc.bms.q_comic.mvp.presenter;

import android.support.annotation.MainThread;
import android.util.Log;

import com.rdc.bms.q_comic.base.BasePresenter;
import com.rdc.bms.q_comic.bean.BookBean;
import com.rdc.bms.q_comic.mvp.contract.IHistoryContract;
import com.rdc.bms.q_comic.mvp.model.db.HistoryBean;
import com.rdc.bms.q_comic.mvp.model.dto.PageDTO;
import com.rdc.bms.q_comic.mvp.model.vo.HistoryVO;

import org.litepal.LitePal;
import org.litepal.crud.callback.CountCallback;
import org.litepal.crud.callback.FindMultiCallback;
import org.litepal.crud.callback.UpdateOrDeleteCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class HistoryPresenter extends BasePresenter<IHistoryContract.View>
        implements IHistoryContract.Presenter {

    private List<HistoryVO> mNewBookList;
    private List<HistoryVO> mOldBookList;
    private PageDTO mPage;
    private int mTotalPage;
    private final int PAGE_SIZE = 30;
    private boolean isStartEdit;


    public HistoryPresenter(){
        mNewBookList = new ArrayList<>();
        mOldBookList = new ArrayList<>();
    }

    @MainThread
    @Override
    public void refresh() {
        LitePal.countAsync(HistoryBean.class)
                .listen(new CountCallback() {
                    @Override
                    public void onFinish(int count) {
                        mTotalPage = count / PAGE_SIZE;
                        if (mTotalPage * PAGE_SIZE < count){
                            mTotalPage++;
                        }
                        mPage = new PageDTO(mTotalPage,0);
                        getHistoryData();
                    }
                });
    }

    @Override
    public void getHistoryData() {
        if (mPage.hasMore()){
            // 删除数据时只会删除HistoryBean表中的数据而不会删除BookBean表中的数据
            // 不然在删除历史记录时把可能会把收藏的漫画删除导致收藏丢失

            // 1.先向HistoryBean表查询历史漫画的ID
            LitePal.limit(PAGE_SIZE)
                    .offset(PAGE_SIZE * mPage.getCurrentPage())
                    .order("time desc")
                    .findAsync(HistoryBean.class)
                    .listen(new FindMultiCallback<HistoryBean>() {
                @Override
                public void onFinish(List<HistoryBean> list) {
                    long[] bookPrimaryKeys = new long[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        bookPrimaryKeys[i] = list.get(i).getBookPrimaryKey();
                    }
                    Log.d(TAG, "1. onFinish: "+ Arrays.toString(bookPrimaryKeys));
                    if (bookPrimaryKeys.length > 0){
                        //2.再向BookBean表查询详细数据
                        LitePal.findAllAsync(BookBean.class,bookPrimaryKeys)
                                .listen(new FindMultiCallback<BookBean>() {
                                    @Override
                                    public void onFinish(List<BookBean> list) {
                                        Log.d(TAG, "2. onFinish: "+list.toString());
                                        mOldBookList.clear();
                                        mOldBookList.addAll(mNewBookList);
                                        if (mPage.getCurrentPage() == 0){
                                            //第一页，刷新
                                            mNewBookList = new ArrayList<>();
                                        }
                                        for (BookBean bookBean : list) {
                                            mNewBookList.add(new HistoryVO(bookBean));
                                        }
                                        mPage.setCurrentPage(mPage.getCurrentPage() + 1);
                                       if (isAttachView()){
                                            getMVPView().setHistoryData(mOldBookList, mNewBookList);
                                            if (list.size() < PAGE_SIZE){
                                                getMVPView().noMore();
                                            }
                                            if (isStartEdit){
                                               //此时正在编辑
                                               startEdit();
                                            }
                                        }
                                    }
                                });
                    }

                }
            });
        }else {
            getMVPView().noMore();
        }

    }

    @Override
    public void startEdit() {
        isStartEdit = true;
        mOldBookList.clear();
        mOldBookList.addAll(mNewBookList);
        ListIterator<HistoryVO> listIterator = mNewBookList.listIterator();
        while (listIterator.hasNext()){
            HistoryVO c = listIterator.next().clone();
            c.setStartSelect(true);
            listIterator.set(c);
        }
        if (isAttachView()){
            getMVPView().setHistoryData(mOldBookList, mNewBookList);
        }
    }

    @Override
    public void endEdit() {
        isStartEdit = false;
        refresh();
    }

    @Override
    public void deleteBooks() {
        List<HistoryVO> temp = new ArrayList<>();
        mOldBookList.clear();
        mOldBookList.addAll(mNewBookList);
        for (HistoryVO c : mNewBookList) {
            if (c.isSelect()) {
                temp.add(c);
                c.getBookBean().deleteAsync().listen(new UpdateOrDeleteCallback() {
                    @Override
                    public void onFinish(int rowsAffected) {

                    }
                });
            }
        }
        mNewBookList.removeAll(temp);
        if (isAttachView()){
            getMVPView().setHistoryData(mOldBookList, mNewBookList);
        }
    }

    @Override
    public void selectAll(boolean isSelect) {
        mOldBookList.clear();
        mOldBookList.addAll(mNewBookList);
        ListIterator<HistoryVO> listIterator = mNewBookList.listIterator();
        while (listIterator.hasNext()){
            HistoryVO c = listIterator.next().clone();
            c.setSelect(isSelect);
            listIterator.set(c);
        }
        if (isAttachView()){
            getMVPView().setHistoryData(mOldBookList, mNewBookList);
        }
    }


}
