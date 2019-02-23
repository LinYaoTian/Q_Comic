package com.rdc.bms.q_comic.mvp.view.fragment.BookShelf;


import android.support.v7.util.DiffUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rdc.bms.easy_rv_adapter.OnClickViewRvListener;
import com.rdc.bms.easy_rv_adapter.base.BaseRvCell;
import com.rdc.bms.q_comic.base.BaseBookShelfCell;
import com.rdc.bms.q_comic.base.BaseBookShelfTabFragment;
import com.rdc.bms.q_comic.base.BaseLazyAbsFragment;
import com.rdc.bms.q_comic.bean.BookBillBean;
import com.rdc.bms.q_comic.bean.rv_cell.BookBillCell;
import com.rdc.bms.q_comic.bean.rv_cell.MyBookBillCell;
import com.rdc.bms.q_comic.mvp.contract.IMyBookBillContract;
import com.rdc.bms.q_comic.mvp.model.vo.MyBookBillVO;
import com.rdc.bms.q_comic.mvp.presenter.MyBookBillPresenter;
import com.rdc.bms.q_comic.util.StartActUtil;
import com.rdc.bms.q_comic.util.diffutil.BookBillDiffUtil;

import java.util.ArrayList;
import java.util.List;

public class MyBookBillFragment extends BaseBookShelfTabFragment<MyBookBillPresenter>
        implements IMyBookBillContract.View {
    @Override
    protected MyBookBillPresenter getInstance() {
        return new MyBookBillPresenter();
    }
    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || isLoaded){
            return;
        }
        mPresenter.getMyBookBillData();
        isLoaded = true;
    }

    @Override
    public void onRecyclerViewInitialized() {
        mRefreshLayout.setEnableLoadMore(false);
    }

    @Override
    public void onPullRefresh() {
        mPresenter.getMyBookBillData();
    }


    @Override
    public void onLoadMore() {

    }

    @Override
    public void setMyBookBillData(List<MyBookBillVO> oldList, List<MyBookBillVO> newList) {
        mRefreshLayout.finishRefresh();
        mBaseAdapter.setDataNoNotify(createBookBillCellList(newList));
        DiffUtil.DiffResult diffResult =
                DiffUtil.calculateDiff(new BookBillDiffUtil(oldList,newList));
        diffResult.dispatchUpdatesTo(mBaseAdapter);
    }

    @Override
    public void onError(String msg) {
        mRefreshLayout.finishRefresh();
        showToast(msg);
    }

    private List<BaseRvCell> createBookBillCellList(List<MyBookBillVO> list){
        List<BaseRvCell> cellList = new ArrayList<>();
        for (final MyBookBillVO bean : list) {
            BaseRvCell cell = new MyBookBillCell(bean);
            cell.setListener(new OnClickViewRvListener() {
                @Override
                public void onClick(View view, int position) {


                }

                @Override
                public <C> void onClickItem(C data, int position) {
                    StartActUtil.toBookBillDetail(
                            mActivity,
                            bean.getBookBillBean().getBookBillId(),
                            bean.getBookBillBean().getBookIdList());
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
