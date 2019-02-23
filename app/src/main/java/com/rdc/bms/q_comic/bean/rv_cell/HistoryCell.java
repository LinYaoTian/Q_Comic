package com.rdc.bms.q_comic.bean.rv_cell;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rdc.bms.easy_rv_adapter.base.BaseRvCell;
import com.rdc.bms.easy_rv_adapter.base.BaseRvViewHolder;
import com.rdc.bms.q_comic.R;
import com.rdc.bms.q_comic.base.BaseBookShelfCell;
import com.rdc.bms.q_comic.bean.BookBean;
import com.rdc.bms.q_comic.config.ItemType;
import com.rdc.bms.q_comic.mvp.model.vo.HistoryVO;

public class HistoryCell extends BaseBookShelfCell<HistoryVO> {

    public HistoryCell(HistoryVO h) {
        super(h);
    }

    @Override
    public void releaseResource() {

    }

    @Override
    public int getItemType() {
        return ItemType.TYPE_HISTORY;
    }

    @Override
    public BaseRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return createSimpleHolder(parent, R.layout.cell_book_history);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(BaseRvViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        TextView tvName = holder.getTextView(R.id.tv_name_cell_book_history);
        TextView tvRecord = holder.getTextView(R.id.tv_record_cell_book_history);
        ImageView ivCover = holder.getImageView(R.id.iv_cover_cell_book_history);
        tvName.setText(mData.getBookBean().getName());
        tvRecord.setText("已读："+mData.getBookBean().getLastRecordChapter());
        Glide.with(holder.getContext()).load(mData.getBookBean().getCoverUrl()).into(ivCover);
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
