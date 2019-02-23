package com.rdc.bms.q_comic.mvp.model;

import com.rdc.bms.q_comic.bean.BookBean;
import com.rdc.bms.q_comic.bean.ChapterBean;
import com.rdc.bms.q_comic.config.Constant;
import com.rdc.bms.q_comic.mvp.model.dto.ComicDTO;
import com.rdc.bms.q_comic.mvp.model.dto.ComicInfoDTO;
import com.rdc.bms.q_comic.mvp.model.service.ComicService;
import com.rdc.bms.q_comic.mvp.model.vo.CollectVO;
import com.rdc.bms.q_comic.util.RetrofitUtil;

import org.litepal.LitePal;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class CollectModel {
    public void updateAll(List<CollectVO> list, Observer<CollectVO> observer) {
        Observable.fromIterable(list)
                .map(new Function<CollectVO, ComicDTO>() {
                    @Override
                    public ComicDTO apply(CollectVO collectVO) throws Exception {
                        Observable<ComicDTO> observable = RetrofitUtil.bind(Constant.BASE_URL_WEB_JSON)
                                .create(ComicService.class)
                                .getComic(collectVO.getBookBean().getBookId());
                        return observable.blockingFirst();
                    }
                }).map(new Function<ComicDTO, CollectVO>() {
                    @Override
                    public CollectVO apply(ComicDTO comicDTO) throws Exception {
                        try {
                            if (comicDTO.isSuccess()){
                                ChapterBean netRecentChapter = comicDTO.toLastChapter();
                                BookBean bookBean = LitePal
                                        .where("bookId = ?",String.valueOf(comicDTO.getData().get(0).getComicid()))
                                        .findFirst(BookBean.class);
                                //本地最新话ID
                                long localRecentChapterId = bookBean.getRecentChapterId();
                                //网络最新话
                                if (netRecentChapter.getChapterId() > localRecentChapterId){
                                    //有更新
                                    bookBean.setUpdate(true);
                                    bookBean.setRecentChapter(netRecentChapter.getChapterNum());
                                    bookBean.setRecentChapterId(netRecentChapter.getChapterId());
                                    bookBean.update(bookBean.getPrimaryKey());
                                }
                                return new CollectVO(bookBean);
                            }else {
                                throw new Exception("ComicInfoDTO not success !");
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            throw e;
                        }
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .unsubscribeOn(Schedulers.io())
                        .subscribe(observer);
    }
}
