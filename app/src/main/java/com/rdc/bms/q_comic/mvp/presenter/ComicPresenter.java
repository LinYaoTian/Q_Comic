package com.rdc.bms.q_comic.mvp.presenter;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.LongSparseArray;

import com.rdc.bms.q_comic.base.BasePresenter;
import com.rdc.bms.q_comic.bean.BookBean;
import com.rdc.bms.q_comic.bean.ChapterBean;
import com.rdc.bms.q_comic.bean.ComicBean;
import com.rdc.bms.q_comic.config.Constant;
import com.rdc.bms.q_comic.mvp.contract.IComicContract;
import com.rdc.bms.q_comic.mvp.model.ComicModel;
import com.rdc.bms.q_comic.mvp.model.dto.ComicInfoDTO;
import com.rdc.bms.q_comic.util.ImageUtil;


import org.litepal.LitePal;
import org.litepal.crud.callback.SaveCallback;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ComicPresenter extends BasePresenter<IComicContract.View>
        implements IComicContract.Presenter {

    private ComicModel mComicModel;
    private Handler mHandler;
    //Adapter中每次只会放总共3章的漫画
    private List<ComicBean> mPreChapterList;//前一章
    private List<ComicBean> mCurChapterList;//当前浏览的章节
    private List<ComicBean> mNextChapterList;//后一张
    private List<ComicBean> mOldComicList;
    private List<ChapterBean> mDirList;
    private LongSparseArray<ChapterBean> mChapterMap;//存储key-value=chapterId-ChapterBean
    private int mRvComicFirstVisibleItemIndex = 0;
    private int mImageQuality;


    public static final int QUALITY_LOW = 0;
    public static final int QUALITY_MIDDLE = 1;
    public static final int QUALITY_HIGH = 2;

    public static final int SCROLL_STATE_IDLE = 0;//闲置
    public static final int SCROLL_STATE_RUNNING = 1;//滑动
    public static final int NEED_UPDATE = 2;//需要更新数据

    public ComicPresenter(){
        mComicModel = new ComicModel();
        mOldComicList = new ArrayList<>();
        mDirList = new ArrayList<>();
        mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            boolean isIDLE = true;
            boolean needUpdate = false;
            ChapterBean data = null;
            @Override
            public boolean handleMessage(Message msg) {
                // 使用Handler刷新数据，因为RecyclerView在滑动时或onComputingLayout()计算布局时
                // notifyDataChanged()可能会造成出错
                switch (msg.what){
                    case SCROLL_STATE_IDLE:
                        isIDLE = true;
                        if (needUpdate){
                            updateComicData(data);
                            needUpdate = false;
                        }
                        break;
                    case SCROLL_STATE_RUNNING:
                        isIDLE = false;
                        break;
                    case NEED_UPDATE:
                        if (!isIDLE){
                            needUpdate = true;
                            data = (ChapterBean) msg.obj;
                        }else {
                            needUpdate = false;
                            data = null;
                            updateComicData((ChapterBean) msg.obj);
                        }
                        break;
                }
                return false;
            }
        });
    }


    @Override
    public void updateComicData(ChapterBean data) {
        if (data != null){
            ChapterBean preChapter = mChapterMap.get(data.getPreChapterId());
            mPreChapterList = (preChapter == null ?
                    new ArrayList<ComicBean>(): toChapterComicList(preChapter));
            ChapterBean nextChapter = mChapterMap.get(data.getNextChapterId());
            mNextChapterList = (nextChapter == null ?
                    new ArrayList<ComicBean>(): toChapterComicList(nextChapter));
            mCurChapterList = toChapterComicList(data);
            List<ComicBean> newComicList = new ArrayList<>();
            newComicList.addAll(mPreChapterList);
            newComicList.addAll(mCurChapterList);
            newComicList.addAll(mNextChapterList);
            if (isAttachView()){
                getMVPView().updateComic(newComicList,mOldComicList);
            }
            mOldComicList.clear();
            mOldComicList.addAll(newComicList);
        }
    }

    @Override
    public void sendMessage(int what, Object obj) {
        mHandler.sendMessage(mHandler.obtainMessage(what,obj));
    }

    @Override
    public ChapterBean getChapterById(long chapterId) {
        return mChapterMap.get(chapterId);
    }

    @Override
    public void onRecyclerViewScroll(int dy,int firstVisibleItemIndex,int lastVisibleItemIndex) {
        mRvComicFirstVisibleItemIndex = firstVisibleItemIndex;
        ComicBean pre_LastComic = mOldComicList.get(firstVisibleItemIndex);
        ComicBean next_FirstComic = mOldComicList.get(lastVisibleItemIndex);
        //这里逻辑比较复杂，其中 else if 那里我还没搞懂，只是找到某种规律
//        Log.d("ComicPresenter", "firstVisibleItemIndex="+firstVisibleItemIndex);
//        Log.d("ComicPresenter", "dy="+dy+",preListSize!=0:"+(mPreChapterList.size() != 0)+
//                ",pre_LastComic.isLastPage()="+pre_LastComic.isLastPage()+",flag="+pre_LastComic.getBookBeanId().equals(mPreChapterList.get(mPreChapterList.size()-1).getBookBeanId()));
        if (dy < 0 && mPreChapterList.size() != 0 && pre_LastComic.isLastPage() &&
                pre_LastComic.getComicId().equals(mPreChapterList.get(mPreChapterList.size()-1).getComicId())){
            ChapterBean preChapter = mChapterMap.get(pre_LastComic.getChapterId());
            mHandler.sendMessage(mHandler.obtainMessage(NEED_UPDATE,preChapter));
            return;
        }else if (dy < 0 && firstVisibleItemIndex == 0){
            // firstVisibleItemIndex 随着内容向下滚动（即往上浏览看以前的章节），会逐渐减小为0？
            // 这里没太懂，可能是和我数据刷新的有关
            // 当前第一个可见的Bean
            ComicBean firstVisibleComicBean = mOldComicList.get(firstVisibleItemIndex);
            long preChapterId = mChapterMap.get(firstVisibleComicBean.getChapterId()).getPreChapterId();
            if (preChapterId != ChapterBean.NO_CHAPTER_ID){
                ChapterBean preChapter = mChapterMap.get(preChapterId);
                mHandler.sendMessage(mHandler.obtainMessage(NEED_UPDATE,preChapter));
            }
            return;
        }
        if (dy > 0 && mNextChapterList.size()!= 0 && next_FirstComic.isFirstPage() &&
                next_FirstComic.getComicId().equals(mNextChapterList.get(0).getComicId())){
            ChapterBean nextChapter = mChapterMap.get(next_FirstComic.getChapterId());
            mHandler.sendMessage(mHandler.obtainMessage(NEED_UPDATE,nextChapter));
        }else if (dy > 0 && lastVisibleItemIndex == mOldComicList.size()-1){
            //同样，这里的 mLastVisibleItemIndex 会逐渐变为 mComicBeanList.size()-1
            ComicBean lastVisibleComicBean = mOldComicList.get(lastVisibleItemIndex);
            long nextChapterId = mChapterMap.get(lastVisibleComicBean.getChapterId()).getNextChapterId();
            if (nextChapterId != ChapterBean.NO_CHAPTER_ID){
                mHandler.sendMessage(mHandler.obtainMessage(NEED_UPDATE, mChapterMap.get(nextChapterId)));
            }
        }
    }

    @Override
    public ChapterBean getReadingChapter() {
        if (isAttachView()){
            ComicBean curComic = getMVPView().getReadingComic();
            if (curComic != null){
                return mChapterMap.get(curComic.getChapterId());
            }
        }
        return null;
    }

    @Override
    public void setImageQuality(int quality) {
        if (quality == mImageQuality){
            return;
        }
        mImageQuality = quality;
        //重新加载漫画
        updateComicData(getReadingChapter());
    }

    @Override
    public List<ChapterBean> getDirList() {
        return mDirList;
    }

    @Override
    public List<ComicBean> getComicList() {
        return mOldComicList;
    }

    @Override
    public int getCurComicListFirstIndex() {
        return mPreChapterList.size();
    }

    @Override
    public void indexRvDirFirst() {
        if (mOldComicList.size() != 0){
            ChapterBean chapter = getReadingChapter();
            int index = 0;
            for (int i = 0; i < mDirList.size(); i++) {
                if (mDirList.get(i).getChapterId() == chapter.getChapterId()){
                    index = i;
                    break;
                }
            }
            if (isAttachView()){
                getMVPView().rvDirScrollTo(index);
                getMVPView().setDirItemSelected(index);
            }
        }
    }

    @Override
    public void getComic(String bookId, final long startChapterId) {
        mComicModel.getComic(new Observer<ComicInfoDTO>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ComicInfoDTO dto) {
                if (isAttachView()){
                    if (dto.isSuccess()){
                        List<ChapterBean> list = dto.toChapterList();
                        mDirList = list;
                        mChapterMap = new LongSparseArray<>(list.size());
                        for (ChapterBean bean : list) {
                            mChapterMap.put(bean.getChapterId(),bean);
                        }
                        ChapterBean chapter = getChapterById(startChapterId);
                        //更新漫画Container
                        updateComicData(chapter);
                        //漫画Container滚动到指定章节首页漫画
                        getMVPView().rvComicScrollTo(mPreChapterList.size());
                        //更新RightLayout的目录
                        getMVPView().finishedGetComicData(mDirList);
                        //更新TopLayout
                        getMVPView().setChapterTip(
                                chapter.getChapterNum(),
                                String.valueOf(chapter.getStartVal()),
                                String.valueOf(chapter.getEndVal()));
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


    private List<ComicBean> toChapterComicList(ChapterBean chapter){
        if (chapter == null)
            return new ArrayList<>();
        List<ComicBean> list= new ArrayList<>();
        int c = 1;
        for (int i = chapter.getStartVal(); i <= chapter.getEndVal(); i++) {
            ComicBean comicBean = new ComicBean();
            switch (mImageQuality){
                case QUALITY_HIGH:
                    comicBean.setImageUrl(ImageUtil.toComicImageUrl(chapter.getComicImage().getHigh()
                            ,i));
                    break;
                case QUALITY_MIDDLE:
                    comicBean.setImageUrl(ImageUtil.toComicImageUrl(chapter.getComicImage().getMiddle()
                            ,i));
                    break;
                case QUALITY_LOW:
                    comicBean.setImageUrl(ImageUtil.toComicImageUrl(chapter.getComicImage().getLow()
                            ,i));
                    break;
            }
            comicBean.setFirstPage(i == chapter.getStartVal());
            comicBean.setLastPage(i == chapter.getEndVal());
            comicBean.setChapterId(chapter.getChapterId());
            comicBean.setPage(c++);
            list.add(comicBean);
        }
        return list;
    }

    @Override
    public void saveLastRecordChapter(ChapterBean chapterBean) {
        BookBean bookBean = LitePal.where("bookId = ?",chapterBean.getBookId())
                .findFirst(BookBean.class);
        if (chapterBean.getChapterId() >= bookBean.getRecentChapterId()){
            //读者已经读了最新章节
            bookBean.setToDefault("isUpdate");
        }
        bookBean.setLastRecordChapter(chapterBean.getChapterNum());
        bookBean.setLastRecordChapterId(chapterBean.getChapterId());
        bookBean.update(bookBean.getPrimaryKey());
    }

    @Override
    public void saveReadChapter(ChapterBean chapterBean) {
        chapterBean.saveOrUpdateAsync(
                "chapterId = ? and bookId = ?",
                chapterBean.getChapterId()+"",
                chapterBean.getBookId()).listen(new SaveCallback() {
            @Override
            public void onFinish(boolean success) {
                Log.d("LYT", "onFinish: "+success);
            }
        });
    }
}
