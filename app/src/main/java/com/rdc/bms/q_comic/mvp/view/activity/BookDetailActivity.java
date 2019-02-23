package com.rdc.bms.q_comic.mvp.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rdc.bms.q_comic.R;
import com.rdc.bms.q_comic.base.BaseActivity;
import com.rdc.bms.q_comic.base.BaseLazyFragment;
import com.rdc.bms.q_comic.bean.BookDetailVO;
import com.rdc.bms.q_comic.bean.ChapterBean;
import com.rdc.bms.q_comic.config.Constant;
import com.rdc.bms.q_comic.mvp.model.vo.BookTopInfoVO;
import com.rdc.bms.q_comic.mvp.contract.IBookDetailContract;
import com.rdc.bms.q_comic.mvp.presenter.BookDetailPresenter;
import com.rdc.bms.q_comic.mvp.view.fragment.BookDetail.TabDetailFragment;
import com.rdc.bms.q_comic.mvp.view.fragment.BookDetail.TabDirFragment;
import com.rdc.bms.q_comic.mvp.view.fragment.BookDetail.TabSupportFragment;
import com.rdc.bms.q_comic.util.AnimateUtil;
import com.rdc.bms.q_comic.util.ImageUtil;
import com.rdc.bms.q_comic.util.StartActUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class BookDetailActivity extends BaseActivity<BookDetailPresenter>
        implements IBookDetailContract.View {

    @BindView(R.id.tabLayout_act_book_detail)
    TabLayout mTabLayout;
    @BindView(R.id.iv_back_layout_top)
    ImageView mIvBack;
    @BindView(R.id.iv_right_layout_top)
    ImageView mIvCollect;
    @BindView(R.id.tv_title_layout_top)
    TextView mTvTitle;
    @BindView(R.id.layout_top_act_book_detail)
    View mTopLayout;
    @BindView(R.id.cl_top_layout_act_book_detail)
    ConstraintLayout mHeaderLayout;
    @BindView(R.id.vp_container_act_book_detail)
    ViewPager mVpContainer;
    @BindView(R.id.iv_cover_act_book_detail)
    ImageView mIvCover;
    @BindView(R.id.iv_bg_top_act_book_detail)
    ImageView mIvTopBg;
    @BindView(R.id.tv_name_act_book_detail)
    TextView mTvName;
    @BindView(R.id.tv_author_act_book_detail)
    TextView mTvAuthor;
    @BindView(R.id.tfl_tag_act_book_detail)
    TagFlowLayout mTagFlowLayout;
    @BindView(R.id.tv_popularity_act_book_detail)
    TextView mTvPopularity;

    @BindView(R.id.toolbar_act_book_detail)
    Toolbar mToolbar;
    @BindView(R.id.app_bar_layout_act_book_detail)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.collapsing_layout_act_book_detail)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.btn_offline_cache_act_book_detail)
    Button mBtnOfflineCache;
    @BindView(R.id.btn_start_read_act_book_detail)
    Button mBtnStartRead;

    private List<BaseLazyFragment> mFList;
    private final String[] mTitles = {"详情","目录","支持"};
    private String mBookId;
    private boolean isRequestSuccess = false;
    private ChapterBean mRecentReadChapter;//最近阅读的一话
    private ChapterBean mFirstChapterBean;//第一话
    private boolean isCollected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.getBookDetailData(mBookId);
    }

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_book_detail;
    }

    @Override
    protected BookDetailPresenter getInstance() {
        return new BookDetailPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isRequestSuccess){
            mPresenter.getAllRecord();
            mPresenter.getLastRecordChapter();
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        mBookId = intent.getStringExtra("bookId");
        mFList = new ArrayList<>();
        if (mBookId != null){
            mFList.add(new TabDetailFragment());
            mFList.add(new TabDirFragment());
            mFList.add(new TabSupportFragment());
        }
    }

    @Override
    protected void initView() {
        mTopLayout.setBackgroundResource(R.color.transparent);
        mIvCollect.setVisibility(View.VISIBLE);
        mIvBack.setImageResource(R.drawable.svg_white_nav_bar_back);
        mIvCollect.setImageResource(R.drawable.svg_white_nav_bar_love);
        mIvCollect.setEnabled(false);
        setSupportActionBar(mToolbar);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset <= -mHeaderLayout.getHeight() / 2){
                    mCollapsingToolbarLayout.setTitle("");
                    setStatusTextColor(true);
                    mIvBack.setImageResource(R.mipmap.svg_red_nav_bar_back);
                    mIvCollect.setImageResource(isCollected?R.drawable.svg_red_pressed_like:R.drawable.svg_red_like_normal);
                    mTvTitle.setVisibility(View.VISIBLE);
                }else {
                    mCollapsingToolbarLayout.setTitle("");
                    setStatusTextColor(false);
                    mIvBack.setImageResource(R.drawable.svg_white_nav_bar_back);
                    mIvCollect.setImageResource(isCollected?R.drawable.svg_white_pressed_like:R.drawable.svg_white_normal_like);
                    mTvTitle.setVisibility(View.INVISIBLE);
                }
            }
        });
        mVpContainer.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFList.get(position);
            }

            @Override
            public int getCount() {
                return mFList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        });
        mVpContainer.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mVpContainer);
    }

    @Override
    protected void initListener() {
        mIvCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimateUtil.likeAnimate(mIvCollect,isCollected);
                if (isCollected){
                    showToast("已取消收藏！");
                }else {
                    showToast("已收藏！");
                }
                isCollected = !isCollected;
                mPresenter.collect(isCollected);
            }
        });
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mBtnOfflineCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(Constant.TIP_NOT_COMPLETE);
            }
        });
        mBtnStartRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRecentReadChapter != null){
                    StartActUtil.toComicAct(
                            BookDetailActivity.this,
                            mRecentReadChapter.getChapterId(),
                            mBookId);
                }else if (mFirstChapterBean != null){
                    StartActUtil.toComicAct(
                            BookDetailActivity.this,
                            mFirstChapterBean.getChapterId(),
                            mBookId);
                }
            }
        });
    }

    @Override
    public void setBookDetailData(BookDetailVO bean) {
        BookTopInfoVO topInfoBean = bean.getComicTopInfo();
        isCollected = bean.getComicTopInfo().isCollected();
        mIvCollect.setEnabled(true);
        Glide.with(this)
                .load(topInfoBean.getCoverUrl())
                .into(mIvCover);
        Glide.with(this)
                .load(topInfoBean.getCoverUrl())
                .apply(new RequestOptions()
                        .transform(new BlurTransformation(25)))
                .into(mIvTopBg);
        ImageUtil.changeBrightness(mIvTopBg,-70);
        mTvAuthor.setText(topInfoBean.getAuthor());
        mTvName.setText(topInfoBean.getName());
        mTvTitle.setText(topInfoBean.getName());
        mTvPopularity.setText(topInfoBean.getFireNum());
        final int[] tagsBackground = new int[]{
                R.drawable.bg_hot_key_blue,R.drawable.bg_hot_key_orange,
                R.drawable.bg_hot_key_dark_green,R.drawable.bg_hot_key_purple,
                R.drawable.bg_hot_key_red,R.drawable.bg_hot_key_light_green
        };
        mTagFlowLayout.setAdapter(new TagAdapter<String>(topInfoBean.getmTagList()) {
            @Override
            public View getView(FlowLayout parent, int position, String o) {
                TextView textView = (TextView) LayoutInflater.from(BookDetailActivity.this)
                        .inflate(R.layout.tag_tagflowlayout,parent,false);
                textView.setText(o);
                textView.setBackgroundResource(tagsBackground[position % (tagsBackground.length-1)]);
                return textView;
            }
        });
        ((TabDirFragment)(mFList.get(1))).setData(bean.getBookTabDirVO(), mBookId);
        ((TabDetailFragment)(mFList.get(0))).setData(bean.getBookTabDetailVO());
        ((TabSupportFragment)(mFList.get(2))).setData(bean.getBookTabSupportVO());
        mFirstChapterBean = bean.getBookTabDirVO().getChapterList()
                .get(bean.getBookTabDirVO().getChapterList().size()-1);
        isRequestSuccess = true;
    }

    @Override
    public void setReadRecord(List<ChapterBean> newList) {
        ((TabDirFragment)(mFList.get(1))).setChapterList(newList);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setLastRecordChapter(ChapterBean chapter, boolean first) {
        mRecentReadChapter = chapter;
        if (first){
            mBtnStartRead.setText("开始阅读");
        }else {
            mBtnStartRead.setText("续看"+chapter.getChapterNum());
        }

    }



    @Override
    public void onError(String msg) {
        showToast(msg);
    }
}
