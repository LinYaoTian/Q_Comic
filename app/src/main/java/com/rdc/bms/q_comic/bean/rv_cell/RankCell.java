package com.rdc.bms.q_comic.bean.rv_cell;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rdc.bms.easy_rv_adapter.base.BaseRvCell;
import com.rdc.bms.easy_rv_adapter.base.BaseRvViewHolder;
import com.rdc.bms.q_comic.R;
import com.rdc.bms.q_comic.bean.RankBean;
import com.rdc.bms.q_comic.config.ItemType;
import com.rdc.bms.q_comic.util.ImageUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

public class RankCell extends BaseRvCell<RankBean> {
    public RankCell(RankBean rankBean) {
        super(rankBean);
    }

    @Override
    public void releaseResource() {

    }

    @Override
    public int getItemType() {
        return ItemType.TYPE_BOOK;
    }

    @Override
    public BaseRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseRvViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.cell_rank,
                parent,
                false));
    }

    @Override
    public void onBindViewHolder(BaseRvViewHolder holder, final int position) {
        View bg = holder.getView(R.id.fl_bg_cell_rank);
        ImageView ivCover = holder.getImageView(R.id.iv_cover_cell_rank);
        ImageView ivTotal = holder.getImageView(R.id.iv_total_cell_rank);
        TextView tvName = holder.getTextView(R.id.tv_name_cell_rank);
        TextView tvTotal = holder.getTextView(R.id.tv_total_cell_rank);
        TextView tvRank = holder.getTextView(R.id.tv_rank_cell_rank);

        final TagFlowLayout tagLayout = (TagFlowLayout) holder.getView(R.id.tfl_cell_rank);
        TagAdapter<String> tagAdapter = new TagAdapter<String>(mData.getTagList()) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater
                        .from(parent.getContext())
                        .inflate(
                                R.layout.tag_tagflowlayout,
                                tagLayout,
                                false);
                tv.setText(mData.getTagList().get(position));
                return tv;
            }
        };
        tagLayout.setAdapter(tagAdapter);
        tagAdapter.notifyDataChanged();
        bg.setBackgroundResource(mData.getBg());
        Glide.with(holder.getContext())
                .load(ImageUtil.getCoverUrl(Integer.valueOf(mData.getBookId())))
                .into(ivCover);
        Glide.with(holder.getContext())
                .load(mData.getTotalIvResId())
                .into(ivTotal);
        tvName.setTextColor(mData.getColorName());
        tvName.setText(mData.getName());
        tvRank.setText(mData.getRank());
        tvRank.setTextColor(mData.getColorRank());
        tvTotal.setText(mData.getTotal());
        tvTotal.setTextColor(mData.getColorRank());
        if (mListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClickItem(mData,position);
                }
            });
        }
    }
}
