package com.rdc.bms.q_comic.bean.rv_cell;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
import com.rdc.bms.q_comic.util.ScreenUtil;

public class BookCell extends BaseRvCell<BookBean> {

    private boolean isGrid = false;

    public BookCell(BookBean bookBean) {
        super(bookBean);
    }

    @Override
    public void releaseResource() {

    }

    public void setGrid(boolean isGrid){
        this.isGrid = isGrid;
    }

    @Override
    public int getItemType() {
        return ItemType.TYPE_BOOK;
    }

    @Override
    public BaseRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_book_home,parent,false);
        if (isGrid){
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.bottomMargin = ScreenUtil.dip2px(parent.getContext(),10);
            view.setLayoutParams(layoutParams);
        }
        return new BaseRvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BaseRvViewHolder holder, final int position) {
        TextView tvName = holder.getTextView(R.id.tv_name_cell_book_home);
        TextView tvScore = holder.getTextView(R.id.tv_score_cell_book_home);
        TextView tvRecentWords = holder.getTextView(R.id.tv_recentWords_cell_book_home);
        TextView tvSummary = holder.getTextView(R.id.tv_summary_cell_book_home);
        ImageView ivCover = holder.getImageView(R.id.iv_cover_cell_book_home);

        tvName.setText(mData.getName());
        tvScore.setText(mData.getScore());
        int index = mData.getRecentChapter().indexOf("话");
        String chapter = "";
        if (index != -1){
            chapter = mData.getRecentChapter().substring(0,index+1);
        }
        tvRecentWords.setText(chapter);
        tvSummary.setText(mData.getSummary());
        Glide.with(holder.getContext()).load(mData.getCoverUrl()).into(ivCover);

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
