package com.rdc.bms.q_comic.mvp.model.dto;

import com.rdc.bms.q_comic.bean.RankBean;

import java.util.List;

public class RankDTO {


    List<String> kinkList;
    List<RankBean> comicRankList;

    public RankDTO(List<String> kinkList, List<RankBean> comicRankList) {
        this.kinkList = kinkList;
        this.comicRankList = comicRankList;
    }

    public RankDTO() {
    }

    public List<String> getKinkList() {
        return kinkList;
    }

    public void setKinkList(List<String> kinkList) {
        this.kinkList = kinkList;
    }

    public List<RankBean> getComicRankList() {
        return comicRankList;
    }

    public void setComicRankList(List<RankBean> comicRankList) {
        this.comicRankList = comicRankList;
    }
}
