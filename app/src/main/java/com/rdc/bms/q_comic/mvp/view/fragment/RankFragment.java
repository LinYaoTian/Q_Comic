package com.rdc.bms.q_comic.mvp.view.fragment;

import android.view.View;

import com.rdc.bms.easy_rv_adapter.OnClickViewRvListener;
import com.rdc.bms.easy_rv_adapter.base.BaseRvCell;
import com.rdc.bms.q_comic.base.BaseLazyAbsFragment;
import com.rdc.bms.q_comic.bean.RankBean;
import com.rdc.bms.q_comic.bean.rv_cell.RankCell;
import com.rdc.bms.q_comic.mvp.contract.IRankContract;
import com.rdc.bms.q_comic.mvp.presenter.RankPresenter;
import com.rdc.bms.q_comic.util.StartActUtil;

import java.util.ArrayList;
import java.util.List;

public class RankFragment extends BaseLazyAbsFragment<RankPresenter> implements IRankContract.View {

    private int mKind;
    private boolean isLoaded = false;

    public void setKind(int kind){
        mKind = kind;
    }

    @Override
    protected RankPresenter getInstance() {
        return new RankPresenter();
    }

    @Override
    protected void lazyLoad() {
        if (!isVisible || !isPrepared || isLoaded){
            return;
        }
        mPresenter.getRankData(mKind);
        isLoaded = true;
    }

    @Override
    public void onRecyclerViewInitialized() {
        mRefreshLayout.setEnableLoadMore(false);
    }

    @Override
    public void onPullRefresh() {
        mPresenter.getRankData(mKind);
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void setRankData(List<RankBean> list) {
        if (mRefreshLayout.isRefreshing()){
            mBaseAdapter.clear();
        }
        mRefreshLayout.finishRefresh();
        List<BaseRvCell> baseRvCellList = new ArrayList<>();
        for (final RankBean bean : list) {
            RankCell rankCell = new RankCell(bean);
            rankCell.setListener(new OnClickViewRvListener() {
                @Override
                public void onClick(View view, int position) {

                }

                @Override
                public <C> void onClickItem(C data, int position) {
                    StartActUtil.toBookDetail(mBaseActivity,bean.getBookId());
                }

            });
            baseRvCellList.add(rankCell);
        }
        mBaseAdapter.addAll(baseRvCellList);
    }

    @Override
    public void onError(String msg) {
        showToast(msg);
        mRefreshLayout.finishRefresh();
    }
}
