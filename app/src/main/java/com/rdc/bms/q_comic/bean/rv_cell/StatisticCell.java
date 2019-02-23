package com.rdc.bms.q_comic.bean.rv_cell;

import android.text.Html;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rdc.bms.easy_rv_adapter.base.BaseRvCell;
import com.rdc.bms.easy_rv_adapter.base.BaseRvViewHolder;
import com.rdc.bms.q_comic.R;
import com.rdc.bms.q_comic.mvp.model.vo.BookTabSupportVO;
import com.rdc.bms.q_comic.config.ItemType;

public class StatisticCell extends BaseRvCell<BookTabSupportVO.SupportItem> {

    public StatisticCell(BookTabSupportVO.SupportItem supportItem) {
        super(supportItem);
    }

    @Override
    public void releaseResource() {

    }

    @Override
    public int getItemType() {
        return ItemType.TYPE_STATISTIC;
    }

    @Override
    public BaseRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return createSimpleHolder(parent, R.layout.cell_statistic);
    }

    @Override
    public void onBindViewHolder(BaseRvViewHolder holder, int position) {
        TextView tvName = holder.getTextView(R.id.tv_name_cell_statistic);
        TextView tvValue = holder.getTextView(R.id.tv_value_cell_statistic);
        ImageView ivCover = holder.getImageView(R.id.iv_icon_cell_statistic);

        tvName.setText(mData.getOption());
        tvName.setTextColor(holder.getContext().getResources().getColor(mData.getColorTextResId()));
        tvValue.setTextColor(holder.getContext().getResources().getColor(mData.getColorTextResId()));
        tvValue.setText(Html.fromHtml(mData.getNum()));
        holder.itemView.setBackgroundResource(mData.getDrawableBgResId());
        Glide.with(holder.getContext()).load(mData.getIconResId()).into(ivCover);
    }
}
