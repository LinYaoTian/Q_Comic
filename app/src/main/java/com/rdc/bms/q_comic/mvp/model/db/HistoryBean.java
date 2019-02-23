package com.rdc.bms.q_comic.mvp.model.db;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class HistoryBean extends LitePalSupport {
    @Column(unique = true)
    private long bookPrimaryKey;
    private Date time;

    public long getBookPrimaryKey() {
        return bookPrimaryKey;
    }

    public void setBookPrimaryKey(long bookPrimaryKey) {
        this.bookPrimaryKey = bookPrimaryKey;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
