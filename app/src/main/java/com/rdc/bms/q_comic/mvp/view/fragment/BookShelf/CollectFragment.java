package com.rdc.bms.q_comic.mvp.view.fragment.BookShelf;


import android.support.v7.util.DiffUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.rdc.bms.easy_rv_adapter.OnClickViewRvListener;
import com.rdc.bms.easy_rv_adapter.base.BaseRvCell;
import com.rdc.bms.q_comic.base.BaseBookShelfCell;
import com.rdc.bms.q_comic.base.BaseBookShelfTabFragment;
import com.rdc.bms.q_comic.bean.rv_cell.CollectCell;
import com.rdc.bms.q_comic.mvp.contract.ICollectContract;
import com.rdc.bms.q_comic.mvp.model.vo.CollectVO;
import com.rdc.bms.q_comic.mvp.presenter.CollectPresenter;
import com.rdc.bms.q_comic.util.StartActUtil;
import com.rdc.bms.q_comic.util.diffutil.CollectDiffUtil;

import java.util.ArrayList;
import java.util.List;


public class CollectFragment extends BaseBookShelfTabFragment<CollectPresenter>
        implements ICollectContract.View {


    @Override
    protected CollectPresenter getInstance() {
        return new CollectPresenter();
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || isLoaded){
            return;
        }
        Log.d("LYT", "lazyLoad: collectFragment");
        mPresenter.getCollectData();
        isLoaded = true;
    }

    @Override
    public void onRecyclerViewInitialized() {
        mRefreshLayout.setEnableLoadMore(false);
    }

    @Override
    protected RecyclerView.LayoutManager initLayoutManger() {
        return new GridLayoutManager(mBaseActivity,3);
    }

    @Override
    public void onPullRefresh() {
        mPresenter.getCollectData();
    }

    @Override
    public void onLoadMore() {
    }

    @Override
    public void setCollectData(List<CollectVO> oldList, List<CollectVO> newList) {
        mRefreshLayout.finishRefresh();
        mBaseAdapter.setDataNoNotify(createCollectCellList(newList));
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new CollectDiffUtil(oldList,newList));
        diffResult.dispatchUpdatesTo(mBaseAdapter);
    }


    @Override
    public void onError(String msg) {
        mRefreshLayout.finishRefresh();
        showToast(msg);
    }

    private List<BaseRvCell> createCollectCellList(List<CollectVO> list){
        List<BaseRvCell> cellList = new ArrayList<>();
        for (final CollectVO collectVO : list) {
            CollectCell cell = new CollectCell(collectVO);
            cell.setListener(new OnClickViewRvListener() {
                @Override
                public void onClick(View view, int position) {

                }

                @Override
                public <C> void onClickItem(C data, int position) {
                    StartActUtil.toBookDetail(mActivity,collectVO.getBookBean().getBookId());
                }
            });
            cellList.add(cell);
        }
        return cellList;
    }

    @Override
    public void onStartEdit() {
        addBottomCheckLayout();
        mRefreshLayout.setEnableRefresh(false);
        mPresenter.startEdit();
    }

    @Override
    public void onEndEdit() {
        removerBottomCheckLayout();
        mRefreshLayout.setEnableRefresh(true);
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
