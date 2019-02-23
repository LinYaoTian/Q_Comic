package com.rdc.bms.q_comic.mvp.view.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.rdc.bms.easy_rv_adapter.OnClickViewRvListener;
import com.rdc.bms.easy_rv_adapter.base.BaseRvCell;
import com.rdc.bms.q_comic.base.BaseAbsFragment;
import com.rdc.bms.q_comic.bean.BookBean;
import com.rdc.bms.q_comic.bean.rv_cell.BookCell;
import com.rdc.bms.q_comic.mvp.contract.ISearchResultContract;
import com.rdc.bms.q_comic.mvp.presenter.SearchResultPresenter;
import com.rdc.bms.q_comic.util.StartActUtil;

import java.util.ArrayList;
import java.util.List;

public class SearchResultFragment extends BaseAbsFragment<SearchResultPresenter> implements ISearchResultContract.View {

    private String mType;
    private String mKey;

    public void setData(String type,String key){
        mType = type;
        mKey = key;
    }

    @Override
    protected RecyclerView.LayoutManager initLayoutManger() {
        return new GridLayoutManager(mActivity,3);
    }

    @Override
    protected SearchResultPresenter getInstance() {
        return new SearchResultPresenter();
    }

    @Override
    public void onRecyclerViewInitialized() {
        mRefreshLayout.setEnableRefresh(false);
        search();
    }

    @Override
    public void onPullRefresh() {

    }

    @Override
    public void onLoadMore() {
        search();
    }

    @Override
    public void onError(String msg) {
        showToast(msg);
    }

    @Override
    public void noMore() {
        showToast("没有更多数据了！");
        mRefreshLayout.finishLoadMore();
        mRefreshLayout.setEnableLoadMore(false);
    }

    @Override
    public void setSearchResult(List<BookBean> list) {
        mRefreshLayout.finishLoadMore();
        List<BaseRvCell> cellList = new ArrayList<>();
        for (final BookBean bookBean : list) {
            BookCell bookCell = new BookCell(bookBean);
            bookCell.setGrid(true);
            bookCell.setListener(new OnClickViewRvListener() {
                @Override
                public void onClick(View view, int position) {

                }

                @Override
                public <C> void onClickItem(C data, int position) {
                    StartActUtil.toBookDetail(mActivity,bookBean.getBookId());
                }

            });
            cellList.add(bookCell);
        }
        mBaseAdapter.addAll(cellList);
    }

    private void search() {
        if (TextUtils.isEmpty(mKey)){
            mPresenter.SearchType(mType);
        }else {
            mPresenter.SearchKey(mKey);
        }
    }
}
