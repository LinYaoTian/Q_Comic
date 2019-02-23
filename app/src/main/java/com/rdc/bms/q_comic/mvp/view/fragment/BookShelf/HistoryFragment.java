package com.rdc.bms.q_comic.mvp.view.fragment.BookShelf;


import android.support.v7.util.DiffUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.rdc.bms.easy_rv_adapter.OnClickViewRvListener;
import com.rdc.bms.easy_rv_adapter.base.BaseRvCell;
import com.rdc.bms.q_comic.base.BaseBookShelfTabFragment;
import com.rdc.bms.q_comic.bean.BookBean;
import com.rdc.bms.q_comic.bean.rv_cell.HistoryCell;
import com.rdc.bms.q_comic.mvp.contract.IHistoryContract;
import com.rdc.bms.q_comic.mvp.model.vo.HistoryVO;
import com.rdc.bms.q_comic.mvp.presenter.HistoryPresenter;
import com.rdc.bms.q_comic.util.StartActUtil;
import com.rdc.bms.q_comic.util.diffutil.HistoryDiffUtil;

import java.util.ArrayList;
import java.util.List;


public class HistoryFragment extends BaseBookShelfTabFragment<HistoryPresenter>
        implements IHistoryContract.View {

    private OnClickViewRvListener mOnHistoryItemListener;

    private List<BaseRvCell> createHistoryCellList(List<HistoryVO> list){
        List<BaseRvCell> cellList = new ArrayList<>();
        for (final HistoryVO vo : list) {
            BaseRvCell cell = new HistoryCell(vo);
            cell.setListener(mOnHistoryItemListener);
            cellList.add(cell);
        }
        return cellList;
    }

    @Override
    protected HistoryPresenter getInstance() {
        return new HistoryPresenter();
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || isLoaded){
            return;
        }
        mPresenter.refresh();
        isLoaded = true;
    }

    @Override
    public void onRecyclerViewInitialized() {
        mOnHistoryItemListener = new OnClickViewRvListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public <C> void onClickItem(C data, int position) {
                StartActUtil.toBookDetail(mActivity,((HistoryVO)data).getBookBean().getBookId());
            }
        };
    }

    @Override
    protected RecyclerView.LayoutManager initLayoutManger() {
        return new GridLayoutManager(mBaseActivity,3);
    }

    @Override
    public void onPullRefresh() {
        mRefreshLayout.setEnableLoadMore(true);
        mPresenter.refresh();
    }

    @Override
    public void onLoadMore() {
        mPresenter.getHistoryData();
    }

    @Override
    public void setHistoryData(List<HistoryVO> oldList, List<HistoryVO> newList) {
        Log.d("LYT", "setHistoryData: oldSize="+oldList.size()+",newSize="+newList.size());
        mRefreshLayout.finishLoadMore();
        mRefreshLayout.finishRefresh();
        mBaseAdapter.setDataNoNotify(createHistoryCellList(newList));
        DiffUtil.DiffResult diffResult =
                DiffUtil.calculateDiff(new HistoryDiffUtil(oldList,newList));
        diffResult.dispatchUpdatesTo(mBaseAdapter);
    }

    @Override
    public void onError(String msg) {
        showToast(msg);
    }

    @Override
    public void noMore() {
        mRefreshLayout.setEnableLoadMore(false);
        showToast("没有更多数据了！");
    }

    @Override
    public void onStartEdit() {
        addBottomCheckLayout();
        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableLoadMore(false);
        mPresenter.startEdit();
    }

    @Override
    public void onEndEdit() {
        removerBottomCheckLayout();
        mRefreshLayout.setEnableRefresh(true);
        mRefreshLayout.setEnableLoadMore(true);
        mPresenter.endEdit();
    }

    @Override
    public void onSelectAll(boolean isSelect) {
        mPresenter.selectAll(isSelect);
    }

    @Override
    public void onDelete() {
        mPresenter.deleteBooks();
    }
}
