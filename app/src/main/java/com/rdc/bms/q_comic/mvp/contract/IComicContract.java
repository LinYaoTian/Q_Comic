package com.rdc.bms.q_comic.mvp.contract;


import com.rdc.bms.q_comic.bean.ChapterBean;
import com.rdc.bms.q_comic.bean.ComicBean;

import java.util.List;

public interface IComicContract {
    interface View{
        void finishedGetComicData(List<ChapterBean> chapterList);
        void updateComic(List<ComicBean> newComicList,List<ComicBean> oldComicList);
        void rvDirScrollTo(int index);
        void rvComicScrollTo(int index);
        void onError(String msg);
        void setChapterTip(String title, String count, String total);
        void setDirItemSelected(int index);
        ComicBean getReadingComic();
    }

    interface Presenter{
        void setImageQuality(int quality);
        List<ChapterBean> getDirList();
        List<ComicBean> getComicList();
        int getCurComicListFirstIndex();
        void indexRvDirFirst();
        void getComic(String comicId,long startChapterId);
        void updateComicData(ChapterBean data);
        void sendMessage(int what,Object obj);
        ChapterBean getChapterById(long chapterId);
        void onRecyclerViewScroll(int dy,int firstVisibleItemIndex,int lastVisibleItemIndex);
        ChapterBean getReadingChapter();
        void saveLastRecordChapter(ChapterBean chapterBean);
        void saveReadChapter(ChapterBean chapterBean);
    }
}
