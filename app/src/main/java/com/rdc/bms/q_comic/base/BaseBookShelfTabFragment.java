package com.rdc.bms.q_comic.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rdc.bms.easy_rv_adapter.fragment.AbsFragment;
import com.rdc.bms.q_comic.R;
import com.rdc.bms.q_comic.listener.OnEditListener;
import com.rdc.bms.q_comic.util.ScreenUtil;

import butterknife.BindView;

public abstract class BaseBookShelfTabFragment<P extends BasePresenter> extends
        AbsFragment implements OnEditListener {

    protected BaseActivity mBaseActivity;  //贴附的activity,Fragment中可能用到
    protected View mRootView;           //根view
    protected P mPresenter;
    protected boolean isVisible = false;
    protected boolean isPrepared = false;
    protected boolean isLoaded = false;
    protected View mCheckLayout;
    protected ConstraintLayout mRootContainer;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mBaseActivity = (BaseActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mPresenter = getInstance();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        mRootView = super.onCreateView(inflater, container, savedInstanceState);
        mRootContainer = mRootView.findViewById(R.id.root_container_layout);
        isPrepared = true;
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lazyLoad();
    }

    protected abstract P getInstance();

    protected String getString(EditText editText) {
        return editText.getText().toString();
    }

    protected void showToast(String msg) {
        Toast.makeText(mBaseActivity, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    protected void startActivity(Class clazz) {
        Intent intent = new Intent(mBaseActivity, clazz);
        startActivity(intent);
    }

    /**
     * 在这里实现Fragment数据的缓加载.
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected abstract void lazyLoad();

    protected void onInvisible() {
    }

    /**
     * 添加底部全选和删除布局
     */
    protected void addBottomCheckLayout(){

        mCheckLayout = LayoutInflater.from(mBaseActivity).inflate(
                R.layout.layout_check,
                null);
        mCheckLayout.setTranslationZ(ScreenUtil.dip2px(mBaseActivity,15));
        final TextView tvSelectAll = mCheckLayout.findViewById(R.id.tv_select_all_layout_check);
        TextView tvDelete = mCheckLayout.findViewById(R.id.tv_delete_layout_check);
        tvSelectAll.setOnClickListener(new View.OnClickListener() {
            boolean selectAll = true;
            @Override
            public void onClick(View v) {
                onSelectAll(selectAll);
                if (selectAll){
                    tvSelectAll.setText("取消全选");
                }else {
                    tvSelectAll.setText("全选");
                }
                selectAll = !selectAll;
            }
        });
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDelete();
            }
        });
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,ScreenUtil.dip2px(mBaseActivity,50)
        );
        layoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.bottomMargin = ScreenUtil.dip2px(mBaseActivity,0.5f);
        mCheckLayout.setLayoutParams(layoutParams);
        mRootContainer.addView(mCheckLayout);
    }

    /**
     * 删除底部全选和删除布局
     */
    protected void removerBottomCheckLayout(){
        mRootContainer.removeView(mCheckLayout);
    }
}
