package com.rdc.bms.q_comic.mvp.model.service;

import com.rdc.bms.q_comic.config.Constant;
import com.rdc.bms.q_comic.mvp.model.dto.HotKeyDTO;
import com.rdc.bms.q_comic.mvp.model.dto.SearchDTO;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {
    @GET(Constant.SEARCH)
    Observable<SearchDTO> search(@Query("type") String type,
                                 @Query("key") String key,
                                 @Query("page") int page);

    @GET(Constant.HOT_KEYS)
    Observable<HotKeyDTO> getHotKeys();
}
