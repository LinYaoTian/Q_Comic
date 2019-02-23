package com.rdc.bms.q_comic.bean.rv_cell;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.rdc.bms.easy_rv_adapter.base.BaseRvCell;
import com.rdc.bms.easy_rv_adapter.base.BaseRvViewHolder;
import com.rdc.bms.q_comic.R;
import com.rdc.bms.q_comic.bean.BannerBean;
import com.rdc.bms.q_comic.config.ItemType;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class BannerCell extends BaseRvCell<List<BannerBean>> {

    private List<String> mImageUrlList;
    private List<String> mTitleList;

    public BannerCell(List<BannerBean> bannerBeans) {
        super(bannerBeans);
        mImageUrlList = new ArrayList<>();
        mTitleList = new ArrayList<>();
        for (BannerBean bannerBean : bannerBeans) {
            mImageUrlList.add(bannerBean.getImageUrl());
            mTitleList.add(bannerBean.getTitle());
        }
    }

    @Override
    public void releaseResource() {
    }

    @Override
    public int getItemType() {
        return ItemType.TYPE_BANNER;
    }

    @Override
    public BaseRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_banner,parent,false);
        return new BaseRvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRvViewHolder holder, int position) {
        Banner banner = (Banner) holder.getView(R.id.banner_cell_banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });

        banner.setImages(mImageUrlList);
        banner.setBannerTitles(mTitleList);
        banner.start();
    }
}
