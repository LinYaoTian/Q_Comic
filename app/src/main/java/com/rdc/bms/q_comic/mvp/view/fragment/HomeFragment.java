package com.rdc.bms.q_comic.mvp.view.fragment;

import com.bumptech.glide.Glide;
import com.rdc.bms.easy_rv_adapter.base.RvSimpleAdapter;

import android.content.Context;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rdc.bms.q_comic.R;
import com.rdc.bms.q_comic.base.BaseLazyFragment;
import com.rdc.bms.q_comic.bean.BannerBean;
import com.rdc.bms.q_comic.bean.ItemBean;
import com.rdc.bms.q_comic.config.Constant;
import com.rdc.bms.q_comic.listener.OnComicClickListener;
import com.rdc.bms.q_comic.util.ScreenUtil;
import com.rdc.bms.q_comic.widget.ListenOffsetYNestedScrollView;
import com.rdc.bms.q_comic.listener.OnScrollYChangeForAlphaListener;
import com.rdc.bms.q_comic.mvp.contract.IHomeContract;
import com.rdc.bms.q_comic.mvp.presenter.HomePresenter;
import com.rdc.bms.q_comic.util.StartActUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 使用ScrollView.addView()方式实现HomeFragment
 */
public class HomeFragment extends BaseLazyFragment<HomePresenter> implements IHomeContract.View {
    @BindView(R.id.iv_search_fragment_home)
    ImageView mIvSearch;
    @BindView(R.id.iv_left_fragment_home)
    ImageView mIvLeft;
    @BindView(R.id.cl_top_fragment_home)
    ConstraintLayout mClTopLayout;
    @BindView(R.id.tv_title_fragment_home)
    TextView mTvTitle;
    @BindView(R.id.ll_scroll_inner_fragment_home)
    LinearLayout mLLScrollContainer;
    @BindView(R.id.banner_fragment_home)
    Banner mBanner;
    @BindView(R.id.scrollView_fragment_home)
    ListenOffsetYNestedScrollView mScrollView;
    @BindView(R.id.iv_bg_left_fragment_home)
    View viewBgLeft;
    @BindView(R.id.iv_bg_search_fragment_home)
    View viewBgSearch;
    @BindView(R.id.refreshLayout_fragment_home)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.ll_book_bill_fragment_home)
    LinearLayout mLlBookBill;
    @BindView(R.id.ll_rank_fragment_home)
    LinearLayout mLlRank;

    @Override
    protected HomePresenter getInstance() {
        return new HomePresenter();
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    protected void initView() {
        // 200dp为banner高度,75dp为标题栏高度
        mScrollView.setOffsetDistance(ScreenUtil.dip2px(mBaseActivity,200-75));
        mScrollView.setOnScrollYChangeForAlphaListener(new OnScrollYChangeForAlphaListener() {
            @Override
            public void changeTopLayoutTransport(int alpha) {
                setTopLayoutTransparency(alpha);
            }
        });
        setTopLayoutTransparency(0);
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPresenter.getHomePageData();
            }
        });
    }

    @Override
    protected void setListener() {
        mLlBookBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActUtil.toBookBillAct(mBaseActivity,Constant.TYPE_BOOK_BILL);
            }
        });
        mLlRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActUtil.toBookBillAct(mBaseActivity,Constant.TYPE_RANK);
            }
        });
        mIvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActUtil.toSearchAct(mBaseActivity);
            }
        });
    }

    @Override
    protected void lazyLoad() {
        if (!isVisible || !isPrepared || isLoaded){
            return;
        }
        mPresenter.getHomePageData();
        isLoaded = true;
    }

    /**
     * 设置标题栏及其内容透明度
     * 0:完全透明
     * 255:完全不透明
     * @param alpha
     */
    public void setTopLayoutTransparency(int alpha){
        mClTopLayout.getBackground().mutate().setAlpha(alpha);
        float scale = alpha/255f;
        mTvTitle.setAlpha(scale);
        //白->红
        mIvSearch.setColorFilter(new ColorMatrixColorFilter(new float[]{
                0,0,0,0,255-scale*12,
                0,0,0,0,255-scale*153,
                0,0,0,0,255-scale*172,
                0,0,0,1,0
        }));
        viewBgLeft.setAlpha(1-scale);
        viewBgSearch.setAlpha(1-scale);
    }

    @Override
    public void showData(List<BannerBean> bannerList, List<ItemBean> itemBeanList) {
        if (mLLScrollContainer.getChildCount() > 2){
            mLLScrollContainer.removeViews(2,mLLScrollContainer.getChildCount()-2);
        }
        initBanner(bannerList);
        addAllComicItem(itemBeanList);
        mRefreshLayout.finishRefresh();
    }

    /**
     * 添加分类漫画项
     * @param itemBeanList
     */
    private void addAllComicItem(List<ItemBean> itemBeanList) {
        for (ItemBean bean : itemBeanList) {
            //初始化View
            View rootView = LayoutInflater.from(mBaseActivity).inflate(R.layout.cell_item,null);
            ImageView ivIcon = rootView.findViewById(R.id.iv_icon_cell_item);
            TextView tvTitle = rootView.findViewById(R.id.tv_title_cell_item);
            TextView tvIntro = rootView.findViewById(R.id.tv_intro_cell_item);
            TextView tvMore = rootView.findViewById(R.id.tv_more_cell_item);
            RecyclerView rvComicList = rootView.findViewById(R.id.rv_book_cell_item);
            //赋值
            Glide.with(mBaseActivity).load(bean.getIconResId()).into(ivIcon);
            tvTitle.setText(bean.getTitle());
            tvIntro.setText(bean.getIntro());
            rvComicList.setLayoutManager(new LinearLayoutManager(
                    mBaseActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false));
            RvSimpleAdapter rvSimpleAdapter = new RvSimpleAdapter();
            rvComicList.setAdapter(rvSimpleAdapter);
            rvSimpleAdapter.addAll(bean.getComicCellList(new OnComicClickListener() {
                @Override
                public void onClick(String comicId) {
                    StartActUtil.toBookDetail(mBaseActivity,comicId);
                }
            }));
            //添加进布局
            mLLScrollContainer.addView(rootView);
        }
    }


    /**
     * 初始化Banner
     * @param bannerList
     */
    private void initBanner(final List<BannerBean> bannerList) {
        List<String> imageUrlList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();
        for (BannerBean bannerBean : bannerList) {
            imageUrlList.add(bannerBean.getImageUrl());
            titleList.add(bannerBean.getTitle());
        }
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        mBanner.setImages(imageUrlList);
        mBanner.setBannerTitles(titleList);
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                StartActUtil.toBookDetail(mBaseActivity,bannerList.get(position).getBookId());
            }
        });
        mBanner.start();
    }

    @Override
    public void onError(String msg) {
        showToast(msg);
        mRefreshLayout.finishRefresh();
    }
}

