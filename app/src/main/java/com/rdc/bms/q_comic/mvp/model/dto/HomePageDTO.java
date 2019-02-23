package com.rdc.bms.q_comic.mvp.model.dto;

import com.rdc.bms.q_comic.bean.BannerBean;
import com.rdc.bms.q_comic.bean.ItemBean;

import java.util.List;

public class HomePageDTO {

    private List<BannerBean> bannerList;
    private List<ItemBean> itemList;

    public HomePageDTO(List<BannerBean> bannerList, List<ItemBean> itemList) {
        this.bannerList = bannerList;
        this.itemList = itemList;
    }

    public HomePageDTO(){

    }

    public List<BannerBean> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<BannerBean> bannerList) {
        this.bannerList = bannerList;
    }

    public List<ItemBean> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemBean> itemList) {
        this.itemList = itemList;
    }
}
