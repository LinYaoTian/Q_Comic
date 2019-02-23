package com.rdc.bms.q_comic.mvp.model.vo;

import com.rdc.bms.q_comic.bean.ChapterBean;

import java.util.List;

public class BookTabDirVO {

    private String updateTime;
    private List<ChapterBean> chapterList;

    public String getUpdateTime() {
        return updateTime == null ? "" : updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? "" : updateTime;
    }

    public List<ChapterBean> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<ChapterBean> chapterList) {
        this.chapterList = chapterList;
    }
}
