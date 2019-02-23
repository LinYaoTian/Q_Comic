package com.rdc.bms.q_comic.mvp.view.fragment;

import android.os.Bundle;

import com.rdc.bms.q_comic.R;
import com.rdc.bms.q_comic.base.BaseFragment;
import com.rdc.bms.q_comic.base.BaseLazyFragment;
import com.rdc.bms.q_comic.base.BasePresenter;

public class MineFragment extends BaseLazyFragment {
    @Override
    protected BasePresenter getInstance() {
        return null;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_mine;
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

        isLoaded = true;
    }
}
