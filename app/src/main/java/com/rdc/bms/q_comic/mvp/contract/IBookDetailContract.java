package com.rdc.bms.q_comic.mvp.contract;

import com.rdc.bms.q_comic.bean.BookDetailVO;
import com.rdc.bms.q_comic.bean.ChapterBean;

import java.util.List;

public interface IBookDetailContract {
    interface View{
        void setBookDetailData(BookDetailVO bean);
        void setReadRecord(List<ChapterBean> newList);
        void setLastRecordChapter(ChapterBean chapter,boolean first);
        void onError(String msg);
    }

    interface Presenter{
        void getBookDetailData(String bookId);
        //获取读者读了哪些章节
        void getAllRecord();
        //获取最近读的章节（用于btnStartRead显示，和保存数据库）
        void getLastRecordChapter();
        void collect(boolean isCollect);
    }
}
