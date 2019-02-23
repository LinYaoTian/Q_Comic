package com.rdc.bms.q_comic.bean.rv_cell;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rdc.bms.easy_rv_adapter.base.BaseRvCell;
import com.rdc.bms.easy_rv_adapter.base.BaseRvViewHolder;
import com.rdc.bms.q_comic.R;
import com.rdc.bms.q_comic.bean.BookBillBean;
import com.rdc.bms.q_comic.config.ItemType;
import com.rdc.bms.q_comic.util.ImageUtil;

public class BookBillCell extends BaseRvCell<BookBillBean> {
    public BookBillCell(BookBillBean bookBillBean) {
        super(bookBillBean);
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
        return new BaseRvViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.cell_book_bill,
                parent,
                false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final BaseRvViewHolder holder, final int position) {
        ImageView ivCover1 = holder.getImageView(R.id.iv_cover_1_cell_book_bill);
        ImageView ivCover2 = holder.getImageView(R.id.iv_cover_2_cell_book_bill);
        ImageView ivCover3 = holder.getImageView(R.id.iv_cover_3_cell_book_bill);
        ImageView[] ivs = new ImageView[]{ivCover1,ivCover2,ivCover3};
        TextView tvTitle = holder.getTextView(R.id.tv_title_cell_book_bill);
        TextView tvNum = holder.getTextView(R.id.tv_num_cell_book_bill);
        TextView tvIntro = holder.getTextView(R.id.tv_intro_cell_book_bill);
        for (int i = 0; i < mData.getBookIdList().size(); i++) {
            Glide.with(holder.getContext())
                    .load(ImageUtil.getCoverUrl(mData.getBookIdList().get(i)))
                    .into(ivs[2-i]);
        }
        tvTitle.setText(mData.getTitle());
        tvNum.setText(String.valueOf(mData.getNum())+"本");
        tvIntro.setText(mData.getIntro());

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
