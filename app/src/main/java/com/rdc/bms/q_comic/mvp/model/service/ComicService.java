package com.rdc.bms.q_comic.mvp.model.service;

import com.rdc.bms.q_comic.config.Constant;
import com.rdc.bms.q_comic.mvp.model.dto.ComicDTO;
import com.rdc.bms.q_comic.mvp.model.dto.ComicInfoDTO;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ComicService {
    @GET(Constant.COMIC_INFO)
    Observable<ComicInfoDTO> getComicInfo(@Query("comic_id") String comicId);
    @GET(Constant.COMIC)
    Observable<ComicDTO> getComic(@Query("comic_id") String comicId);
}
