package com.rdc.bms.q_comic.mvp.view.fragment.BookDetail;

import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rdc.bms.easy_rv_adapter.OnClickViewRvListener;
import com.rdc.bms.easy_rv_adapter.base.BaseRvCell;
import com.rdc.bms.easy_rv_adapter.base.RvSimpleAdapter;
import com.rdc.bms.q_comic.R;
import com.rdc.bms.q_comic.base.BaseLazyFragment;
import com.rdc.bms.q_comic.base.BasePresenter;
import com.rdc.bms.q_comic.bean.ChapterBean;
import com.rdc.bms.q_comic.mvp.model.vo.BookTabDirVO;
import com.rdc.bms.q_comic.bean.rv_cell.ChapterNumCell;
import com.rdc.bms.q_comic.util.diffutil.ChapterDiffUtil;
import com.rdc.bms.q_comic.util.StartActUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

public class TabDirFragment extends BaseLazyFragment {

    @BindView(R.id.tv_update_time_fragment_tab_dir)
    TextView mTvUpdateTime;
    @BindView(R.id.iv_order_fragment_tab_dir)
    ImageView mIvOrder;
    @BindView(R.id.rv_chapter_fragment_tab_dir)
    RecyclerView mRvChapter;
    @BindView(R.id.tv_order_fragment_tab_dir)
    TextView mTvOrder;

    private BookTabDirVO mBookTabDirVO;
    private RvSimpleAdapter mChapterRvAdapter;
    private String mBookId;
    private boolean isDesc = true;//是否逆序
    private List<ChapterBean> mNewList;
    private List<ChapterBean> mOldList;

    public void setData(BookTabDirVO bookTabDirVO,String bookId){
        mBookTabDirVO = bookTabDirVO;
        mBookId = bookId;
        lazyLoad();
    }

    /**
     * @param newList 默认降序
     */
    public void setChapterList(List<ChapterBean> newList){
        if (!isDesc){
            Collections.reverse(newList);
        }
        mOldList = mNewList;
        mNewList = newList;
        updateChapterList(mNewList,mOldList);
    }

    @Override
    protected BasePresenter getInstance() {
        return null;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_tab_dir;
    }

    @Override
    protected void initData(Bundle bundle) {
        mNewList = new ArrayList<>();
        mOldList = new ArrayList<>();
    }

    @Override
    protected void initView() {
        mChapterRvAdapter = new RvSimpleAdapter();
        mRvChapter.setLayoutManager(new GridLayoutManager(mBaseActivity,4));
        mRvChapter.setAdapter(mChapterRvAdapter);
    }

    @Override
    protected void setListener() {
        mIvOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvOrder.performClick();
            }
        });
        mTvOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ChapterBean> newList = new ArrayList<>(mNewList);
                Collections.reverse(newList);
                mOldList = mNewList;
                mNewList = newList;
                updateChapterList(mNewList,mOldList);
                mRvChapter.getLayoutManager().scrollToPosition(0);
                if (isDesc){
                    mTvOrder.setText("升序");
                    mIvOrder.setImageResource(R.drawable.svg_arrow_top);
                }else {
                    mTvOrder.setText("降序");
                    mIvOrder.setImageResource(R.drawable.svg_arrow_bottom);
                }
                isDesc = !isDesc;
            }
        });
    }

    @Override
    protected void lazyLoad() {
        if (!isVisible || !isPrepared || isLoaded){
            return;
        }
        if (mBookTabDirVO != null){
            isLoaded = true;
            mTvUpdateTime.setText(mBookTabDirVO.getUpdateTime());
        }
    }

    private void updateChapterList(List<ChapterBean> newList,List<ChapterBean> oldList){
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ChapterDiffUtil(oldList,newList));
        diffResult.dispatchUpdatesTo(mChapterRvAdapter);
        mChapterRvAdapter.getData().clear();
        mChapterRvAdapter.getData().addAll(createChapterCellList(newList));
    }

    private List<BaseRvCell> createChapterCellList(List<ChapterBean> list){
        List<BaseRvCell> cellList = new ArrayList<>();
        for (final ChapterBean chapter : list) {
            ChapterNumCell chapterCell1 = new ChapterNumCell(chapter);
            chapterCell1.setListener(new OnClickViewRvListener() {
                @Override
                public void onClick(View view, int position) {

                }

                @Override
                public <C> void onClickItem(C data, int position) {
                    StartActUtil.toComicAct(mBaseActivity, chapter.getChapterId(), mBookId);
                }


            });
            cellList.add(chapterCell1);
        }
        return cellList;
    }
}
