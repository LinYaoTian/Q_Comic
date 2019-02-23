package com.rdc.bms.q_comic.mvp.view.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rdc.bms.easy_rv_adapter.OnClickViewRvListener;
import com.rdc.bms.easy_rv_adapter.base.BaseRvCell;
import com.rdc.bms.easy_rv_adapter.base.BaseRvViewHolder;
import com.rdc.bms.q_comic.base.BaseLazyAbsFragment;
import com.rdc.bms.q_comic.bean.TypeBean;
import com.rdc.bms.q_comic.bean.rv_cell.TypeBeanCell;
import com.rdc.bms.q_comic.mvp.contract.ITypeContract;
import com.rdc.bms.q_comic.mvp.presenter.TypePresenter;
import com.rdc.bms.q_comic.util.StartActUtil;

import java.util.ArrayList;
import java.util.List;

public class TypeContainerFragment extends BaseLazyAbsFragment<TypePresenter> implements ITypeContract.View {

    private int mKind = -1;

    public void setKind(int kind ){
        mKind = kind;
    }

    @Override
    protected RecyclerView.LayoutManager initLayoutManger() {
        return new GridLayoutManager(mActivity,3);
    }

    @Override
    public void onRecyclerViewInitialized() {
        mRefreshLayout.setEnableLoadMore(false);
    }

    @Override
    public void onPullRefresh() {
        mPresenter.getTypeList(mKind);
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    protected TypePresenter getInstance() {
        return new TypePresenter();
    }

    @Override
    protected void lazyLoad() {
        if (!isVisible || !isPrepared || isLoaded){
            return;
        }
        if (mKind != -1){
            mPresenter.getTypeList(mKind);
        }
    }

    @Override
    public void setTypeList(List<TypeBean> list) {
        isLoaded = true;
        if (mRefreshLayout.isRefreshing()){
            mBaseAdapter.clear();
        }
        mRefreshLayout.finishRefresh();
        List<BaseRvCell> cellList = new ArrayList<>();
        for (final TypeBean bean : list) {
            TypeBeanCell beanCell = new TypeBeanCell(bean);
            beanCell.setListener(new OnClickViewRvListener() {
                @Override
                public void onClick(View view, int position) {

                }

                @Override
                public <C> void onClickItem(C data, int position) {
                    StartActUtil.toSearchResultAct(mActivity,bean.getName(),bean.getTypeId());
                }

            });
            cellList.add(beanCell);
        }
        mBaseAdapter.addAll(cellList);
    }

    @Override
    public void onError(String msg) {
        showToast(msg);
    }
}
