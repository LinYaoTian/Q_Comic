package com.rdc.bms.q_comic.bean.rv_cell;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rdc.bms.easy_rv_adapter.base.BaseRvCell;
import com.rdc.bms.easy_rv_adapter.base.BaseRvViewHolder;
import com.rdc.bms.q_comic.R;
import com.rdc.bms.q_comic.bean.BookInfoBean;
import com.rdc.bms.q_comic.config.ItemType;

import de.hdodenhof.circleimageview.CircleImageView;

public class BookInfoCell extends BaseRvCell<BookInfoBean> {

    public BookInfoCell(BookInfoBean bookInfoBean) {
        super(bookInfoBean);
    }

    @Override
    public void releaseResource() {

    }

    @Override
    public int getItemType() {
        return ItemType.TYPE_BANNER;
    }

    @Override
    public BaseRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseRvViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_book_info,parent,false));
    }

    @Override
    public void onBindViewHolder(BaseRvViewHolder holder, int position) {
        TextView mTvSynopsis = holder.getTextView(R.id.tv_synopsis_cell_book_info);
        CircleImageView mCivAuthorCover = (CircleImageView) holder
                .getView(R.id.civ_author_cover_cell_book_info);
        TextView mTvAuthorName= holder.getTextView(R.id.tv_author_name_cell_book_info);
        TextView mTvFans= holder.getTextView(R.id.tv_fans_num_cell_book_info);
        TextView mTvNotice= holder.getTextView(R.id.tv_notice_cell_book_info);
        ImageView mIvAuthorPriority = holder.getImageView(R.id.iv_author_priority_cell_book_info);
        mTvSynopsis.setText(mData.getIntro());
        mTvNotice.setText(mData.getNotice());
        mTvFans.setText(Html.fromHtml(mData.getFansNum()));
        Glide.with(holder.getContext())
                .load(mData.getAuthorCoverUrl())
                .apply(new RequestOptions().error(R.drawable.svg_error))
                .into(mCivAuthorCover);
        Glide.with(holder.getContext())
                .load(mData.getPriorityResId())
                .into(mIvAuthorPriority);
        mTvAuthorName.setText(mData.getAuthorName());
    }
}
