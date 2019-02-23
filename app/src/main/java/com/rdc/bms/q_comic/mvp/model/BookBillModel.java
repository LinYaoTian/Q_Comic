package com.rdc.bms.q_comic.mvp.model;

import com.rdc.bms.q_comic.bean.BookBillBean;
import com.rdc.bms.q_comic.config.Constant;
import com.rdc.bms.q_comic.mvp.model.dto.BookBillDetailContainerDTO;
import com.rdc.bms.q_comic.mvp.model.dto.BookBillDetailHeadDTO;
import com.rdc.bms.q_comic.mvp.model.dto.BookListDTO;
import com.rdc.bms.q_comic.mvp.model.service.BookBillService;
import com.rdc.bms.q_comic.mvp.model.vo.BookBillDetailVO;
import com.rdc.bms.q_comic.util.RetrofitUtil;

import org.litepal.LitePal;

import java.util.Date;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 书单
 */
public class BookBillModel {
    public void getBookBillList(Observer<BookListDTO> observer, final String tagName, final int page){
        RetrofitUtil.bind(Constant.BASE_URL_FIDDLER)
                .create(BookBillService.class)
                .getBookBillList(
                        page,
                        tagName,
                        Constant.CLIENT_TYPE,
                        Constant.CLIENT_VERSION,
                        new Date().getTime())
                .map(new Function<BookListDTO, BookListDTO>() {
                    @Override
                    public BookListDTO apply(BookListDTO bookListDTO) throws Exception {
                        if (bookListDTO.isSuccess()){
                            return bookListDTO;
                        }else {
                            throw new Exception(bookListDTO.getMsg());
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getBookBillDetailHead(Observer<BookBillDetailHeadDTO> observer, final String bookBillId){
        RetrofitUtil.bind(Constant.BASE_URL_FIDDLER)
                .create(BookBillService.class)
                .getBookBillDetailHead(
                        bookBillId)
                .map(new Function<BookBillDetailHeadDTO, BookBillDetailHeadDTO>() {
                    @Override
                    public BookBillDetailHeadDTO apply(BookBillDetailHeadDTO bookBillDetailHeadDTO) throws Exception {
                        if (bookBillDetailHeadDTO.isSuccess()){
                            //查询此书单是否被收藏
                            BookBillDetailVO vo = bookBillDetailHeadDTO.getBookBillDetailVO();
                            BookBillBean bookBillBean = LitePal
                                    .where("bookBillId = ?",bookBillId)
                                    .findFirst(BookBillBean.class);
                            vo.setCollected(bookBillBean != null);
                            vo.setBookBillBean(bookBillBean);
                            return bookBillDetailHeadDTO;
                        }else {
                            throw new Exception(bookBillDetailHeadDTO.getMsg());
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    public void getBookBillDetailContainer(Observer<BookBillDetailContainerDTO> observer, String bookId, int pageSize){
        RetrofitUtil.bind(Constant.BASE_URL_FIDDLER)
                .create(BookBillService.class)
                .getBookBillContainer(
                        bookId,pageSize)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
