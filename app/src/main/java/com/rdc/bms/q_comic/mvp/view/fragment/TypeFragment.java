package com.rdc.bms.q_comic.mvp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.rdc.bms.q_comic.R;
import com.rdc.bms.q_comic.base.BaseLazyFragment;
import com.rdc.bms.q_comic.mvp.presenter.TypePresenter;
import com.rdc.bms.q_comic.mvp.view.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.support.constraint.Constraints.TAG;

public class TypeFragment extends BaseLazyFragment{

    @BindView(R.id.tl_top_fragment_type)
    TabLayout mTabLayout;
    @BindView(R.id.vp_container_fragment_type)
    ViewPager mVpContainer;

    String TAG = "TypeFragment";

    private List<String> mKindTitleList;
    private List<TypeContainerFragment> mFList;

    public void setKindTitleList(List<String> list){
        mKindTitleList = list;
        lazyLoad();
    }

    @Override
    protected TypePresenter getInstance() {
        return new TypePresenter();
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_type;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void lazyLoad() {
        if (!isVisible || !isPrepared || isLoaded){
            return;
        }
        if (mKindTitleList != null){
            mFList = new ArrayList<>();
            for (int i = 0; i < mKindTitleList.size(); i++) {
                TypeContainerFragment f = new TypeContainerFragment();
                f.setKind(i);
                mFList.add(f);
            }
            mVpContainer.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
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
                    return mKindTitleList.get(position);
                }
            });
            mTabLayout.setupWithViewPager(mVpContainer);
            Log.d(TAG, "lazyLoad: "+mVpContainer.getCurrentItem());
            mVpContainer.setOffscreenPageLimit(mFList.size());
            isLoaded = true;
        }else {
            ((MainActivity)(mBaseActivity)).getKindTitleList();
        }
    }
}
