package com.rdc.bms.q_comic.mvp.model;

import com.rdc.bms.q_comic.bean.BookBean;
import com.rdc.bms.q_comic.bean.BookDetailVO;
import com.rdc.bms.q_comic.bean.ChapterBean;
import com.rdc.bms.q_comic.mvp.model.db.CollectBean;
import com.rdc.bms.q_comic.config.Constant;
import com.rdc.bms.q_comic.mvp.model.db.HistoryBean;
import com.rdc.bms.q_comic.util.JsoupUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.litepal.LitePal;
import org.litepal.crud.callback.SaveCallback;

import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BookDetailModel {

    String TAG = "LYT";

    public void getComicDetailData(Observer<BookDetailVO> observer, final String bookId){
        Observable.create(new ObservableOnSubscribe<BookDetailVO>() {
            @Override
            public void subscribe(ObservableEmitter<BookDetailVO> emitter){
                try {
                    Element element = Jsoup.connect(Constant.BASE_URL_WEB+bookId).get().body();
                    BookDetailVO bean = JsoupUtil.toComicDetail(element);
                    if (bean == null){
                        emitter.onError(new Exception(Constant.TIP_ERROR_GET_DATA));
                    }else {
                        BookBean bookBean = LitePal
                                .where("bookId = ? ",bookId)
                                .findFirst(BookBean.class);
                        if (bookBean == null){
                            //不存在，则保存
                            bookBean = saveBookBean(bean,bookId);
                            addHistory(bookBean);
                            bean.getComicTopInfo().setCollected(false);
                        }else {
                            //查询是否被收藏
                            int count = LitePal.
                                    where("bookPrimaryKey = ?",bookBean.getPrimaryKey()+"")
                                    .count(CollectBean.class);
                            bean.getComicTopInfo().setCollected(count > 0);
                        }
                        //目录界面需要显示的小红点（目前没有做缓存，所以这里处理较复杂）
                        setRedPoint(bean.getBookTabDirVO().getChapterList(),bookBean.getFirstOpenLastChapterId());
                        bean.setBookBean(bookBean);
                        emitter.onNext(bean);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    emitter.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 保存BookBean所需信息
     * @param vo
     * @param bookId
     * @return
     */
    private BookBean saveBookBean(BookDetailVO vo, String bookId){
        BookBean bookBean = new BookBean();
        bookBean.setBookId(bookId);
        List<ChapterBean> dirList = vo.getBookTabDirVO().getChapterList();
        bookBean.setFirstOpenLastChapterId(dirList.get(0).getChapterId());
        bookBean.setRecentChapterId(dirList.get(0).getChapterId());
        bookBean.setRecentChapter(dirList.get(0).getChapterNum());
        bookBean.setCoverUrl(vo.getComicTopInfo().getCoverUrl());
        bookBean.setName(vo.getComicTopInfo().getName());
        bookBean.save();
        return bookBean;
    }

    /**
     * 向数据库添加历史记录
     * @param bookBean
     */
    private void addHistory(BookBean bookBean){
        HistoryBean historyBean = new HistoryBean();
        historyBean.setBookPrimaryKey(bookBean.getPrimaryKey());
        historyBean.setTime(new Date());
        historyBean.saveAsync().listen(new SaveCallback() {
            @Override
            public void onFinish(boolean success) {

            }
        });
    }

    /**
     * 目录界面有的章节需要显示的小红点
     * @param list
     * @param firstOpenLastchapterId
     */
    private void setRedPoint(List<ChapterBean> list,long firstOpenLastchapterId){
        for (ChapterBean bean : list) {
            bean.setHasRedPoint(bean.getChapterId() > firstOpenLastchapterId);
        }
    }

}
