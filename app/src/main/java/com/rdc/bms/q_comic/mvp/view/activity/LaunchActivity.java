package com.rdc.bms.q_comic.mvp.view.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rdc.bms.q_comic.R;
import com.rdc.bms.q_comic.base.BaseActivity;
import com.rdc.bms.q_comic.base.BasePresenter;
import com.rdc.bms.q_comic.util.StartActUtil;

public class LaunchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
    }

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_launch;
    }

    @Override
    protected BasePresenter getInstance() {
        return null;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    StartActUtil.toMainAct(LaunchActivity.this);
                }
            }
        }).start();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }
}
