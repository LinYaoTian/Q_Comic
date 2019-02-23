package com.rdc.bms.q_comic.mvp.view.fragment.BookDetail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rdc.bms.easy_rv_adapter.base.RvSimpleAdapter;
import com.rdc.bms.q_comic.R;
import com.rdc.bms.q_comic.base.BaseLazyFragment;
import com.rdc.bms.q_comic.base.BasePresenter;
import com.rdc.bms.q_comic.bean.BookBean;
import com.rdc.bms.q_comic.bean.BookInfoBean;
import com.rdc.bms.q_comic.mvp.model.vo.BookTabDetailVO;
import com.rdc.bms.q_comic.bean.rv_cell.BookInfoCell;
import com.rdc.bms.q_comic.bean.rv_cell.BookListCell;

import java.util.List;

import butterknife.BindView;

public class TabDetailFragment extends BaseLazyFragment {

    @BindView(R.id.rv_container_fragment_tab_detail)
    RecyclerView mRvContainer;

    private RvSimpleAdapter mAdapter;
    private BookTabDetailVO mBookTabDetailVO;

    private final String TAG = "TabDetailFragment";

    public void setData(BookTabDetailVO bookTabDetailVO){
        mBookTabDetailVO = bookTabDetailVO;
        lazyLoad();
    }

    @Override
    protected BasePresenter getInstance() {
        return null;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_tab_detail;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    protected void initView() {
        mAdapter = new RvSimpleAdapter();
        mRvContainer.setLayoutManager(new LinearLayoutManager(
                mBaseActivity,
                LinearLayoutManager.VERTICAL,
                false));
        mRvContainer.setAdapter(mAdapter);

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void lazyLoad() {
        if (!isVisible || !isPrepared || isLoaded){
            return;
        }
        if (mBookTabDetailVO != null){
            isLoaded = true;
            BookInfoCell bookInfoCell = new BookInfoCell(BookInfoBean.toThis(mBookTabDetailVO));
            mAdapter.add(bookInfoCell);
            addItem(BookListCell.TYPE_TAB_COMIC_LIST_OTHER_WORKS,"作者作品", mBookTabDetailVO.getAuthorOtherComicList());
            addItem(BookListCell.TYPE_TAB_COMIC_LIST_RECOMMEND,"相关推荐", mBookTabDetailVO.getRecommendComicList());
        }
    }

    private void addItem(int type,String name,List<BookBean> list){
        if (list != null && list.size() !=0){
            mAdapter.add(new BookListCell(list,type,name));
        }
    }
}
