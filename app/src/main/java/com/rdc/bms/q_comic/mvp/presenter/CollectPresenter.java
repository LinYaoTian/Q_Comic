package com.rdc.bms.q_comic.mvp.presenter;

import android.util.Log;

import com.rdc.bms.q_comic.base.BasePresenter;
import com.rdc.bms.q_comic.bean.BookBean;
import com.rdc.bms.q_comic.mvp.model.db.CollectBean;
import com.rdc.bms.q_comic.mvp.contract.ICollectContract;
import com.rdc.bms.q_comic.mvp.model.CollectModel;
import com.rdc.bms.q_comic.mvp.model.vo.CollectVO;

import org.litepal.LitePal;
import org.litepal.crud.callback.UpdateOrDeleteCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class CollectPresenter extends BasePresenter<ICollectContract.View>
        implements ICollectContract.Presenter {

    private CollectModel mCollectModel;

    private List<CollectVO> mNewCollectBookList;
    private List<CollectVO> mOldCollectBookList;
    private boolean isStartEdit;

    public CollectPresenter(){
        mNewCollectBookList = new ArrayList<>();
        mOldCollectBookList = new ArrayList<>();
        mCollectModel = new CollectModel();
    }

    @Override
    public void getCollectData() {
        //数据库中的个人收藏列表
        List<CollectBean> collectBeanList = LitePal
                .where()
                .order("time desc")
                .find(CollectBean.class);
        if (collectBeanList.size() > 0){
            // 1. 先从数据库获取本地的收藏数据
            long[] mBookPrimaryKeys = new long[collectBeanList.size()];
            //获取所有收藏的书本的数据库主键
            for (int i = 0; i < collectBeanList.size(); i++) {
                mBookPrimaryKeys[i] = collectBeanList.get(i).getBookPrimaryKey();
            }
            mOldCollectBookList = mNewCollectBookList;
            mNewCollectBookList = new ArrayList<>();
            List<BookBean> list = LitePal.findAll(BookBean.class, mBookPrimaryKeys);
            for (BookBean bookBean : list) {
                mNewCollectBookList.add(new CollectVO(bookBean));
            }
            if (isAttachView()){
                getMVPView().setCollectData(mOldCollectBookList, mNewCollectBookList);
                if (isStartEdit){
                    startEdit();
                }
            }
            //2.联网检查更新
            mCollectModel.updateAll(mNewCollectBookList, new Observer<CollectVO>() {
                List<CollectVO> list = new ArrayList<>();
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(CollectVO collectVO) {
                    list.add(collectVO);
                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    Log.d(TAG, "CollectPresenter onError: "+e.getMessage());
                }

                @Override
                public void onComplete() {
                    mOldCollectBookList = mNewCollectBookList;
                    mNewCollectBookList = list;
                    if (isAttachView()){
                        getMVPView().setCollectData(mOldCollectBookList,mNewCollectBookList);
                        if (isStartEdit){
                            startEdit();
                        }
                    }
                }
            });
        }else {
            if (isAttachView()){
                getMVPView().onError("还没收藏漫画哦！");
            }
        }
    }

    @Override
    public void startEdit() {
        isStartEdit = true;
        mOldCollectBookList.clear();
        mOldCollectBookList.addAll(mNewCollectBookList);
        ListIterator<CollectVO> listIterator = mNewCollectBookList.listIterator();
        while (listIterator.hasNext()){
            CollectVO c = listIterator.next().clone();
            c.setStartSelect(true);
            listIterator.set(c);
        }
        if (isAttachView()){
            getMVPView().setCollectData(mOldCollectBookList,mNewCollectBookList);
        }

    }

    @Override
    public void endEdit() {
        isStartEdit = false;
        mOldCollectBookList.clear();
        mOldCollectBookList.addAll(mNewCollectBookList);
        ListIterator<CollectVO> listIterator = mNewCollectBookList.listIterator();
        while (listIterator.hasNext()){
            CollectVO c = listIterator.next().clone();
            c.setStartSelect(false);
            c.setSelect(false);
            listIterator.set(c);
        }
        if (isAttachView()){
            getMVPView().setCollectData(mOldCollectBookList,mNewCollectBookList);
        }
    }

    @Override
    public void deleteBooks() {
        List<CollectVO> temp = new ArrayList<>();
        mOldCollectBookList.clear();
        mOldCollectBookList.addAll(mNewCollectBookList);
        for (CollectVO c : mNewCollectBookList) {
            if (c.isSelect()) {
                temp.add(c);
                LitePal.deleteAllAsync(
                        CollectBean.class,
                        "bookPrimaryKey = ?", String.valueOf(c.getBookBean().getPrimaryKey()))
                        .listen(new UpdateOrDeleteCallback() {
                            @Override
                            public void onFinish(int rowsAffected) {
                                //不写这个回调无法触发数据库操作。。。。
                            }
                        });
            }
        }
        mNewCollectBookList.removeAll(temp);
        if (isAttachView()){
            getMVPView().setCollectData(mOldCollectBookList,mNewCollectBookList);
        }
    }

    @Override
    public void selectAll(boolean isSelect) {
        mOldCollectBookList.clear();
        mOldCollectBookList.addAll(mNewCollectBookList);
        ListIterator<CollectVO> listIterator = mNewCollectBookList.listIterator();
        while (listIterator.hasNext()){
            CollectVO c = listIterator.next().clone();
            c.setSelect(isSelect);
            listIterator.set(c);
        }
        if (isAttachView()){
            getMVPView().setCollectData(mOldCollectBookList,mNewCollectBookList);
        }
    }

}
