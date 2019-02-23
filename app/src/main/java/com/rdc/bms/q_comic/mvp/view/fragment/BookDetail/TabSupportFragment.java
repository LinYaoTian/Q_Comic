package com.rdc.bms.q_comic.mvp.view.fragment.BookDetail;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rdc.bms.easy_rv_adapter.base.BaseRvCell;
import com.rdc.bms.easy_rv_adapter.base.RvSimpleAdapter;
import com.rdc.bms.q_comic.R;
import com.rdc.bms.q_comic.base.BaseLazyFragment;
import com.rdc.bms.q_comic.base.BasePresenter;
import com.rdc.bms.q_comic.mvp.model.vo.BookTabSupportVO;
import com.rdc.bms.q_comic.bean.rv_cell.FanCell;
import com.rdc.bms.q_comic.bean.rv_cell.StatisticCell;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class TabSupportFragment extends BaseLazyFragment {

    @BindView(R.id.rv_statistics_fragment_tab_support)
    RecyclerView mRvStatistics;
    @BindView(R.id.rv_fans_fragment_tab_support)
    RecyclerView mRvFans;
    @BindView(R.id.civ_cover_rank1_cell_fan_top3)
    CircleImageView mCivRank1;
    @BindView(R.id.tv_name_rank1_cell_fan_top3)
    TextView mTvNameRank1;
    @BindView(R.id.tv_influence_rank1_cell_fan_top3)
    TextView mTvInfluenceRank1;
    @BindView(R.id.civ_cover_rank2_cell_fan_top3)
    CircleImageView mCivRank2;
    @BindView(R.id.tv_name_rank2_cell_fan_top3)
    TextView mTvNameRank2;
    @BindView(R.id.tv_influence_rank2_cell_fan_top3)
    TextView mTvInfluenceRank2;
    @BindView(R.id.civ_cover_rank3_cell_fan_top3)
    CircleImageView mCivRank3;
    @BindView(R.id.tv_name_rank3_cell_fan_top3)
    TextView mTvNameRank3;
    @BindView(R.id.tv_influence_rank3_cell_fan_top3)
    TextView mTvInfluenceRank3;
    @BindView(R.id.scv_root_fragment_tab_support)
    NestedScrollView mScvRoot;

    private BookTabSupportVO mBookTabSupportVO;
    private RvSimpleAdapter mFansAdapter;
    private RvSimpleAdapter mStatisticsAdapter;

    public void setData(BookTabSupportVO bean){
        mBookTabSupportVO = bean;
        lazyLoad();
    }

    @Override
    protected BasePresenter getInstance() {
        return null;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_tab_support;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    protected void initView() {
        mRvFans.setLayoutManager(new LinearLayoutManager(
                mBaseActivity,
                LinearLayoutManager.VERTICAL,
                false));
        mFansAdapter = new RvSimpleAdapter();
        mRvFans.setAdapter(mFansAdapter);
        mRvFans.setNestedScrollingEnabled(false);
        mRvStatistics.setLayoutManager(new GridLayoutManager(
                mBaseActivity,
                3));
        mStatisticsAdapter = new RvSimpleAdapter();
        mRvStatistics.setAdapter(mStatisticsAdapter);
        mRvStatistics.setNestedScrollingEnabled(false);

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void lazyLoad() {
        if (!isVisible || !isPrepared || isLoaded){
            return;
        }
        if (mBookTabSupportVO != null){
            List<BaseRvCell> statisticCellList = new ArrayList<>();
            for (BookTabSupportVO.SupportItem item : mBookTabSupportVO.getSupportItemList()) {
                StatisticCell statisticCell = new StatisticCell(item);
                statisticCellList.add(statisticCell);
            }
            mStatisticsAdapter.addAll(statisticCellList);

            List<BaseRvCell> fanCellList = new ArrayList<>();
            for (int i = 0; i < mBookTabSupportVO.getFanList().size(); i++) {
                BookTabSupportVO.Fan fan = mBookTabSupportVO.getFanList().get(i);
                switch (i){
                    case 0:
                        setTop3Data(mCivRank2,mTvNameRank2,mTvInfluenceRank2,fan);
                        break;
                    case 1:
                        setTop3Data(mCivRank1,mTvNameRank1,mTvInfluenceRank1,fan);
                        break;
                    case 2:
                        setTop3Data(mCivRank3,mTvNameRank3,mTvInfluenceRank3,fan);
                        break;
                    default:
                        FanCell fanCell = new FanCell(fan);
                        fanCellList.add(fanCell);
                }
            }
            mFansAdapter.addAll(fanCellList);
            isLoaded = true;
        }
    }

    private void setTop3Data(CircleImageView civCover,
                             TextView tvName,
                             TextView tvInfluence,
                             BookTabSupportVO.Fan fan) {
        Glide.with(mBaseActivity)
                .load(fan.getCoverUrl())
                .apply(new RequestOptions().error(R.drawable.ic_reader_error))
                .into(civCover);
        tvName.setText(fan.getName());
        tvInfluence.setText(Html.fromHtml(fan.getInfluence()));
    }
}
