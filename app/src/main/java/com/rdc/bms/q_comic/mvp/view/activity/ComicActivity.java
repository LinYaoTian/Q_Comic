package com.rdc.bms.q_comic.mvp.view.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rdc.bms.easy_rv_adapter.OnClickViewRvListener;
import com.rdc.bms.easy_rv_adapter.base.BaseRvCell;
import com.rdc.bms.easy_rv_adapter.base.RvSimpleAdapter;
import com.rdc.bms.q_comic.R;
import com.rdc.bms.q_comic.base.BaseActivity;
import com.rdc.bms.q_comic.bean.ChapterBean;
import com.rdc.bms.q_comic.bean.ComicBean;
import com.rdc.bms.q_comic.bean.rv_cell.ComicCell;
import com.rdc.bms.q_comic.bean.rv_cell.DirCell;
import com.rdc.bms.q_comic.config.Constant;
import com.rdc.bms.q_comic.mvp.contract.IComicContract;
import com.rdc.bms.q_comic.mvp.presenter.ComicPresenter;
import com.rdc.bms.q_comic.util.diffutil.ComicDiffUtil;
import com.rdc.leavesloading.LeavesLoading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

public class ComicActivity extends BaseActivity<ComicPresenter> implements IComicContract.View {

    @BindView(R.id.rv_container_act_comic)
    RecyclerView mRvComicContainer;
    @BindView(R.id.iv_back_act_comic)
    ImageView mIvBack;
    @BindView(R.id.tv_title_top_act_comic)
    TextView mTvTitleTop;
    @BindView(R.id.tv_page_count_top_act_comic)
    TextView mTvPageTop;
    @BindView(R.id.tv_page_total_top_act_comic)
    TextView mTvTotalPageTop;
    @BindView(R.id.tv_title_bottom_act_comic)
    TextView mTvTitleBottom;
    @BindView(R.id.tv_page_count_bottom_act_comic)
    TextView mTvPageBottom;
    @BindView(R.id.tv_page_total_bottom_act_comic)
    TextView mTvTotalPageBottom;
    @BindView(R.id.cl_top_layout_act_comic)
    ConstraintLayout mClTopLayout;
    @BindView(R.id.ll_bottom_layout_act_comic)
    LinearLayout mLlBottomLayout;
    @BindView(R.id.ll_setting_act_comic)
    LinearLayout mLlSetting;
    @BindView(R.id.ll_auto_act_comic)
    LinearLayout mLlAuto;
    @BindView(R.id.ll_quality_act_comic)
    LinearLayout mLlQuality;
    @BindView(R.id.ll_brightness_act_comic)
    LinearLayout mLlBrightness;
    @BindView(R.id.ll_dir_act_comic)
    LinearLayout mLlDir;
    @BindView(R.id.ll_right_layout_act_comic)
    LinearLayout mLlRightLayout;
    @BindView(R.id.ll_order_act_comic)
    LinearLayout mLlOrder;
    @BindView(R.id.iv_order_act_comic)
    ImageView mIvOrder;
    @BindView(R.id.tv_order_act_comic)
    TextView mTvOrder;
    @BindView(R.id.rv_dir_act_comic)
    RecyclerView mRvDir;
    @BindView(R.id.ll_quality_setting_layout_act_comic)
    LinearLayout mLlQualitySetting;
    @BindView(R.id.iv_quality_low_ac_comic)
    ImageView mIvQualityLow;
    @BindView(R.id.iv_quality_middle_ac_comic)
    ImageView mIvQualityMiddle;
    @BindView(R.id.iv_quality_high_ac_comic)
    ImageView mIvQualityHigh;
    @BindView(R.id.iv_quality_bottom_act_comic)
    ImageView mIvQualityBottom;
    @BindView(R.id.leavesLoading_loading_layout)
    LeavesLoading mLeavesLoading;
    @BindView(R.id.loadingLayout_act_comic)
    View mLoadingLayout;


