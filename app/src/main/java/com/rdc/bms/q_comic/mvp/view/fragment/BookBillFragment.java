package com.rdc.bms.q_comic.mvp.view.fragment;

import android.util.Log;
import android.view.View;

import com.rdc.bms.easy_rv_adapter.OnClickViewRvListener;
import com.rdc.bms.easy_rv_adapter.base.BaseRvCell;
import com.rdc.bms.q_comic.base.BaseLazyAbsFragment;
import com.rdc.bms.q_comic.bean.BookBillBean;
import com.rdc.bms.q_comic.bean.rv_cell.BookBillCell;
import com.rdc.bms.q_comic.mvp.contract.IBookListContract;
import com.rdc.bms.q_comic.mvp.presenter.BookBillPresenter;
import com.rdc.bms.q_comic.util.StartActUtil;

import java.util.ArrayList;
import java.util.List;

public class BookBillFragment extends BaseLazyAbsFragment<BookBillPresenter>
        implements IBookListContract.view {

    private String mTagName;
    private boolean isLoaded = false;

    public void setTagName(String tagName){
        mTagName = tagName;
    }

    @Override
    protected BookBillPresenter getInstance() {
        return new BookBillPresenter();
    }

    @Override
    protected void lazyLoad() {
        if (!isVisible || !isPrepared || isLoaded){
            return;
        }
        Log.d(TAG, "lazyLoad: start load !");
        mPresenter.getBookBillList(mTagName);
        isLoaded = true;
    }

    @Override
    public void onRecyclerViewInitialized() {

    }

    @Override
    public void onPullRefresh() {
        mPresenter.clearFlag();
        mPresenter.getBookBillList(mTagName);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getBookBillList(mTagName);
    }

    @Override
    public void setBookList(List<BookBillBean> list) {
        if (mRefreshLayout.isRefreshing()){
            mBaseAdapter.clear();
            mRefreshLayout.finishRefresh();
        }else {
            mRefreshLayout.finishLoadMore();
        }
        mBaseAdapter.addAll(createBookBillCellList(list));
    }

    private List<BaseRvCell> createBookBillCellList(List<BookBillBean> list){
        List<BaseRvCell> cellList = new ArrayList<>();
        for (final BookBillBean bean : list) {
            BookBillCell cell = new BookBillCell(bean);
            cell.setListener(new OnClickViewRvListener() {
                @Override
                public void onClick(View view, int position) {


                }

                @Override
                public <C> void onClickItem(C data, int position) {
                    StartActUtil.toBookBillDetail(mActivity,bean.getBookBillId(),bean.getBookIdList());
                }
            });
            cellList.add(cell);
        }
        return cellList;
    }





    @Override
    public void showError(String msg) {
        showToast(msg);
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
    }

    @Override
    public void setNoMore() {
        showToast("没有数据了！");
        mRefreshLayout.setEnableLoadMore(false);
    }
}
