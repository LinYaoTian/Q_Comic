package com.rdc.bms.q_comic.bean.rv_cell;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rdc.bms.easy_rv_adapter.base.BaseRvCell;
import com.rdc.bms.easy_rv_adapter.base.BaseRvViewHolder;
import com.rdc.bms.q_comic.R;
import com.rdc.bms.q_comic.bean.BookBean;
import com.rdc.bms.q_comic.config.ItemType;

public class BookBillDetailCell extends BaseRvCell<BookBean> {
    public BookBillDetailCell(BookBean bookBean) {
        super(bookBean);
    }

    @Override
    public void releaseResource() {

    }

    @Override
    public int getItemType() {
        return ItemType.TYPE_BOOK_BILL;
    }

    @Override
    public BaseRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return createSimpleHolder(parent, R.layout.cell_book_bill_detaill);
    }

    @Override
    public void onBindViewHolder(final BaseRvViewHolder holder, final int position) {
        ImageView ivCover = holder.getImageView(R.id.iv_cover_cell_book_bill_detail);
        TextView tvTitle = holder.getTextView(R.id.tv_title_cell_book_bill_detail);
        TextView tvIntro = holder.getTextView(R.id.tv_intro_cell_book_bill_detail);
        TextView tvRecent = holder.getTextView(R.id.tv_recent_chapter_cell_book_bill_detail);
        Glide.with(holder.getContext())
                .load(mData.getCoverUrl())
                .into(ivCover);
        tvTitle.setText(mData.getName());
        tvRecent.setText(mData.getRecentChapter());
        tvIntro.setText(mData.getSummary());


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
