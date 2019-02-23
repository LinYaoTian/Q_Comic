package com.rdc.bms.q_comic.bean.rv_cell;

import android.text.Html;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rdc.bms.easy_rv_adapter.base.BaseRvCell;
import com.rdc.bms.easy_rv_adapter.base.BaseRvViewHolder;
import com.rdc.bms.q_comic.R;
import com.rdc.bms.q_comic.mvp.model.vo.BookTabSupportVO;
import com.rdc.bms.q_comic.config.ItemType;

import de.hdodenhof.circleimageview.CircleImageView;

public class FanCell extends BaseRvCell<BookTabSupportVO.Fan> {
    public FanCell(BookTabSupportVO.Fan fan) {
        super(fan);
    }

    @Override
    public void releaseResource() {

    }

    @Override
    public int getItemType() {
        return ItemType.TYPE_FAN;
    }

    @Override
    public BaseRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return createSimpleHolder(parent,R.layout.cell_support_fan);
    }

    @Override
    public void onBindViewHolder(BaseRvViewHolder holder, int position) {
        TextView tvRank = holder.getTextView(R.id.tv_rank_cell_fan);
        TextView tvName = holder.getTextView(R.id.tv_name_cell_fan);
        CircleImageView civCover = (CircleImageView) holder.getView(R.id.civ_cover_cell_fan);
        TextView tvInfluence = holder.getTextView(R.id.tv_influence_cell_fan);

        tvRank.setText(String.valueOf(position+4));
        tvName.setText(mData.getName());
        Glide.with(holder.getContext())
                .load(mData.getCoverUrl())
                .apply(new RequestOptions().error(R.drawable.ic_reader_error))
                .into(civCover);
        tvInfluence.setText(Html.fromHtml(mData.getInfluence()));

    }
}
