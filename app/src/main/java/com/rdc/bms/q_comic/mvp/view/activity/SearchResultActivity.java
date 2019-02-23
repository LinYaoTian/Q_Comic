package com.rdc.bms.q_comic.mvp.view.activity;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rdc.bms.q_comic.R;
import com.rdc.bms.q_comic.base.BaseActivity;
import com.rdc.bms.q_comic.base.BasePresenter;
import com.rdc.bms.q_comic.mvp.view.fragment.SearchResultFragment;

import butterknife.BindView;

public class SearchResultActivity extends BaseActivity {

    @BindView(R.id.iv_back_layout_top)
    ImageView mIvBack;
    @BindView(R.id.tv_title_layout_top)
    TextView mTvTitle;

    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_search_result;
    }

    @Override
    protected BasePresenter getInstance() {
        return null;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        if (intent != null){
            mTitle = intent.getStringExtra("title");
            SearchResultFragment f = new SearchResultFragment();
            f.setData(intent.getStringExtra("type"),
                    intent.getStringExtra("key"));
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fl_root_act_type_detail,f);
            transaction.commit();
        }
    }

    @Override
    protected void initListener() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mTvTitle.setText(mTitle);
    }
}
