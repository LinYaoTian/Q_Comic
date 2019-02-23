package com.rdc.bms.q_comic.mvp.presenter;

import android.util.LongSparseArray;

import com.rdc.bms.q_comic.base.BasePresenter;
import com.rdc.bms.q_comic.bean.BookBean;
import com.rdc.bms.q_comic.bean.BookDetailVO;
import com.rdc.bms.q_comic.bean.ChapterBean;
import com.rdc.bms.q_comic.mvp.model.db.CollectBean;
import com.rdc.bms.q_comic.config.Constant;
import com.rdc.bms.q_comic.mvp.contract.IBookDetailContract;
import com.rdc.bms.q_comic.mvp.model.BookDetailModel;

import org.litepal.LitePal;
import org.litepal.crud.callback.FindMultiCallback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BookDetailPresenter extends BasePresenter<IBookDetailContract.View>
        implements IBookDetailContract.Presenter {

    String TAG = "LYT";

    private BookDetailModel mBookDetailModel;
    private List<ChapterBean> mOldChapterList;
    private LongSparseArray<Integer> mChapterMap;//存储(ChapterId-Chapter在List中的index)
    private BookBean mBookBean;
    private String mBookId;

    public BookDetailPresenter(){
        mBookDetailModel = new BookDetailModel();
    }

    @Override
    public void getBookDetailData(String bookId) {
        mBookId = bookId;
        mBookDetailModel.getComicDetailData(new Observer<BookDetailVO>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BookDetailVO bookDetailVO) {
                if (isAttachView()){
                    if (bookDetailVO.isSuccess()){
                        mBookBean = bookDetailVO.getBookBean();
                        mOldChapterList = bookDetailVO.getBookTabDirVO().getChapterList();
                        mChapterMap = new LongSparseArray<>(mOldChapterList.size());
                        for (int i = 0; i < mOldChapterList.size(); i++) {
                            mChapterMap.put(mOldChapterList.get(i).getChapterId(),i);
                        }
                        getMVPView().setBookDetailData(bookDetailVO);
                        getAllRecord();//获取阅读记录
                        getLastRecordChapter();//获取最近读过的
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
        },bookId);
    }

    @Override
    public void getAllRecord() {
         LitePal.where("bookId = ?",mBookId)
                .findAsync(ChapterBean.class).listen(new FindMultiCallback<ChapterBean>() {
                    @Override
                    public void onFinish(List<ChapterBean> list) {
                        List<ChapterBean> newList = new ArrayList<>(mOldChapterList);
                        for (ChapterBean bean : list) {
                            Integer index = mChapterMap.get(bean.getChapterId());
                            if (index != null){
                                ChapterBean chapterBean = newList.get(index).clone();
                                chapterBean.setRead(true);
                                newList.set(index,chapterBean);
                            }
                        }
                        if (isAttachView()){
                            getMVPView().setReadRecord(newList);
                        }
                    }
                });
    }

    @Override
    public void getLastRecordChapter() {
        ChapterBean chapterBean = new ChapterBean();
        mBookBean = LitePal.where("bookId = ?",mBookId).findFirst(BookBean.class);
        if (mBookBean.getLastRecordChapter().equals("")
                && mBookBean.getLastRecordChapterId() == 0){
            //第一次阅读！
            chapterBean.setBookId(mBookId);
            chapterBean.setChapterId(mOldChapterList.get(mOldChapterList.size()-1).getChapterId());
            chapterBean.setChapterNum(mOldChapterList.get(mOldChapterList.size()-1).getChapterNum());
            getMVPView().setLastRecordChapter(chapterBean,true);
        }else {
            chapterBean.setBookId(mBookId);
            chapterBean.setChapterNum(mBookBean.getLastRecordChapter());
            chapterBean.setChapterId(mBookBean.getLastRecordChapterId());
            getMVPView().setLastRecordChapter(chapterBean,false);
        }

    }

    @Override
    public void collect(boolean isCollect) {
        if (isCollect){
            //添加收藏
            CollectBean bean = new CollectBean();
            bean.setBookPrimaryKey(mBookBean.getPrimaryKey());
            bean.setTime(new Date());
            bean.save();
        }else {
            LitePal.where("bookPrimaryKey = ?",String.valueOf(mBookBean.getPrimaryKey()))
                    .findFirst(CollectBean.class).delete();
        }
    }
}
