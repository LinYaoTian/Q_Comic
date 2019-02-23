package com.rdc.bms.q_comic.bean;

import org.litepal.crud.LitePalSupport;

public class SearchHistoryBean extends LitePalSupport {

    private String key;

    public SearchHistoryBean(String key) {
        this.key = key;
    }

    public SearchHistoryBean() {
    }

    public String getKey() {
        return key == null ? "" : key;
    }

    public void setKey(String key) {
        this.key = key == null ? "" : key;
    }
}
