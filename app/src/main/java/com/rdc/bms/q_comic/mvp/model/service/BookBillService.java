package com.rdc.bms.q_comic.mvp.model.service;

import com.rdc.bms.q_comic.config.Constant;
import com.rdc.bms.q_comic.mvp.model.dto.BookBillDetailContainerDTO;
import com.rdc.bms.q_comic.mvp.model.dto.BookBillDetailHeadDTO;
import com.rdc.bms.q_comic.mvp.model.dto.BookListDTO;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BookBillService {

    @POST(Constant.BOOK_BILL)
    Observable<BookListDTO> getBookBillList(@Query("page") int page,
                                            @Query("tagname") String tagName,
                                            @Query("client-type") String clientType,
                                            @Query("client-version") String clientVersion,
                                            @Query("localtime") long localTime);

    @POST(Constant.BOOK_BILL_HEAD)
    Observable<BookBillDetailHeadDTO> getBookBillDetailHead(@Query("book_id") String bookId);

    @POST(Constant.BOOK_BILL_CONTAINER)
    Observable<BookBillDetailContainerDTO> getBookBillContainer(@Query("book_id") String bookId,@Query("page_size") int pageSize);

}
