package com.rdc.bms.q_comic.mvp.view.fragment.BookShelf;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.rdc.bms.q_comic.R;
import com.rdc.bms.q_comic.base.BaseBookShelfTabFragment;
import com.rdc.bms.q_comic.base.BaseLazyAbsFragment;
import com.rdc.bms.q_comic.base.BaseLazyFragment;
import com.rdc.bms.q_comic.base.BasePresenter;
import com.rdc.bms.q_comic.mvp.view.fragment.BookBillFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BookShelfFragment extends BaseLazyFragment {

    @BindView(R.id.tl_top_fragment_bookshelf)
    TabLayout mTabLayout;
    @BindView(R.id.vp_container_fragment_bookshelf)
    ViewPager mVpContainer;
    @BindView(R.id.tv_edit_fragment_bookshelf)
    TextView mTvEdit;

    private List<BaseBookShelfTabFragment> mFList;
    private List<EditStatus> mEditStatusList;
    private int mCurrentPage;

    @Override
    protected BasePresenter getInstance() {
        return null;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_bookshelf;
    }

    @Override
    protected void initData(Bundle bundle) {
        mFList = new ArrayList<>(3);
        mFList.add(new CollectFragment());
        mFList.add(new MyBookBillFragment());
        mFList.add(new HistoryFragment());
        mEditStatusList = new ArrayList<>(3);
        mEditStatusList.add(new EditStatus(false));
        mEditStatusList.add(new EditStatus(false));
        mEditStatusList.add(new EditStatus(false));
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void lazyLoad () {
        if (!isPrepared || !isVisible || isLoaded) {
            return;
        }
        mVpContainer.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {

            String[] titles = new String[]{"收藏", "书单", "历史"};

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
                return titles[position];
            }
        });
        mVpContainer.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mVpContainer);
        isLoaded = true;
    }

    @Override
    protected void setListener() {
        mTvEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditStatus editStatus = mEditStatusList.get(mCurrentPage);
                if (!editStatus.isEdit()){
                    mFList.get(mCurrentPage).onStartEdit();
                    mTvEdit.setText("完成");
                }else {
                    mFList.get(mCurrentPage).onEndEdit();
                    mTvEdit.setText("编辑");
                }
                editStatus.setEdit(!editStatus.isEdit());
            }
        });
        mVpContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPage = position;
                mTvEdit.setText(mEditStatusList.get(position).getText());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 编辑管理
     */
    class EditStatus {
        private boolean isEdit;

        public EditStatus(boolean isEdit) {
            this.isEdit = isEdit;
        }

        public String getText() {
            return isEdit?"完成":"编辑";
        }

        public boolean isEdit() {
            return isEdit;
        }

        public void setEdit(boolean edit) {
            isEdit = edit;
        }
    }
}