    private RvSimpleAdapter mComicAdapter;
    private RvSimpleAdapter mDirAdapter;
    private long mStartChapterId = 0;
    private String mBookId;
    private int mFirstVisibleItemIndex = -1;
    private int mLastVisibleItemIndex = -1;
    private LinearLayoutManager mComicLayoutManager;
    private LinearLayoutManager mDirLayoutManager;
    private PanelManage mPanelManage;
    private int mDirCheckedIndex = -1;
    private ObjectAnimator mLoadingAnimator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //取消标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        mPresenter.getComic(mBookId,mStartChapterId);
    }

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_comic;
    }

    @Override
    protected ComicPresenter getInstance() {
        return new ComicPresenter();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent != null){
            mStartChapterId = intent.getLongExtra("chapterId",0);
            mBookId = intent.getStringExtra("bookId");
        }
        mPanelManage = new PanelManage(
                mClTopLayout,
                mLlBottomLayout,
                mLlRightLayout,
                mLlQualitySetting);

    }

    @Override
    protected void initView() {
        //show loading view
        startLoading();
        mComicLayoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false);
        mRvComicContainer.setLayoutManager(mComicLayoutManager);
        mComicAdapter = new RvSimpleAdapter();
        mRvComicContainer.setAdapter(mComicAdapter);
        mDirLayoutManager = (new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false));
        mRvDir.setLayoutManager(mDirLayoutManager);
        mDirAdapter = new RvSimpleAdapter();
        mRvDir.setAdapter(mDirAdapter);

        mLlBottomLayout.post(new Runnable() {
            @Override
            public void run() {
                mPanelManage.setBottomLayoutHeight(mLlBottomLayout.getHeight());
            }
        });
        mClTopLayout.post(new Runnable() {
            @Override
            public void run() {
                mPanelManage.setTopLayoutHeight(mClTopLayout.getHeight());
            }
        });
        mLlRightLayout.post(new Runnable() {
            @Override
            public void run() {
                mPanelManage.setRightLayoutWidth(mLlRightLayout.getWidth());
                mPanelManage.hideRight();//初始隐藏
            }
        });
    }

    @Override
    protected void onDestroy() {
        //保存最近阅读的章节
        ChapterBean curChapter = mPresenter.getReadingChapter();
        if (curChapter != null){
            mPresenter.saveLastRecordChapter(curChapter);
        }
        super.onDestroy();
    }

    @Override
    protected void initListener() {
        mRvComicContainer.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState){
                    case RecyclerView.SCROLL_STATE_IDLE:
                        mPresenter.sendMessage(ComicPresenter.SCROLL_STATE_IDLE,null);
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        mPresenter.sendMessage(ComicPresenter.SCROLL_STATE_RUNNING,null);
                        mPanelManage.hideRight();
                        break;
                    default:
                        mPresenter.sendMessage(ComicPresenter.SCROLL_STATE_RUNNING,null);
                }
            }

            //往下浏览时dy>0
            @Override
            public void onScrolled(final RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //设置阅读提示（阅读到第几章第几页）
                ComicBean curComic = getReadingComic();
                if (curComic != null){
                    ChapterBean curChapter = mPresenter.getChapterById(curComic.getChapterId());
                    mPresenter.saveReadChapter(curChapter);
                    setChapterTip(
                            curChapter.getChapterNum(),
                            String.valueOf(curComic.getPage()),
                            String.valueOf(curChapter.getEndVal()));
                }
                if (dy < -5){
                    //往上浏览，看前面的章节
                    mPanelManage.showTopAndBottom();
                }else if (dy > 5){
                    mPanelManage.hideTopAndBottom();
                    mPanelManage.setQualityLayoutVisibility(false);
                }
                int f = mComicLayoutManager.findFirstVisibleItemPosition();
                int l = mComicLayoutManager.findLastVisibleItemPosition();
                if (f == RecyclerView.NO_POSITION
                        || (mFirstVisibleItemIndex == f && mLastVisibleItemIndex ==l))
                    return;
                mFirstVisibleItemIndex = f;
                mLastVisibleItemIndex = l;
                mPresenter.onRecyclerViewScroll(dy,mFirstVisibleItemIndex,mLastVisibleItemIndex);
            }
        });
        mLlDir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPanelManage.hideTopAndBottom();
                mPanelManage.showRight();
                mPresenter.indexRvDirFirst();
            }
        });
        mLlOrder.setOnClickListener(new View.OnClickListener() {
            boolean order = true;//正序
            @Override
            public void onClick(View v) {
                if (order){
                    mIvOrder.setImageResource(R.drawable.ic_read_catalog_reverse);
                    mTvOrder.setText("逆序");
                }else {
                    mIvOrder.setImageResource(R.drawable.ic_read_catalog_order);
                    mTvOrder.setText("正序");
                }
                Collections.reverse(mPresenter.getDirList());
                mDirAdapter.setData(createDirCellList(mPresenter.getDirList()));
                order = !order;
            }
        });
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mLlSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(Constant.TIP_NOT_COMPLETE);
            }
        });
        mLlAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(Constant.TIP_NOT_COMPLETE);
            }
        });
        mLlBrightness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(Constant.TIP_NOT_COMPLETE);
            }
        });
        mLlQuality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPanelManage.setQualityLayoutVisibility(true);
            }
        });
        mIvQualityHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.setImageQuality(ComicPresenter.QUALITY_HIGH);
                mPanelManage.setQualityLayoutVisibility(false);
                mIvQualityBottom.setImageResource(R.drawable.ic_read_definition_high);
                showToast("已切换到高清画质");
            }
        });
        mIvQualityMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.setImageQuality(ComicPresenter.QUALITY_MIDDLE);
                mPanelManage.setQualityLayoutVisibility(false);
                mIvQualityBottom.setImageResource(R.drawable.ic_read_definition_middle);
                showToast("已切换到标清画质");
            }
        });
        mIvQualityLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.setImageQuality(ComicPresenter.QUALITY_LOW);
                mPanelManage.setQualityLayoutVisibility(false);
                mIvQualityBottom.setImageResource(R.drawable.ic_read_definition_low);
                showToast("已切换到流畅画质");
            }
        });
    }

    @Override
    public void finishedGetComicData(List<ChapterBean> chapterList) {
        mDirAdapter.addAll(createDirCellList(chapterList));
        // hide loading view
        endLoading();
    }

    @Override
    public void onError(String msg) {
        showToast(msg);
    }

    @Override
    public void setChapterTip(String title, String count, String total) {
        mTvTitleTop.setText(title);
        mTvTotalPageTop.setText(total);
        mTvPageTop.setText(count);
        mTvTitleBottom.setText(title);
        mTvTotalPageBottom.setText(total);
        mTvPageBottom.setText(count);
    }

    @Override
    public void setDirItemSelected(int index) {
        if (mPresenter.getDirList().get(index).isChecked()){
            //已经被选中，直接返回
            return;
        }
        mPresenter.getDirList().get(index).setChecked(true);
        mDirAdapter.update(index,createDirCell(mPresenter.getDirList().get(index)));
        if (mDirCheckedIndex != -1){
            mPresenter.getDirList().get(mDirCheckedIndex).setChecked(false);
            mDirAdapter.update(mDirCheckedIndex,createDirCell(mPresenter.getDirList().get(mDirCheckedIndex)));
        }
        mDirCheckedIndex = index;
    }

    @Override
    public void updateComic(List<ComicBean> newComicList, List<ComicBean> oldComicList) {
        mComicAdapter.getData().clear();
        mComicAdapter.getData().addAll(createComicCellList(newComicList));
        //使用DiffUtil高效刷新，刷新时没有白闪
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(
                new ComicDiffUtil(oldComicList,newComicList),true);
        diffResult.dispatchUpdatesTo(mComicAdapter);
    }

    @Override
    public void rvDirScrollTo(int index) {
        if (index > -1 && index < mPresenter.getDirList().size()){
            mDirLayoutManager.scrollToPositionWithOffset(index,0);
        }
    }

    @Override
    public void rvComicScrollTo(int index) {
        if (index > -1 && index < mPresenter.getComicList().size()){
            mComicLayoutManager.scrollToPositionWithOffset(index,0);
        }
    }

    /**
     * 获取当前正在阅读的漫画页
     * @return
     */
    @Override
    public ComicBean getReadingComic() {
        int curPosition = -1;
        View firstView = mRvComicContainer.getChildAt(0);
        if (firstView.getBottom() > firstView.getHeight()/2){
            curPosition = mRvComicContainer.getChildAdapterPosition(firstView);
        }else {
            View secondView = mRvComicContainer.getChildAt(1);
            if (secondView != null){
                curPosition = mRvComicContainer.getChildAdapterPosition(secondView);
            }
        }
        return curPosition == -1 ? null : mPresenter.getComicList().get(curPosition);
    }

    private void startLoading(){
        mLoadingAnimator = ObjectAnimator
                .ofInt(mLeavesLoading,"progress",0,30)
                .setDuration(1000);
        mLoadingAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mLoadingAnimator.setAutoCancel(true);
        mLoadingAnimator.start();
    }

    private void endLoading(){
        mLeavesLoading.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mLoadingAnimator.isRunning()){
                    endLoading();
                    return;
                }
                ObjectAnimator animator = ObjectAnimator
                        .ofInt(mLeavesLoading,"progress",100)
                        .setDuration(2000);
                animator.setInterpolator(new AccelerateInterpolator());
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mLeavesLoading.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mLoadingLayout.setVisibility(View.GONE);
                            }
                        },600);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animator.start();
            }
        },100);
    }

    private List<BaseRvCell> createComicCellList(List<ComicBean> list){
        List<BaseRvCell> cellList = new ArrayList<>();
        for (ComicBean comicBean : list) {
            ComicCell comicCell = new ComicCell(comicBean);
            comicCell.setListener(new OnClickViewRvListener() {
                @Override
                public void onClick(View view, int position) {

                }

                @Override
                public <C> void onClickItem(C data, int position) {
                    mPanelManage.hideRight();
                }
            });
            cellList.add(comicCell);
        }
        return cellList;
    }

    private List<BaseRvCell> createDirCellList(final List<ChapterBean> list){
        List<BaseRvCell> cellList = new ArrayList<>();
        for (ChapterBean chapterBean : list) {
            DirCell dirCell = createDirCell(chapterBean);
            cellList.add(dirCell);
        }
        return cellList;
    }

    private DirCell createDirCell(final ChapterBean chapterBean){
        DirCell dirCell = new DirCell(chapterBean);
        dirCell.setListener(new OnClickViewRvListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public <C> void onClickItem(C data, int position) {
                setDirItemSelected(position);
                mPresenter.updateComicData(chapterBean);
                rvComicScrollTo(mPresenter.getCurComicListFirstIndex());
            }

        });
        return dirCell;
    }

    /**
     * 管理TopLayout、BottomLayout、RightLayout、QualitySettingLayout的显示和隐藏
     */
    private class PanelManage {
        private View mQualitySettingLayout;//画质设置栏
        private View mTopLayout;
        private View mBottomLayout;
        private View mRightLayout;
        private int mRightLayoutWidth;
        private int mTopLayoutHeight;//px
        private int mBottomLayoutHeight;
        private boolean isTopShowed;
        private boolean isBottomShowed;
        private boolean isRightShowed;
        private boolean isBottomAnimating;
        private boolean isTopAnimating;
        private boolean isRightAnimating;

        PanelManage(View mTopLayout,
                    View mBottomLayout,
                    View mRightLayout,
                    View mQualitySettingLayout) {
            this.mTopLayout = mTopLayout;
            this.mBottomLayout = mBottomLayout;
            this.mRightLayout = mRightLayout;
            this.mQualitySettingLayout = mQualitySettingLayout;
            //是否显示
            isTopShowed = true;
            isRightShowed = true;
            isBottomShowed = true;
            //属性动画执行中
            isTopAnimating = false;
            isRightAnimating = false;
            isBottomAnimating = false;

        }

        void setQualityLayoutVisibility(boolean visible){
            mQualitySettingLayout.setVisibility(visible?View.VISIBLE:View.GONE);
        }

        void showTopAndBottom(){
            if (isBottomAnimating || isTopAnimating || isRightShowed){
                return;
            }
           showTop();
           showBottom();
        }

        void hideTopAndBottom(){
            if (isBottomAnimating || isTopAnimating){
                return;
            }
            hideBottom();
            hideTop();
        }

        void showRight() {
            if (isRightAnimating){
                return;
            }
            if (!isRightShowed){
                mRightLayout.animate()
                        .translationXBy(-mRightLayoutWidth)
                        .setDuration(200)
                        .setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                                isRightAnimating = true;
                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                isRightAnimating = false;
                                isRightShowed = true;
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        })
                        .start();
            }
        }

        void hideRight() {
            if (isRightAnimating){
                return;
            }
            if (isRightShowed){
                mRightLayout.animate()
                        .translationXBy(mRightLayoutWidth)
                        .setDuration(200)
                        .setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                                isRightAnimating = true;
                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                isRightShowed = false;
                                isRightAnimating = false;
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        })
                        .start();
            }
        }

        private void showBottom() {
            if (!isBottomShowed){
                mBottomLayout
                        .animate()
                        .translationYBy(-mBottomLayoutHeight)
                        .setDuration(200)
                        .setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                                isBottomAnimating = true;
                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                isBottomShowed = true;
                                isBottomAnimating = false;
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        })
                        .start();
            }
        }

        private void hideBottom() {
            if (isBottomShowed){
                mBottomLayout
                        .animate()
                        .translationYBy(mBottomLayoutHeight)
                        .setDuration(200)
                        .setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                                isBottomAnimating = true;
                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                isBottomShowed = false;
                                isBottomAnimating = false;
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        })
                        .start();
            }
        }

        private void showTop() {
            if (!isTopShowed){
                mTopLayout.animate()
                        .translationYBy(mTopLayoutHeight)
                        .setDuration(200)
                        .setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                                isTopAnimating = true;
                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                isTopShowed = true;
                                isTopAnimating = false;
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        })
                        .start();
            }
        }

        private void hideTop(){
            if (isTopShowed){
                mTopLayout.animate()
                        .translationYBy(-mTopLayoutHeight)
                        .setDuration(200)
                        .setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                                isTopAnimating = true;
                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                isTopShowed = false;
                                isTopAnimating = false;
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        })
                        .start();
            }
        }

        void setRightLayoutWidth(int mRightLayoutWidth) {
            this.mRightLayoutWidth = mRightLayoutWidth;
        }

        void setTopLayoutHeight(int mTopLayoutHeight) {
            this.mTopLayoutHeight = mTopLayoutHeight;
        }

        void setBottomLayoutHeight(int mBottomLayoutHeight) {
            this.mBottomLayoutHeight = mBottomLayoutHeight;
        }
    }

}
