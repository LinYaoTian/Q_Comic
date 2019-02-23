package com.rdc.bms.q_comic.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rdc.bms.q_comic.util.ActivityCollectorUtil;

import butterknife.ButterKnife;


public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity{
    protected String TAG;
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusTextColor(true);
        TAG = this.getClass().getSimpleName();
        ActivityCollectorUtil.addActivity(this);
        setContentView(setLayoutResID());
        ButterKnife.bind(this);
        mPresenter = getInstance();
        if (mPresenter !=null){
            mPresenter.attachView(this);
        }
        initData(savedInstanceState);
        initView();
        initListener();
    }

    /**
     * 设置状态栏字体颜色
     * @param isDark
     */
    protected void setStatusTextColor(boolean isDark){
        if (isDark){
            //黑色字体
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }else {
            //白色字体
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        ActivityCollectorUtil.removeActivity(this);
        super.onDestroy();
    }

    protected abstract int setLayoutResID();

    protected abstract T getInstance();

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract void initView();

    protected abstract void initListener();

    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    protected static String getString(EditText et) {
        return et.getText().toString();
    }



}
