package com.rdc.bms.q_comic.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rdc.bms.easy_rv_adapter.OnClickViewRvListener;
import com.rdc.bms.easy_rv_adapter.base.BaseRvCell;
import com.rdc.bms.easy_rv_adapter.base.RvSimpleAdapter;
import com.rdc.bms.q_comic.R;
import com.rdc.bms.q_comic.base.BaseActivity;
import com.rdc.bms.q_comic.bean.BookBean;
import com.rdc.bms.q_comic.bean.rv_cell.BookBillDetailCell;
import com.rdc.bms.q_comic.mvp.contract.IBookBillDetailContract;
import com.rdc.bms.q_comic.mvp.model.vo.BookBillDetailVO;
import com.rdc.bms.q_comic.mvp.presenter.BookBillDetailPresenter;
import com.rdc.bms.q_comic.util.AnimateUtil;
import com.rdc.bms.q_comic.util.ImageUtil;
import com.rdc.bms.q_comic.util.StartActUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class BookBillDetailActivity extends BaseActivity<BookBillDetailPresenter>
        implements IBookBillDetailContract.View {

    @BindView(R.id.iv_back_layout_top)
    ImageView mIvBack;
    @BindView(R.id.tv_title_layout_top)
    TextView mTvTopTitle;
    @BindView(R.id.iv_right_layout_top)
    ImageView mIvCollect;
    @BindView(R.id.civ_author_cover_act_book_bill_detail)
    CircleImageView mCivAuthorCover;
    @BindView(R.id.tv_collect_num_act_book_bill_detail)
    TextView mTvCollectNum;
    @BindView(R.id.tv_share_num_act_book_bill_detail)
    TextView mTvShareNum;
    @BindView(R.id.tv_comment_num_act_book_bill_detail)
    TextView mTvCommentNum;
    @BindView(R.id.tv_title_act_book_bill_detail)
    TextView mTvCardTitle;
    @BindView(R.id.iv_book_bill_cover_act_book_bill_detail)
    ImageView mIvBookBillCover;
    @BindView(R.id.tv_summary_act_book_bill_detail)
    TextView mTvSummary;
    @BindView(R.id.tv_book_count_act_book_bill_detail)
    TextView mTvBookCount;
    @BindView(R.id.rv_container_act_book_bill_detail)
    RecyclerView mRvContainer;
    @BindView(R.id.iv_bg_head_act_book_bill_detail)
    ImageView mBgHead;
    @BindView(R.id.app_bar_layout_act_book_bill_detail)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.cl_head_layout_act_book_bill_detail)
    ConstraintLayout mClHeadLayout;

    private RvSimpleAdapter mAdapter;
    private String mBookBillId;
    private BookBillDetailVO mVO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mBookBillId != null){
            mPresenter.getBookBillDetailData(mBookBillId);
        }
    }

    @Override
    protected void onDestroy() {
        if (mVO.isCollected()){
            mPresenter.updateBookBill();
        }
        super.onDestroy();
    }

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_book_bill_detail;
    }

    @Override
    protected BookBillDetailPresenter getInstance() {
        return new BookBillDetailPresenter();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent != null){
            mBookBillId = intent.getStringExtra("bookBillId");
            mPresenter.setCoverBookIdList(intent.getStringArrayListExtra("coverBookIdList"));
        }
    }

    @Override
    protected void initView() {
        mIvCollect.setVisibility(View.VISIBLE);
        mIvCollect.setImageResource(R.drawable.svg_red_like_normal);
        mAdapter = new RvSimpleAdapter();
        mRvContainer.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false));
        mRvContainer.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset <= -mClHeadLayout.getHeight() / 2){
                    mTvTopTitle.setVisibility(View.VISIBLE);
                }else {
                    mTvTopTitle.setVisibility(View.INVISIBLE);
                }
            }
        });
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mIvCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mVO != null){
                    AnimateUtil.likeAnimate(mIvCollect,mVO.isCollected());
                    mVO.setCollected(!mVO.isCollected());
                    mPresenter.collectBookBill(mVO.isCollected());
                }
            }
        });
    }

    @Override
    public void setBookDetailData(BookBillDetailVO data) {
        mVO = data;
        mTvCardTitle.setText(data.getBookTitle());
        mTvTopTitle.setText(data.getBookTitle());
        mTvBookCount.setText(data.getBookCount());
        mTvCollectNum.setText(data.getCollectNum());
        mTvShareNum.setText(data.getShareNum());
        mTvCommentNum.setText(data.getCommentNum());
        mTvSummary.setText(data.getSummary());
        mIvCollect.setImageResource(mVO.isCollected()
                ? R.drawable.svg_red_pressed_like : R.drawable.svg_red_like_normal);
        Glide.with(this)
                .load(data.getBookBillCover())
                .into(mIvBookBillCover);
        Glide.with(this)
                .load(data.getAuthorImage())
                .apply(new RequestOptions()
                .error(R.drawable.svg_error))
                .into(mCivAuthorCover);
        Glide.with(this)
                .load(data.getBookBillCover())
                .apply(new RequestOptions().transform(new BlurTransformation(25)))
                .into(mBgHead);
        ImageUtil.changeBrightness(mBgHead,-90);
        List<BaseRvCell> cellList = new ArrayList<>();
        for (final BookBean bean : data.getBookList()) {
            BaseRvCell cell = new BookBillDetailCell(bean);
            cell.setListener(new OnClickViewRvListener() {
                @Override
                public void onClick(View view, int position) {
                }

                @Override
                public <C> void onClickItem(C data, int position) {
                    StartActUtil.toBookDetail(BookBillDetailActivity.this,bean.getBookId());
                }
            });
            cellList.add(cell);
        }
        mAdapter.addAll(cellList);
    }

    @Override
    public void onError(String msg) {
        showToast(msg);
    }
}
