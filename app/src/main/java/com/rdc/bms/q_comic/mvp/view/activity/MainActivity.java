package com.rdc.bms.q_comic.mvp.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;

import com.rdc.bms.q_comic.R;
import com.rdc.bms.q_comic.base.BaseActivity;
import com.rdc.bms.q_comic.base.BaseLazyFragment;
import com.rdc.bms.q_comic.mvp.view.fragment.BookShelf.BookShelfFragment;
import com.rdc.bms.q_comic.mvp.view.fragment.HomeFragment;
import com.rdc.bms.q_comic.widget.NoScrollViewPager;
import com.rdc.bms.q_comic.mvp.contract.IMainContract;
import com.rdc.bms.q_comic.mvp.presenter.MainPresenter;
import com.rdc.bms.q_comic.mvp.view.fragment.MineFragment;
import com.rdc.bms.q_comic.mvp.view.fragment.TypeFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements IMainContract.View {

    @BindView(R.id.vp_container_act_main)
    NoScrollViewPager mVpContainer;
    @BindView(R.id.bnv_menus_act_main)
    BottomNavigationView mBnvBottom;

    private List<BaseLazyFragment> mFList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void getKindTitleList(){
        mPresenter.getKindTitleList();
    }

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter getInstance() {
        return new MainPresenter();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mFList = new ArrayList<>();
        mFList.add(new HomeFragment());
        mFList.add(new TypeFragment());
        mFList.add(new BookShelfFragment());
        mFList.add(new MineFragment());
    }

    @Override
    protected void initView() {
        disableShiftMode(mBnvBottom);
        mVpContainer.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFList.get(position);
            }

            @Override
            public int getCount() {
                return mFList.size();
            }
        });
        mVpContainer.setOffscreenPageLimit(3);
        mVpContainer.setScroll(true);
    }

    @Override
    protected void initListener() {
        mBnvBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_homepage:
                        mVpContainer.setCurrentItem(0);
                        return true;
                    case R.id.menu_type:
                        mVpContainer.setCurrentItem(1);
                        return true;
                    case R.id.menu_bookshelf:
                        mVpContainer.setCurrentItem(2);
                        return true;
                    case R.id.menu_mine:
                        mVpContainer.setCurrentItem(3);
                        return true;
                }
                return false;
            }
        });
        mVpContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mBnvBottom.setSelectedItemId(R.id.menu_homepage);
                        break;
                    case 1:
                        mBnvBottom.setSelectedItemId(R.id.menu_type);
                        break;
                    case 2:
                        mBnvBottom.setSelectedItemId(R.id.menu_bookshelf);
                        break;
                    case 3:
                        mBnvBottom.setSelectedItemId(R.id.menu_mine);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @SuppressLint("RestrictedApi")
    private void disableShiftMode(BottomNavigationView view) {
        //获取子View BottomNavigationMenuView的对象
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            //设置私有成员变量mShiftingMode可以修改
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //去除shift效果
                item.setShiftingMode(false);
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "没有mShiftingMode这个成员变量", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "无法修改mShiftingMode的值", e);
        }
    }

    @Override
    public void setKindTitle(List<String> list) {
        Log.d(TAG, "setKindTitle: "+list);
        ((TypeFragment)(mFList.get(1))).setKindTitleList(list);
    }
}
