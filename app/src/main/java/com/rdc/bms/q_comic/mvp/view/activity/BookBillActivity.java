package com.rdc.bms.q_comic.mvp.view.activity;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.rdc.bms.q_comic.R;
import com.rdc.bms.q_comic.base.BaseActivity;
import com.rdc.bms.q_comic.base.BasePresenter;
import com.rdc.bms.q_comic.config.Constant;
import com.rdc.bms.q_comic.mvp.view.fragment.BookBillFragment;
import com.rdc.bms.q_comic.mvp.view.fragment.RankFragment;

import butterknife.BindView;

public class BookBillActivity extends BaseActivity {

    @BindView(R.id.iv_back_act_book_bill)
    ImageView mIvBack;
    @BindView(R.id.tl_top_act_book_bill)
    TabLayout mTabLayout;
    @BindView(R.id.vp_container_act_book_bill)
    ViewPager mVpContainer;

    private String[] mTagNames;
    private Fragment[] mFList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_book_bill;
    }

    @Override
    protected BasePresenter getInstance() {
        return null;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        int type = getIntent().getIntExtra("type",Constant.TYPE_BOOK_BILL);
        switch (type){
            case Constant.TYPE_BOOK_BILL:
                String[] mTags = new String[]{
                        "hotbook",
                        "editbook",
                        "newbook"};
                mTagNames = new String[]{"热门","推荐","最新"};
                mFList = new BookBillFragment[3];
                for (int i = 0; i < mTags.length; i++) {
                    BookBillFragment f = new BookBillFragment();
                    f.setTagName(mTags[i]);
                    mFList[i] = f;
                }
                break;
            case Constant.TYPE_RANK:
                mTagNames = new String[]{"人气榜","打赏榜","月票榜"};
                int[] kinds = new int[]{
                        Constant.KIND_POPULARITY_LIST,
                        Constant.KIND_REWARD_LIST,
                        Constant.KIND_MONTHLY_TICKET_LIST
                };
                mFList = new RankFragment[3];
                for (int i = 0; i < kinds.length; i++) {
                    RankFragment f = new RankFragment();
                    f.setKind(kinds[i]);
                    mFList[i] = f;
                }
                break;
        }
    }

    @Override
    protected void initView() {
        for (String tagName : mTagNames) {
            TabLayout.Tab tab = mTabLayout.newTab();
            tab.setText(tagName);
            mTabLayout.addTab(tab);
        }
        mVpContainer.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFList[position];
            }

            @Override
            public int getCount() {
                return mFList.length;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTagNames[position];
            }
        });
        mVpContainer.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mVpContainer);
    }

    @Override
    protected void initListener() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
